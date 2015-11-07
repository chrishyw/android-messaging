package com.sinch.messagingtutorial.app;

import android.app.Application;
import com.parse.Parse;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "LhpRJmwT46q473pt3JxEuoWBIgcIWHQVz6RLvXAP", "bhO5bcWbXGcnnsq992jWbH2fRm9y8va7G01WT3S7");
    }
}
