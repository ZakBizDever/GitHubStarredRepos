package com.hp5.gitrepos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RepositoriesActivity extends AppCompatActivity {

    private RecyclerView reposRecyclerView;
    private RecyclerView.Adapter repoAdapter;

    private List<Repository> reposList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        Toolbar appBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(appBar);
        getSupportActionBar().setTitle("GitHub repositories");
        getSupportActionBar().setIcon(getDrawable(R.drawable.ic_action_github));

        reposRecyclerView = (RecyclerView) findViewById(R.id.repos_RV);
        reposRecyclerView.setHasFixedSize(true);
        reposRecyclerView.setLayoutManager( new LinearLayoutManager(this));

        reposList = new ArrayList<>();

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

        repoAdapter = new RepositoryAdapter(reposList,this);
        reposRecyclerView.setAdapter(repoAdapter);


    }
}
