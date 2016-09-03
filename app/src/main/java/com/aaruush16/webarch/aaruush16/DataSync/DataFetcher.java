package com.aaruush16.webarch.aaruush16.DataSync;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.aaruush16.webarch.aaruush16.ConnectionDetector.ConnectionDetector;
import com.aaruush16.webarch.aaruush16.RealmClasses.Event;
import com.aaruush16.webarch.aaruush16.RealmClasses.Workshop;
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
                    //Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                    while (stringIterator.hasNext()){
                        String type=stringIterator.next();
                        JSONArray row = response.getJSONArray(type);
                        // Toast.makeText(context, ""+type, Toast.LENGTH_SHORT).show();
                        for(int i=0;i<row.length();i++) {
                            JSONObject jsonObject = eventData.getJSONObject(row.getString(i));
                            Event event = new Event();
                            event.setId(jsonObject.getInt("id"));
                            event.setType(type);
                            event.setName(row.getString(i));
                            //Toast.makeText(context, ""+i+" : "+event.getId(), Toast.LENGTH_SHORT).show();
                            if(jsonObject.has("desc"))
                                event.setDescription(jsonObject.getString("desc"));
                            else
                                event.setDescription("Nil");
                            if(jsonObject.has("rules"))
                                event.setRules(jsonObject.getString("rules"));
                            else
                                event.setRules("Nil");
                            if(jsonObject.has("coords"))
                                event.setContact(jsonObject.getString("coords"));
                            else
                                event.setContact("Nil");
                            //event.setImageURL(row.getJSONObject("gsx$imageurl").getString("$t"));
                            if(jsonObject.has("rounds"))
                                event.setRounds(jsonObject.getString("rounds"));
                            Realm realm=Realm.getDefaultInstance();
                            RealmQuery<Event> query=realm.where(Event.class).equalTo("id",event.getId());
                            Event event_q=new Event();
                            event_q=query.findFirst();

                            if(event_q==null){
                                event.setFav(false);
                            }else {
                                if(event_q.getFav()==true) {
                                    event.setFav(true);
                                }else{
                                    event.setFav(false);
                                }
                            }


                            eventList.add(event);
                        }
                    }
                    //Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
                    saveDataEvent(eventList,context);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
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

        JsonObjectRequest workshopRequest=new JsonObjectRequest(Request.Method.GET, URLWorkshops, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        RealmList<Workshop> workshopList=new RealmList<>();
                        Iterator<String> stringIterator=response.keys();
                        //Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                        try {
                            while (stringIterator.hasNext()) {
                                String name=stringIterator.next();
                                JSONObject workshop_json = response.getJSONObject(name);
                                Workshop workshop = new Workshop();
                                workshop.setId(workshop_json.getInt("id"));
                          //      Toast.makeText(context, "ID: "+workshop.getId(), Toast.LENGTH_SHORT).show();
                                workshop.setName(name);
                                workshop.setDesc(workshop_json.getString("description"));
                                workshop.setTeam(workshop_json.getString("team"));
                                workshop.setDate(workshop_json.getString("date"));
                                workshop.setCost(workshop_json.getString("cost"));
                                workshop.setTime(workshop_json.getString("time"));
                                workshop.setCompany_name(workshop_json.getString("company_name"));
                                workshopList.add(workshop);
                            }
                            //Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
                            saveDataWorkshop(workshopList,context);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
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
            AppController.getInstance().addToRequestQueue(workshopRequest);
        }else{
            Toast.makeText(context, "No Internet Connectivity\nPlease connect to internet !!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveDataEvent(final RealmList<Event> eventList, final Context context){
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
                Toast.makeText(context, "Events Updated!!!", Toast.LENGTH_SHORT).show();
            }
        }, null);
        realm.close();
    }

    public void saveDataWorkshop(final RealmList<Workshop> workshopList, final Context context){
        final Realm realm=Realm.getDefaultInstance();
        final Boolean success=true;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(workshopList);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                RealmQuery<Workshop> query=realm.where(Workshop.class);
                RealmResults<Workshop> results=query.findAll();
                Iterator<Workshop> eventIterator=results.iterator();
                while (eventIterator.hasNext()){
                    Workshop workshop=eventIterator.next();
                    Log.w("Realm",workshop.getName());
                    //Toast.makeText(context, "Realm: "+event.getName(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(context, "Workshops Updated!!!", Toast.LENGTH_SHORT).show();
            }
        }, null);
        realm.close();
    }

}
