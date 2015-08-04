package com.example.yazilimstajyer1.bildirimler;

import android.app.Application;
import android.util.Log;

/*
 * Created by yazilim.stajyer1 on 08.07.2015.
 */
import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;


public class PushNotifyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(getApplicationContext(),"c5NURqy38aV9AeSZaWDriKeAN4zk5ddOQXklbA82","ozc44Yxhw5t2KuMWhAcT5vQLGYqoBRPKkpSOHgjq");
        //ilk paramatreye Application ID, ikinci parametreye Client KEY yazılıyor.
        ParsePush.subscribeInBackground("",new SaveCallback() {
            @Override
            public void done(ParseException e)
            {
                if(e==null)
                {
                    Log.d("msg_info", "successful!");
                }
                else
                {
                    Log.e("problem_info","failed!"+e);
                }

            }
        });


    }
}