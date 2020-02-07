package com.mobisem.engineering.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.mobisem.engineering.Activities.FirebaseGraph;
import com.mobisem.engineering.Activities.MeditationActivity;
import com.mobisem.engineering.MeditationApplication;
import com.mobisem.engineering.Models.FirebaseModel;
import com.mobisem.engineering.Models.GraphModel;
import com.mobisem.engineering.Models.MeditationModel;
import com.mobisem.engineering.R;

import org.w3c.dom.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PersonInformationAdapter extends FirestoreRecyclerAdapter<FirebaseModel,PersonInformationAdapter.Information> {
Context context;

    public PersonInformationAdapter(Context context,@NonNull FirestoreRecyclerOptions<FirebaseModel> options) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull Information holder, int position, @NonNull final FirebaseModel model) {
        MeditationModel meditationModel=new MeditationModel();

        DateFormat df =  new SimpleDateFormat("HH:mm:SS -- dd-MM-yy");
        String stringDate = df.format(model.getTime());
        MeditationApplication.getInstance().setCurrentTime(model.getTime());

        holder.myText.setText(stringDate);
        holder.myText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICKED", "YES ");
                HashMap<Integer,Double>map=new HashMap<>();

                if(model.getBase()!=null)
                 map.put(0,model.getBase());

                if(model.getBreathing()!=null){
                 map.put(1,model.getBreathing());}
                if(model.getMindless()!=null){
                 map.put(2,model.getMindless());}

                if(model.getVisulization()!=null) {
                    map.put(3,model.getVisulization());}

                Intent intent=new Intent(context, FirebaseGraph.class);
                intent.putExtra("map",map);
                   context.startActivity(intent);

            }
        });




    }

    @NonNull
    @Override
    public Information onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.information2, parent, false);
        return new Information(v);
    }

    public void deleteFromDB(int position){

        getSnapshots().getSnapshot(position).getReference().delete();



    }

    class Information extends RecyclerView.ViewHolder{
          TextView myText;

        public Information(@NonNull View itemView) {
            super(itemView);
            myText=itemView.findViewById(R.id.information2Tv);


        }
    }

}
