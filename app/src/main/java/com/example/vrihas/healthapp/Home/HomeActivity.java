package com.example.vrihas.healthapp.Home;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.vrihas.healthapp.R;
import com.example.vrihas.healthapp.SignUp.view.SignUpActivity;
import com.example.vrihas.healthapp.doctorDatabase.DatabaseHelperDoctor;
import com.example.vrihas.healthapp.doctorDatabase.DoctorContact;
import com.example.vrihas.healthapp.helper.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDoctors;
    private List<DoctorContact> doctorContactList;
    private HomeRecyclerAdapter homeRecyclerAdapter;
    private SharedPrefs sharedPrefs;
    private Button btn_log_out;
    Context context;
    DoctorContact d, d1, d2, d3, d4;
    DatabaseHelperDoctor databaseHelperDoctor = new DatabaseHelperDoctor(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPrefs = new SharedPrefs(this);
        if(!sharedPrefs.isDocInfoAdded()){
            insertDoctorInfo();
        }
        initViews();
        initObjects();
        btn_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefs.setLogin(false);
                Intent i = new Intent(HomeActivity.this, SignUpActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void insertDoctorInfo(){
        sharedPrefs.setDoctorData(true);
        d = new DoctorContact();
        d.setId(0);
        d.setName("Dr. Shreyansh Sahare");
        d.setSlot1("9am to 12pm");
        d.setSlot2("2pm to 5pm");
        d.setSlot3("6pm to 8pm");
        d.setAvail_slot1(0);
        d.setAvail_slot2(0);
        d.setAvail_slot3(0);
        databaseHelperDoctor.insertData(d);

        d1 = new DoctorContact();
        d1.setId(1);
        d1.setName("Dr. Devesh Sharma");
        d1.setSlot1("9am to 11am");
        d1.setSlot2("12pm to 1pm");
        d1.setSlot3("3pm to 5pm");
        d1.setAvail_slot1(0);
        d1.setAvail_slot2(0);
        d1.setAvail_slot3(0);
        databaseHelperDoctor.insertData(d1);

        d2 = new DoctorContact();
        d2.setId(2);
        d2.setName("Dr. Samveg Thaker");
        d2.setSlot1("8am to 10am");
        d2.setSlot2("11pm to 12pm");
        d2.setSlot3("2pm to 4pm");
        d2.setAvail_slot1(0);
        d2.setAvail_slot2(0);
        d2.setAvail_slot3(0);
        databaseHelperDoctor.insertData(d2);

        d3 = new DoctorContact();
        d3.setId(3);
        d3.setName("Dr. Yash Agrawal");
        d3.setSlot1("10am to 12pm");
        d3.setSlot2("1pm to 4pm");
        d3.setSlot3("5pm to 8pm");
        d3.setAvail_slot1(0);
        d3.setAvail_slot2(0);
        d3.setAvail_slot3(0);
        databaseHelperDoctor.insertData(d3);

        d4 = new DoctorContact();
        d4.setId(4);
        d4.setName("Dr. Sajal Tikariha");
        d4.setSlot1("9am to 11am");
        d4.setSlot2("1pm to 4pm");
        d4.setSlot3("5pm to 8pm");
        d4.setAvail_slot1(0);
        d4.setAvail_slot2(0);
        d4.setAvail_slot3(0);
        databaseHelperDoctor.insertData(d4);
    }

    private void initViews(){
        recyclerViewDoctors = (RecyclerView) findViewById(R.id.recycler_doctor_list);
        btn_log_out = (Button) findViewById(R.id.log_out_btn);
    }

    private void initObjects(){
        doctorContactList = new ArrayList<>();
        homeRecyclerAdapter = new HomeRecyclerAdapter(doctorContactList,context );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewDoctors.setLayoutManager(layoutManager);
        recyclerViewDoctors.setItemAnimator(new DefaultItemAnimator());
        recyclerViewDoctors.setHasFixedSize(true);
        recyclerViewDoctors.setAdapter(homeRecyclerAdapter);
        recyclerViewDoctors.setNestedScrollingEnabled(false);
        databaseHelperDoctor = new DatabaseHelperDoctor(this);

        getDataFromSQLite();
    }

    private void getDataFromSQLite(){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                doctorContactList.clear();
                doctorContactList.addAll(databaseHelperDoctor.getALlDoctors());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                homeRecyclerAdapter.notifyDataSetChanged();

            }
        }.execute();
    }
}
