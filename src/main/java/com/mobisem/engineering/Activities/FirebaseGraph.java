package com.mobisem.engineering.Activities;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobisem.engineering.MeditationApplication;
import com.mobisem.engineering.Models.MeditationModel;
import com.mobisem.engineering.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseGraph extends AppCompatActivity {
      TextView closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_graph);
        closeButton=findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//
            }
        });


        Intent intent = getIntent();
        HashMap<Integer, Double> hashMap = (HashMap<Integer, Double>) intent.getSerializableExtra("map");
        AnyChartView
                anyChartView = findViewById(R.id.chart2);

        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        String[] meditations = {"Base", "Breathing", "Mindless", "Visualization"};


        for (HashMap.Entry<Integer, Double> map : hashMap.entrySet()) {

            if (map.getKey()==0) {

                dataEntries.add(new ValueDataEntry(meditations[0], map.getValue()));
            }

            if (map.getKey()==1) {

                dataEntries.add(new ValueDataEntry(meditations[1], map.getValue()));
            }

            if (map.getKey()==2) {

                dataEntries.add(new ValueDataEntry(meditations[2], map.getValue()));
            }

            if (map.getKey()==3) {

                dataEntries.add(new ValueDataEntry(meditations[3], map.getValue()));
            }

        }

        pie.data(dataEntries);
        anyChartView.setChart(pie);

    }

    @Override

    public void onBackPressed() {
        if (true) {

        } else {
            super.onBackPressed();
        }
    }









    


}
