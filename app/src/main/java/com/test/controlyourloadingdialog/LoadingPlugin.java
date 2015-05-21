package com.test.controlyourloadingdialog;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by rex.yau on 5/21/2015.
 */
public class LoadingPlugin implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = "LoadingPlugin";
    private WeakReference<Activity> mActivityWeakReference;
    private ProgressDialog mProgressDialog;

    private boolean isLoading;

    public void showLoading() {

        Activity context = mActivityWeakReference.get();
        if (context == null) {
            throw new IllegalArgumentException("youShouldCheckFirst");
        }

        if (isLoading) {
            throw new IllegalArgumentException("youShouldOnlyCallOneTime");
        }

        mProgressDialog = ProgressDialog.show(context, "title", "message", true, false, new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                Log.d(TAG, "onCancel");

            }
        });
        isLoading = true;
    }

    public void dismissLoading() {

        Activity context = mActivityWeakReference.get();
        if (context == null) {
            throw new IllegalArgumentException("youShouldCheckFirst");
        }

        if (!isLoading) {
            throw new IllegalArgumentException("youShouldOnlyCallOneTime");
        }

        isLoading = false;
        mProgressDialog.dismiss();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivityWeakReference = new WeakReference<>(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivityWeakReference.clear();
        mActivityWeakReference = null;
    }
}
