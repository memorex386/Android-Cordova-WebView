package com.mtsdealersolutions.cordova_webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bradley.thome on 4/22/17.
 */

public abstract class CordovaBaseFragmentSupportV4 extends Fragment implements CordovaWebView.initCordova {


    private CordovaWebView mCordovaWebView;
    private Bundle mSavedInstanceState;

    @Override
    @CallSuper
    public void initCordova(CordovaWebView cordovaWebView) {
        if (mCordovaWebView == null) mCordovaWebView = cordovaWebView;
        mCordovaWebView.loadSavedInstanceState(mSavedInstanceState);
        mSavedInstanceState = null;
    }


    @Override
    @CallSuper
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSavedInstanceState = savedInstanceState;
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       if (mCordovaWebView == null) findCordovaView(view);

        if (mCordovaWebView == null)
            throw new RuntimeException("CordovaBaseFragmentSupportV4 must have a CordovaWebView attached before super.onViewCreated is called");

    }

    private boolean findCordovaView(View rootView) {
        if (mCordovaWebView != null) return true;
        if (rootView == null) return false;
        if (rootView instanceof CordovaWebView) {
            mCordovaWebView = (CordovaWebView) rootView;
            return true;
        }
        if (rootView instanceof ViewGroup) {
            ViewGroup group = ((ViewGroup) rootView);
            if (group.getChildCount() == 0) return false;
            for (int i = 0; i < group.getChildCount(); i++) {
                if (findCordovaView(group.getChildAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public CordovaWebView getCordovaWebView() {
        return mCordovaWebView;
    }

    public void setCordovaWebView(CordovaWebView cordovaWebView) {
        mCordovaWebView = cordovaWebView;
    }

    @Override
    @CallSuper
    public void onPause() {
        mCordovaWebView.onPause();
        super.onPause();
    }

    @CallSuper
    public void onNewIntent(Intent intent) {
        mCordovaWebView.onNewIntent(intent);
    }

    @Override
    @CallSuper
    public void onResume() {
        super.onResume();
        mCordovaWebView.onResume();
    }

    @Override
    @CallSuper
    public void onStop() {
        mCordovaWebView.onStop();
        super.onStop();
    }

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();
        mCordovaWebView.onStart();
    }

    @Override
    @CallSuper
    public void onDestroy() {
        mCordovaWebView.onDestroy();
        super.onDestroy();
    }

    @Override
    @CallSuper
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        mCordovaWebView.onActivityResult(requestCode, resultCode, intent);
        super.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    @CallSuper
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mCordovaWebView.onSaveInstanceState(outState);
    }
}
