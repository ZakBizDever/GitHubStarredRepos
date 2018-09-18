package com.hp5.gitrepos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class RepoShowActivity extends AppCompatActivity {

    private String repoURL;
    private WebView gitHubWB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_show);


        gitHubWB = (WebView) findViewById(R.id.githubRepo_WV);
        WebSettings webSettings = gitHubWB.getSettings();
        webSettings.setJavaScriptEnabled(true);
        gitHubWB.setWebViewClient(new WebViewClient());

        //Begin : Recupartion of extras came with the intent & initializing repo's URL
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                repoURL= "www.github.com";
            } else {
                repoURL= extras.getString("repoURL");
            }
        } else {
            repoURL= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }
        //End

        gitHubWB.loadUrl(repoURL);

    }

    @Override
    public void onBackPressed() {
        if (gitHubWB.canGoBack())
        {
            gitHubWB.goBack();
        }else super.onBackPressed();

    }

}
