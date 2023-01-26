package com.thebigocean.cloudnscript;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find view by their ids
        webView = findViewById(R.id.webView);
        pbar = findViewById(R.id.pbar);

        pbar.setMax(100);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.clearCache(true);
        webView.clearHistory();
        webView.loadUrl("https://cloudnscripts.com/");
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        pbar.setProgress(0);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.main_color));
        }

        webView.setWebChromeClient(new WebChromeClient()
        {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pbar.setProgress(newProgress);
                if(newProgress==100){
                    pbar.setVisibility(View.GONE);
                }
                else{
                    pbar.setVisibility(View.VISIBLE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();

        }


    }
}