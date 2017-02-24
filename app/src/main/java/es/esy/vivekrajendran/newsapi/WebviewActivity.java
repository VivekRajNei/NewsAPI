package es.esy.vivekrajendran.newsapi;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        final Activity activity = this;
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                getSupportActionBar().setTitle("Loading...");
                activity.setProgress(newProgress * 100);

                if(newProgress == 100)
                    activity.setTitle(R.string.app_name);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
            {
                // Handle the error
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }
        });

        String url = getIntent().getStringExtra("url");
        if (url != null) {
            Log.i("TAG", "onCreate: " + url);
            webView.loadUrl(url);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_white_24px);
        }
    }
}
