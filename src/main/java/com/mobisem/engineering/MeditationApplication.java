package com.mobisem.engineering;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.mobisem.engineering.Models.MeditationModel;

import java.util.ArrayList;
import java.util.Date;

public class MeditationApplication extends MultiDexApplication {

    private String currentUser;
    private ArrayList<MeditationModel>meditationModelArrayList=new ArrayList<>();
    private Date currentTime;

    public Date getCurrentTime() {
        return currentTime;
    }

    public MeditationApplication setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
        return this;
    }

    public ArrayList<MeditationModel> getMeditationModelArrayList() {
        return meditationModelArrayList;
    }

    public MeditationApplication setMeditationModelArrayList(ArrayList<MeditationModel> meditationModelArrayList) {
        this.meditationModelArrayList = meditationModelArrayList;
        return this;
    }

    public static MeditationApplication getMeditationApplication() {
        return meditationApplication;
    }

    public static void setMeditationApplication(MeditationApplication meditationApplication) {
        MeditationApplication.meditationApplication = meditationApplication;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public MeditationApplication setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
        return this;
    }

    private static MeditationApplication meditationApplication;


    public static MeditationApplication getInstance() {
        if (meditationApplication == null) {
            meditationApplication = new MeditationApplication();
        }

        return meditationApplication;
    }




    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }




}
