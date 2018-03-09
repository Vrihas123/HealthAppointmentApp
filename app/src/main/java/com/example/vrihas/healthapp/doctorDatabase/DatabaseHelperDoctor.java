package com.example.vrihas.healthapp.doctorDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrihas on 8/3/18.
 */

public class DatabaseHelperDoctor extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "doctor_contact.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SLOT1 = "slot1";
    private static final String COLUMN_SLOT2 = "slot2";
    private static final String COLUMN_SLOT3 = "slot3";
    private static final String COLUMN_AVAIL1 = "avail1";
    private static final String COLUMN_AVAIL2 = "avail2";
    private static final String COLUMN_AVAIL3 = "avail3";
    Context context1;
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table contacts (id integer primary key autoincrement  , "+
            "name text not null , slot1 text not null , slot2 text not null , slot3 text not null , "+
            "avail1 integer not null , avail2 integer not null , avail3 integer not null);";

    public DatabaseHelperDoctor(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertData(DoctorContact dc) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,dc.getName());
        contentValues.put(COLUMN_SLOT1,dc.getSlot1());
        contentValues.put(COLUMN_SLOT2,dc.getSlot2());
        contentValues.put(COLUMN_SLOT3,dc.getSlot3());
        contentValues.put(COLUMN_AVAIL1,dc.getAvail_slot1());
        contentValues.put(COLUMN_AVAIL2,dc.getAvail_slot2());
        contentValues.put(COLUMN_AVAIL3,dc.getAvail_slot3());
        db.insert(TABLE_NAME,null ,contentValues);
        db.close();

    }

    public boolean updateData(String id,int avail1,int avail2,int avail3) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_AVAIL1,avail1);
        contentValues.put(COLUMN_AVAIL2,avail2);
        contentValues.put(COLUMN_AVAIL3,avail3);
        db.update(TABLE_NAME, contentValues,COLUMN_ID + " = ?",new String[] { id });
        db.close();
        return true;
    }

    public List<DoctorContact> getALlDoctors() {
        String[] columns = {COLUMN_ID,COLUMN_NAME,COLUMN_SLOT1,COLUMN_SLOT2,COLUMN_SLOT3,COLUMN_AVAIL1,COLUMN_AVAIL2,COLUMN_AVAIL3};

        String sortOrder =
                COLUMN_NAME + " ASC";
        List<DoctorContact> doctorList = new ArrayList<DoctorContact>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder);

        if (cursor.moveToFirst()){
            do {
                DoctorContact contact = new DoctorContact();
                contact.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                contact.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                contact.setSlot1(cursor.getString(cursor.getColumnIndex(COLUMN_SLOT1)));
                contact.setSlot2(cursor.getString(cursor.getColumnIndex(COLUMN_SLOT2)));
                contact.setSlot3(cursor.getString(cursor.getColumnIndex(COLUMN_SLOT3)));
                contact.setAvail_slot1(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_AVAIL1))));
                contact.setAvail_slot2(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_AVAIL2))));
                contact.setAvail_slot3(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_AVAIL3))));
                doctorList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return doctorList;
    }

}
