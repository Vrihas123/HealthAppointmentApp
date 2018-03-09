package com.example.vrihas.healthapp.Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vrihas.healthapp.Confirmation.view.ConfirmActivity;
import com.example.vrihas.healthapp.R;
import com.example.vrihas.healthapp.doctorDatabase.DatabaseHelperDoctor;
import com.example.vrihas.healthapp.doctorDatabase.DoctorContact;

import java.util.List;

/**
 * Created by vrihas on 8/3/18.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {

    private List<DoctorContact> doctorContactList;
    DatabaseHelperDoctor myDb;
    private Context myContext;
    private String available,booked;


    public HomeRecyclerAdapter(List<DoctorContact> doctorContactList,Context context) {
        this.doctorContactList = doctorContactList;
        this.myContext = context;
    }


    @Override
    public HomeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_item, parent, false);
        myDb = new DatabaseHelperDoctor(myContext);
        available = "  (available)  ";
        booked = "  (booked)  ";
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeRecyclerAdapter.ViewHolder holder, final int position) {

        if(doctorContactList.get(position).getAvail_slot1()==1){
            holder.slot_1.setText(doctorContactList.get(position).getSlot1().concat(booked));
            holder.layout1.setBackgroundColor(Color.parseColor("#F44336"));
            holder.btn_slot1.setVisibility(View.GONE);
            holder.btn_cancel.setVisibility(View.VISIBLE);
        }else{
            holder.slot_1.setText(doctorContactList.get(position).getSlot1().concat(available));
        }
        if(doctorContactList.get(position).getAvail_slot2()==1){
            holder.layout2.setBackgroundColor(Color.parseColor("#F44336"));
            holder.btn_slot2.setVisibility(View.GONE);
            holder.btn_cancel.setVisibility(View.VISIBLE);
            holder.slot_2.setText(doctorContactList.get(position).getSlot2().concat(booked));

        }else{
            holder.slot_2.setText(doctorContactList.get(position).getSlot2().concat(available));
        }
        if(doctorContactList.get(position).getAvail_slot3()==1){
            holder.layout3.setBackgroundColor(Color.parseColor("#F44336"));
            holder.btn_slot3.setVisibility(View.GONE);
            holder.btn_cancel.setVisibility(View.VISIBLE);
            holder.slot_3.setText(doctorContactList.get(position).getSlot3().concat(booked));
        }else {
            holder.slot_3.setText(doctorContactList.get(position).getSlot3().concat(available));
        }

        holder.doc_name.setText(doctorContactList.get(position).getName());
        holder.btn_slot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToUpdateActivity(String.valueOf(doctorContactList.get(position).getId()),1,0,0);
            }
        });
        holder.btn_slot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUpdateActivity(String.valueOf(doctorContactList.get(position).getId()),0,1,0);
            }
        });
        holder.btn_slot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUpdateActivity(String.valueOf(doctorContactList.get(position).getId()),0,0,1);
            }
        });
        holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCancelActivity(String.valueOf(doctorContactList.get(position).getId()),0,0,0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorContactList.size();
    }

    public void goToUpdateActivity(String doc_id, int doc_avail1, int doc_avail2, int doc_avail3){
        Intent i = new Intent(myContext, ConfirmActivity.class);
        i.putExtra("HEAD","Do you want to confirm the booking of this slot?");
        i.putExtra("DOC_ID",doc_id);
        i.putExtra("DOC_AVAIL1",doc_avail1);
        i.putExtra("DOC_AVAIL2",doc_avail2);
        i.putExtra("DOC_AVAIL3",doc_avail3);
        myContext.startActivity(i);
    }

    public void goToCancelActivity(String doc_id, int doc_avail1, int doc_avail2, int doc_avail3){
        Intent i = new Intent(myContext, ConfirmActivity.class);
        i.putExtra("HEAD","Do you want to cancel the appointment of this doctor?");
        i.putExtra("DOC_ID",doc_id);
        i.putExtra("DOC_AVAIL1",doc_avail1);
        i.putExtra("DOC_AVAIL2",doc_avail2);
        i.putExtra("DOC_AVAIL3",doc_avail3);
        myContext.startActivity(i);

    }
    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView doc_name, slot_1, slot_2, slot_3;
        public Button btn_slot1, btn_slot2, btn_slot3, btn_cancel;
        public LinearLayout layout1, layout2, layout3;

        public ViewHolder(View itemView) {
            super(itemView);
            myContext = itemView.getContext();
            doc_name = (TextView) itemView.findViewById(R.id.doctorName);
            slot_1 = (TextView) itemView.findViewById(R.id.txt_slot1);
            slot_2 = (TextView) itemView.findViewById(R.id.txt_slot2);
            slot_3 = (TextView) itemView.findViewById(R.id.txt_slot3);
            btn_slot1 = (Button) itemView.findViewById(R.id.btn_slot_1);
            btn_slot2 = (Button) itemView.findViewById(R.id.btn_slot_2);
            btn_slot3 = (Button) itemView.findViewById(R.id.btn_slot_3);
            btn_cancel = (Button) itemView.findViewById(R.id.btn_remove_booking);
            layout1 = (LinearLayout) itemView.findViewById(R.id.layout_slot1);
            layout2 = (LinearLayout) itemView.findViewById(R.id.layout_slot2);
            layout3 = (LinearLayout) itemView.findViewById(R.id.layout_slot3);
        }
    }

}
