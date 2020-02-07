package com.mobisem.engineering.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.type.LatLng;
import com.mobisem.engineering.Adapter.PersonInformationAdapter;
import com.mobisem.engineering.Connection.TcpClient;
import com.mobisem.engineering.MeditationApplication;
import com.mobisem.engineering.Models.FirebaseModel;
import com.mobisem.engineering.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterActivity extends AppCompatActivity {
   private FirebaseFirestore db=FirebaseFirestore.getInstance();
   private CollectionReference collectionReference=db.collection("subject");
   private PersonInformationAdapter personInformationAdapter;
    FirestoreRecyclerOptions<FirebaseModel> model;
    TextView userName;
    FirebaseAuth mAuth;
    ImageView exitButton;
    TcpClient tcpClient;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
        mAuth = FirebaseAuth.getInstance();
        userName=findViewById(R.id.personNameInfo);
        exitButton=findViewById(R.id.exit);

        exitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tcpClient != null) {
                    tcpClient.stopClient();
                }
                MeditationApplication.getInstance().getMeditationModelArrayList().clear();
                mAuth.signOut();
                Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(intent);



            }
        });
        userName.setText(mAuth.getCurrentUser().getEmail());

        setUpRecycler();






}


private void setUpRecycler(){


    Query query =collectionReference.whereEqualTo("uuid", MeditationApplication.getInstance().getCurrentUser())
            .orderBy("time", Query.Direction.DESCENDING);

    model= new FirestoreRecyclerOptions.Builder<FirebaseModel>()
            .setQuery(query,FirebaseModel.class)
            .build();
     personInformationAdapter=new PersonInformationAdapter(this,model);
     RecyclerView recyclerView=findViewById(R.id.recyclerView);
     //recyclerView.setHasFixedSize(true);
     recyclerView.setLayoutManager(new LinearLayoutManager(this));
     recyclerView.setAdapter(personInformationAdapter);
     new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
     ItemTouchHelper.LEFT) {
         @Override
         public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
             return false;
         }

         @Override
         public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

             personInformationAdapter.deleteFromDB(viewHolder.getAdapterPosition());

         }
     }).attachToRecyclerView(recyclerView);


}

    @Override
    protected void onStart() {
        super.onStart();
        personInformationAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        personInformationAdapter.stopListening();
    }





}
