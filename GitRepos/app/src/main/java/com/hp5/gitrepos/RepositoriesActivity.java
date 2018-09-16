package com.hp5.gitrepos;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

    private static final String GIT_API_URL="https://api.github.com/search/repositories?q=created:>2017-10-22&sort=stars&order=desc";
    private RecyclerView reposRecyclerView;
    private RecyclerView.Adapter repoAdapter;
    private List<Repository> reposList;

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

        reposRecyclerView = (RecyclerView) findViewById(R.id.repos_RV);
        reposRecyclerView.setHasFixedSize(true);
        reposRecyclerView.setLayoutManager( new LinearLayoutManager(this));

        reposList = new ArrayList<>();

<<<<<<< HEAD
        loadRepositoriesData();

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
=======
        //Begin : Creating static data (in a List) in order to test our RecylerView, adapter ...
        Repository repoTestItem;
        int min = 533;
        int max = 13874;

        Random r = new Random();
        int num= 0 ;
        for (int i =1;  i<=20; i++)
        {
            num= r.nextInt(max - min + 1) + min;
            repoTestItem = new Repository("Repo Name "+i, "This is repository N°"+i,
                    "www.google.com","Guest"+i,""+num );

            reposList.add(repoTestItem);
        }
        //End;
>>>>>>> 601af7df6ab0d610a1a727f77528d36c7c6d1002

                    }
                },
            //On failure, this code will be executed instead.
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG);
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
