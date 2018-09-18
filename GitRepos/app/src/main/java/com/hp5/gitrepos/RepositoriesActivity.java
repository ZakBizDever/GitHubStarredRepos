package com.hp5.gitrepos;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RepositoriesActivity extends AppCompatActivity {

    //test :
    private String gitAPIurl = "https://api.github.com/search/repositories?q=created:>2017-10-22&sort=stars&order=desc&page=";
    private static final String GIT_API_URL="https://api.github.com/search/repositories?q=created:>2017-10-22&sort=stars&order=desc";
    private RecyclerView reposRecyclerView;
    private RecyclerView.Adapter repoAdapter;
    private ProgressBar progressBar;
    private List<Repository> reposList;
    private LinearLayoutManager layoutManager;
    private boolean isScrolling;
    private int currentItems,totalItems,scrolledOutItems, page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        //Begin : Setting up the customized ToolBar
        Toolbar appBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(appBar);
        getSupportActionBar().setTitle("GitHub repositories");
        getSupportActionBar().setIcon(getDrawable(R.drawable.ic_action_github));
        //End

        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        reposRecyclerView = (RecyclerView) findViewById(R.id.repos_RV);
        reposRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        reposRecyclerView.setLayoutManager(layoutManager );

        reposList = new ArrayList<>();

        loadRepositoriesData();

        //Begin : setting an "OnScroll Listener" to implement the "Endless Scroll" concept -Pagination-
        reposRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrolledOutItems = layoutManager.findFirstVisibleItemPosition();

                if (isScrolling && ((currentItems + scrolledOutItems) == totalItems))
                {
                    isScrolling=false;
                    page++;
                    loadMoreRepositoriesData(page);
                }

            }
        });
        //End

    }

    private void loadRepositoriesData() {

        //Begin : While data is loading,let's show a Progress Dialog
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading repositories...");
        progressDialog.show();
        //End

        StringRequest stringRequest = new StringRequest(Request.Method.GET, GIT_API_URL,
                new Response.Listener<String>() {
            //On success, the code inside this method will be executed;
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("items");

                            for (int i=0; i<array.length(); i++)
                            {
                                JSONObject repoObject = array.getJSONObject(i);
                                JSONObject repoOwnerObject = repoObject.getJSONObject("owner");
                                Repository repo = new Repository(repoObject.getString("name"),
                                        repoObject.getString("description"),
                                        repoOwnerObject.getString("avatar_url"),
                                        repoOwnerObject.getString("login"),
                                        repoObject.getString("stargazers_count"),
                                        repoObject.getString("html_url"));

                                reposList.add(repo);
                            }

                            repoAdapter = new RepositoryAdapter(reposList,getApplicationContext());
                            reposRecyclerView.setAdapter(repoAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
            //On failure, this code will be executed instead.
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG);
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void loadMoreRepositoriesData(int pageFunc)
    {
        progressBar.setVisibility(View.VISIBLE);

        gitAPIurl = GIT_API_URL+"&page="+String.valueOf(pageFunc);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, gitAPIurl,
                new Response.Listener<String>() {
                    //On success, the code inside this method will be executed;
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("items");

                            for (int i=0; i<array.length(); i++)
                            {
                                JSONObject repoObject = array.getJSONObject(i);
                                JSONObject repoOwnerObject = repoObject.getJSONObject("owner");
                                Repository repo = new Repository(repoObject.getString("name"),
                                        repoObject.getString("description"),
                                        repoOwnerObject.getString("avatar_url"),
                                        repoOwnerObject.getString("login"),
                                        repoObject.getString("stargazers_count"),
                                        repoObject.getString("html_url"));

                                reposList.add(repo);
                            }

                            progressBar.setVisibility(View.GONE);
                            repoAdapter.notifyDataSetChanged();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                //On failure, this code will be executed instead.
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG);
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
