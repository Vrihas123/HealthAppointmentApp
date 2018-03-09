package com.example.vrihas.healthapp.Confirmation.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vrihas.healthapp.Home.HomeActivity;
import com.example.vrihas.healthapp.R;
import com.example.vrihas.healthapp.doctorDatabase.DatabaseHelperDoctor;

public class ConfirmActivity extends AppCompatActivity {

    private TextView heading;
    private Button btnYes,btnNo;
    private DatabaseHelperDoctor myDb;
    String id,heading_txt,msg;
    int slot1, slot2, slot3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        myDb = new DatabaseHelperDoctor(this);
        heading = (TextView) findViewById(R.id.heading_confirm);
        btnYes = (Button) findViewById(R.id.btn_yes);
        btnNo = (Button) findViewById(R.id.btn_no);
        myDb = new DatabaseHelperDoctor(this);
        id = getIntent().getStringExtra("DOC_ID");
        slot1 = getIntent().getIntExtra("DOC_AVAIL1",0);
        slot2 = getIntent().getIntExtra("DOC_AVAIL2",0);
        slot3 = getIntent().getIntExtra("DOC_AVAIL3",0);
        heading_txt = getIntent().getStringExtra("HEAD");
        heading.setText(heading_txt);

        if (slot2==0 && slot1==0 && slot3==0){
            msg = "The Appointment is  cancelled";
        }
        else {
            msg = "The Appointment is confirmed";
        }

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate= myDb.updateData(id,slot1,slot2,slot3);
                if (isUpdate){
                    Toast.makeText(ConfirmActivity.this,msg,Toast.LENGTH_SHORT).show();
                    Intent ii = new Intent(ConfirmActivity.this, HomeActivity.class);
                    startActivity(ii);
                    finish();
                }
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent ii = new Intent(ConfirmActivity.this, HomeActivity.class);
                    startActivity(ii);
                    finish();
            }
        });

    }
}
