package com.example.vrihas.healthapp.doctorDatabase;

/**
 * Created by vrihas on 8/3/18.
 */

public class DoctorContact {
    int id, avail_slot1, avail_slot2, avail_slot3;
    String name, slot1, slot2, slot3;

    public void setId(int id) {
        this.id = id;
    }

    public void setAvail_slot1(int avail_slot1) {
        this.avail_slot1 = avail_slot1;
    }

    public void setAvail_slot2(int avail_slot2) {
        this.avail_slot2 = avail_slot2;
    }

    public void setAvail_slot3(int avail_slot3) {
        this.avail_slot3 = avail_slot3;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlot1(String slot1) {
        this.slot1 = slot1;
    }

    public void setSlot2(String slot2) {
        this.slot2 = slot2;
    }

    public void setSlot3(String slot3) {
        this.slot3 = slot3;
    }

    public int getId() {

        return id;
    }

    public int getAvail_slot1() {
        return avail_slot1;
    }

    public int getAvail_slot2() {
        return avail_slot2;
    }

    public int getAvail_slot3() {
        return avail_slot3;
    }

    public String getName() {
        return name;
    }

    public String getSlot1() {
        return slot1;
    }

    public String getSlot2() {
        return slot2;
    }

    public String getSlot3() {
        return slot3;
    }
}
