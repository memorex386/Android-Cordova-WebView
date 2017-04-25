package com.mtsdealersolutions.cordova_webview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;

/**
 * Created by bradley.thome on 4/22/17.
 */

public class CordovaBaseActivity extends Activity implements CordovaWebView.initCordova {


    private CordovaWebView mCordovaWebView;

    @Override
    @CallSuper
    public void initCordova(CordovaWebView cordovaWebView) {
        if (mCordovaWebView == null) mCordovaWebView = cordovaWebView;
    }

    @CallSuper
    public void setCordovaWebView(CordovaWebView cordovaWebView) {
        mCordovaWebView = cordovaWebView;
    }

    @CallSuper
    public CordovaWebView getCordovaWebView() {
        return mCordovaWebView;
    }

    @Override
    @CallSuper
    protected void onPause() {
        mCordovaWebView.onPause();
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onNewIntent(Intent intent) {
        mCordovaWebView.onNewIntent(intent);
        super.onNewIntent(intent);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();


        if (mCordovaWebView == null)
            throw new RuntimeException("CordovaBaseActivity must have a CordovaWebView attached before super.onCreate is called");

        mCordovaWebView.onResume();
    }

    @Override
    @CallSuper
    protected void onStop() {
        mCordovaWebView.onStop();
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        mCordovaWebView.onStart();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        mCordovaWebView.onDestroy();
        super.onDestroy();
    }

    @Override
    @CallSuper
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        mCordovaWebView.onActivityResult(requestCode, resultCode, intent);
        super.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    @CallSuper
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mCordovaWebView.onSaveInstanceState(outState);
    }
}
