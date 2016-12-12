package com.test.fb.dreamteambim.workat.burlaka.myfbteststructure.ModelVP;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Operator on 08.12.2016.
 */
public class Lot {
    private String owner;
    private String text;
    private String uid;
    private String lotId;
    private boolean isSelected;

    public Lot() {
    }

    public Lot(String owner, String text, String uid, String lotId) {
        this.owner = owner;
        this.text = text;
        this.uid = uid;
        this.lotId = lotId;
    }

    public Lot(String uid, String owner, String message) {
        this.text = message;
        this.uid = uid;
        this.owner = owner;
    }

    public String getUid() {
        return uid;
    }

    public String getText() {
        return text;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    @Exclude
    public Map<String, Object> toMapInfo() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("text", text);
        result.put("owner", owner);
        result.put("lotId",lotId);
        return result;
    }
}