package com.aaruush16.webarch.aaruush16.DataSync;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.aaruush16.webarch.aaruush16.ConnectionDetector.ConnectionDetector;
import com.aaruush16.webarch.aaruush16.RealmClasses.Event;
import com.aaruush16.webarch.aaruush16.VolleySingleton.AppController;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Rishi on 10-08-2016.
 */
public class DataFetcher {
    ConnectionDetector connectionDetector;
    JSONObject eventData;
    String URL="http://aaruush.net/testing123/eventData/eventNames.json";
    String URLEvents="http://aaruush.net/testing123/eventData/eventData.json";
    String URLWorkshops="http://aaruush.net/testing123/eventData/workshop.json";

    public void fetchJSON(final Context context){

        final JsonObjectRequest jsonObjectRequestDomains=new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    RealmList<Event> eventList=new RealmList<Event>();
                    Iterator<String> stringIterator=response.keys();
                    while (stringIterator.hasNext()){
                        String type=stringIterator.next();
                        JSONArray row = response.getJSONArray(type);
                        for(int i=0;i<row.length();i++) {
                            JSONObject jsonObject = eventData.getJSONObject(row.getString(i));
                            Event event = new Event();
                            event.setId(jsonObject.getInt("id"));
                            event.setType(type);
                            event.setName(row.getString(i));
                            if(jsonObject.has("desc"))
                                event.setDescription(jsonObject.getString("desc"));
                            if(jsonObject.has("coords"))
                                event.setContact(jsonObject.getString("coords"));
                            //event.setImageURL(row.getJSONObject("gsx$imageurl").getString("$t"));
                            eventList.add(event);
                        }
                    }
                    saveData(eventList,context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, URLEvents, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                eventData=response;
                AppController.getInstance().addToRequestQueue(jsonObjectRequestDomains);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        //Detecting internet connection
        connectionDetector=new ConnectionDetector(context);
        Boolean isConnected=connectionDetector.isConnectingToInternet();
        if (isConnected){
            Toast.makeText(context, "Updating Database", Toast.LENGTH_SHORT).show();
            AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        }else{
            Toast.makeText(context, "No Internet Connectivity\nPlease connect to internet !!!", Toast.LENGTH_SHORT).show();
        }
}

    public void saveData(final RealmList<Event> eventList, final Context context){
        final Realm realm=Realm.getDefaultInstance();
        final Boolean success=true;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(eventList);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                RealmQuery<Event> query=realm.where(Event.class);
                RealmResults<Event> results=query.findAll();
                Iterator<Event> eventIterator=results.iterator();
                while (eventIterator.hasNext()){
                    Event event=eventIterator.next();
                    Log.w("Realm",event.getName());
                    //Toast.makeText(context, "Realm: "+event.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        }, null);
        realm.close();
    }

}
