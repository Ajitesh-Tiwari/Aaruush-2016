package com.aaruush16.webarch.aaruush16.HomeFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aaruush16.webarch.aaruush16.ConnectionDetector.ConnectionDetector;
import com.aaruush16.webarch.aaruush16.R;
import com.aaruush16.webarch.aaruush16.VolleySingleton.AppController;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Ravi/28/06/2016
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    public Context context;
    private View rootView;
    FirebaseUser firebaseUser;
    private List<Comment> comments = new ArrayList<>();
    private RecyclerView recyclerView;
    private CommentAdapter mAdapter;
    EditText editTextComment;
    Button buttonComment;
    private SwipeRefreshLayout swipeRefreshLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView= (RecyclerView) rootView.findViewById(R.id.comments);
        mAdapter = new CommentAdapter(getActivity(),comments);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        editTextComment=(EditText)rootView.findViewById(R.id.etComment);
        buttonComment=(Button)rootView.findViewById(R.id.btnComment);
        buttonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeComment();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                fetchData();
            }
        });

        CircularImageView circleImageView= (CircularImageView) rootView.findViewById(R.id.profileImage);
        context=getActivity();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser.getPhotoUrl()!=null)
            Glide.with(this).load(firebaseUser.getPhotoUrl()).into(circleImageView);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");

        return rootView;
    }

    private void fetchData() {
        swipeRefreshLayout.setRefreshing(true);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest("http://aaruush.net/testing123/get_feed.php",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        comments.clear();
                        for (int i=response.length()-1;i>=0;i--){
                            JSONObject jsonObject= null;
                            try {
                                jsonObject = response.getJSONObject(i);
                                Comment comment=new Comment();
                                comment.setName(jsonObject.getString("user_name"));
                                comment.setEmail(jsonObject.getString("user_mail"));
                                comment.setComment(jsonObject.getString("post_text"));
                                comment.setPhoto(jsonObject.getString("user_photo"));
                                comments.add(comment);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
                Snackbar snackbar = Snackbar
                        .make(getView(), "Something Went Wrong", Snackbar.LENGTH_INDEFINITE)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                fetchData();
                            }
                        });

                snackbar.show();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
    private void makeComment(){
        final String comment=editTextComment.getText().toString();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://aaruush.net/testing123/add_feed.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        editTextComment.setText("");
                        fetchData();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar snackbar = Snackbar
                        .make(getView(), "Something Went Wrong", Snackbar.LENGTH_INDEFINITE)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                makeComment();
                            }
                        });

                snackbar.show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_name", firebaseUser.getDisplayName());
                params.put("user_mail", firebaseUser.getEmail());
                if(firebaseUser.getPhotoUrl()!=null)
                    params.put("user_photo", firebaseUser.getPhotoUrl().toString());
                params.put("post_text", comment);
                return params;
            }
        };

        if(comment.length()>100)
            showSnackbar("100 Char Limit Exceeded");
        else if(comment.length()>0)
            AppController.getInstance().addToRequestQueue(stringRequest);
        else
            showSnackbar("Please write something");
    }

    private void showSnackbar(String s) {
        Snackbar snackbar = Snackbar
                .make(getView(), s, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void onRefresh() {
        fetchData();
    }
}