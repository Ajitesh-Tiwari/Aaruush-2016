package com.aaruush16.webarch.aaruush16.DataSync;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

    String URL="https://spreadsheets.google.com/feeds/list/1-W0923TO_T9nlEq7_O-xXBf80TTKhyUeHKb1_0sWBUg/1/public/values?alt=json";

    public void fetchJSON(final Context context){

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray list=response.getJSONObject("feed").getJSONArray("entry");
                    int len = list.length();
                    //String result="";
                    RealmList<Event> eventList=new RealmList<Event>();
                    for (int i = 0; i < len; i++) {
                        JSONObject row = list.getJSONObject(i);
                        Event event=new Event();
                        event.setId(Integer.parseInt(row.getJSONObject("gsx$id").getString("$t")));
                        event.setType(row.getJSONObject("gsx$type").getString("$t"));
                        event.setSubType(row.getJSONObject("gsx$sub-type").getString("$t"));
                        event.setName(row.getJSONObject("gsx$name").getString("$t"));
                        event.setDescription(row.getJSONObject("gsx$description").getString("$t"));
                        event.setContact(row.getJSONObject("gsx$contact").getString("$t"));
                        event.setCost(row.getJSONObject("gsx$cost").getString("$t"));
                        event.setDate(row.getJSONObject("gsx$date").getString("$t"));
                        event.setImageURL(row.getJSONObject("gsx$imageurl").getString("$t"));

                        eventList.add(event);

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


        //TODO:IF request gets cancel then some safety feature needed to be included
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void saveData(final RealmList<Event> eventList, final Context context){
        final Realm realm=Realm.getDefaultInstance();
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
