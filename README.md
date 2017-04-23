[ ![Download](https://api.bintray.com/packages/memorex386/maven/cordova_webview/images/download.svg) ](https://bintray.com/memorex386/maven/cordova_webview/_latestVersion)

# Android-Cordova-WebView
Easy to implement Cordova WebView for embedding within a native Android App

## Overview

Cordova is a great cross-platform tool that enables you to...  

* Host web assets locally on your device like a virtual local web server
* Give a native-like app experience while using html
* Easily attach plug-ins that interact with native device functions (camera, gps, etc)
* Works with Ionic, PhoneGap, or any html website really!

The purpose of this library is to make it easy to add Cordova into any Android app.  You can implement the ```CordovaWebView``` into a layout, extend your activity/fragment off of one of the many ```CordovaBaseActivity/Fragment``` classes, or just attach callbacks to your Activity/Fragment lifecycle to interact with the CordovaWebView.

This gives great flexibility within your app, allowing to you have Activites/Fragments that run in native Android, and others that run CordovaWebView.  To the user it will seem seamless and give the best possible experience!

## Gradle
    repositories
    {
    ...
    maven {
       url  'https://bintray.com/memorex386/maven'
    }
    ...
    }


    dependencies
    {
    ...
    compile 'com.mtsdealersolutions: cordova_webview:0.0.12'
    ...
    }

## Quick Implementation

Quick implementation will get you up and running!
        
    public class TestCordovaActivity extends CordovaBaseAppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // setContentView must be called before super.onCreate in the Activity
        // Make sure your layout has CordovaWebView inside it
        setContentView(R.layout.basiclinearlayout);
        
        super.onCreate(savedInstanceState);

        // The url should always start with 'file:///android_asset'
        // The 'www' is the folder inside the assets folder where 'index.html' is kept
        // If your website uses <base href> then make sure it references <base href="/www/">
        getCordovaWebView().loadUrl("file:///android_asset/www/index.html");
        }
    }
    
__Recommended__ : Put a config.xml file inside 'res/xml' for Cordova.
        
####

## Recommended Implementation

#### Load a Config.xml (optional)...

Create/copy a config.xml file inside 'res/xml' for the Cordova plug-in manager to reference and for Cordova settings to work properly

#### ...add a CordovaWebView...

Load the CordovaWebView inside of a layout

    <com.mtsdealersolutions.cordova_webview.CordovaWebView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/cordova_web_view"/>
        
#### ...extend one of these Acitvity/Fragments...

    CordovaBaseActivity
    CordovaBaseAppCompatActivity
    
    CordovaBaseFragment
    CordovaBaseFragmentSupportV4
    
    
#### ...or manually attach the view to the Activity/Fragment Lifecycle...

    public class CordovaTestActivity extends AppCompatActivity {

    private CordovaWebView mCordovaWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_new_activity_main_web_view);

        mCordovaWebView = (CordovaWebView) findViewById(R.id.cordova_web_view);

        if (savedInstanceState == null) {
            mCordovaWebView.loadUrl("file:///android_asset/www/index.html");
        } else {
            mCordovaWebView.loadSavedInstanceState(savedInstanceState);
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCordovaWebView.loadSavedInstanceState(savedInstanceState);
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

    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        mCordovaWebView.startActivityForResult(intent, requestCode, options);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        mCordovaWebView.onCreateOptionsMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        mCordovaWebView.onPrepareOptionsMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        mCordovaWebView.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mCordovaWebView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    }