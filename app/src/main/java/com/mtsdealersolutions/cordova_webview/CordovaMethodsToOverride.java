package com.mtsdealersolutions.cordova_webview;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by bradley.thome on 4/23/17.
 */

public interface CordovaMethodsToOverride {

    void restoreInstanceState(Bundle savedInstanceBundle);

    void onPause();

    void onNewIntent(Intent intent);

    void onResume();

    void onStop();

    void onStart();

    void onDestroy();

    void onActivityResult(int requestCode, int resultCode, Intent intent);

    void startActivityForResult(Intent intent, int requestCode, Bundle options);

    void onSaveInstanceState(Bundle outState);

    boolean onCreateOptionsMenu(Menu menu);

    boolean onPrepareOptionsMenu(Menu menu);

    boolean onOptionsItemSelected(MenuItem item);

    void onRequestPermissionsResult(int requestCode, String permissions[],
                                    int[] grantResults);

}
