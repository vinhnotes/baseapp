package com.vtu.baseapp.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vtu.baseapp.BaseApplication;


public class BaseActivity extends AppCompatActivity {
    protected BaseApplication medicalAppication;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        medicalAppication = (BaseApplication)this.getApplicationContext();
    }
    protected void onResume() {
        super.onResume();
        medicalAppication.setCurrentActivity(this);
    }
    protected void onPause() {
        clearReferences();
        super.onPause();
    }
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    private void clearReferences(){
        Activity currActivity = medicalAppication.getCurrentActivity();
        if (this.equals(currActivity))
            medicalAppication.setCurrentActivity(null);
    }
}
