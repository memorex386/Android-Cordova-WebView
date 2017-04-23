package com.mtsdealersolutions.cordova_webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by bradley.thome on 4/23/17.
 */

public class CordovaTestLocationActivity extends AppCompatActivity implements CordovaActivityLoad {

    private CordovaWebView mCordovaWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_new_activity_main_web_view);

        mCordovaWebView = (CordovaWebView) findViewById(R.id.cordova_web_view);

        restoreInstanceState(savedInstanceState);

        mCordovaWebView.loadUrl("file:///android_asset/www/index.html");

    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceBundle) {
        mCordovaWebView.loadSavedInstanceState(savedInstanceBundle);
    }

    @Override
    public void onPause() {
        super.onPause();
        mCordovaWebView.onPause();
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mCordovaWebView.onNewIntent(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        mCordovaWebView.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        mCordovaWebView.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        mCordovaWebView.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCordovaWebView.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mCordovaWebView.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mCordovaWebView.onSaveInstanceState(outState);
    }
}
