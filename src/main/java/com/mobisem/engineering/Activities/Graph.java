package com.mobisem.engineering.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobisem.engineering.MeditationApplication;
import com.mobisem.engineering.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Graph extends AppCompatActivity {

 AnyChartView anyChartView;
 String[] meditations={"Base","Breathing","Mindless","Visualization"};
 TextView saveButton;
    FirebaseFirestore db;
    FirebaseAuth mAuth;

    Date currentTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        anyChartView=findViewById(R.id.chart1);
        saveButton=findViewById(R.id.saveButton);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentTime = Calendar.getInstance().getTime();


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( anyChartView.isShown()){

                   addIntoDB();
                   finish();

                }

            }
        });




        int size =MeditationApplication.getInstance().getMeditationModelArrayList().size();

        Pie pie= AnyChart.pie();
        List<DataEntry>dataEntries=new ArrayList<>();

        for(int i=0;i<size;i++){

            if(MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getID()==0){

                dataEntries.add(new ValueDataEntry(meditations[0],MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getResult()));

            }

            if(MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getID()==1){

                dataEntries.add(new ValueDataEntry(meditations[1],MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getResult()));

            }


            if(MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getID()==2){

                dataEntries.add(new ValueDataEntry(meditations[2],MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getResult()));

            }

            if(MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getID()==3){

                dataEntries.add(new ValueDataEntry(meditations[3],MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getResult()));

            }




        }


        pie.data(dataEntries);
        anyChartView.setChart(pie);
    }




    public void addIntoDB() {

        int size = MeditationApplication.getInstance().getMeditationModelArrayList().size();

        HashMap<String, Object> hashMap = new HashMap<>();

        for (int i = 0; i < size; i++) {


            if (MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getID() == 0) {

                hashMap.put("base", MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getResult());
                hashMap.put("meditation ID",0);

            }

            if (MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getID() == 1) {

                hashMap.put("breathing", MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getResult());
                hashMap.put("meditation ID",1);

            }
            if (MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getID() == 2) {

                hashMap.put("mindless", MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getResult());
                hashMap.put("meditation ID",2);

            }
            if (MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getID() == 3) {

                hashMap.put("visulization", MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getResult());
                hashMap.put("meditation ID",3);


            }


        }
        hashMap.put("uuid",mAuth.getCurrentUser().getUid());
        hashMap.put("time",currentTime);

        db.collection("subject").add(hashMap);









    }
}
