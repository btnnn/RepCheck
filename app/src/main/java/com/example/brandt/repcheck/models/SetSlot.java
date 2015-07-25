package com.example.brandt.repcheck.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.brandt.repcheck.database.schemas.SetSlotTable;
import com.example.brandt.repcheck.util.adapters.StandardRowItem;
import com.example.brandt.repcheck.util.database.DBHandler;
import com.example.brandt.repcheck.util.database.DataObject;
import com.example.brandt.repcheck.util.database.QueryParams.QueryParams;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by Brandt on 7/24/2015.
 */
public class SetSlot extends DataObject implements StandardRowItem{

    private String name;
    private int reps;
    private double weight;
    private Date lastUsed;

    public static SetSlot defaultSet(String name) {
        SetSlot setSlot = new SetSlot(10, 135);
        setSlot.setName(name);
        return setSlot;
    }

    private SetSlot() {
        super(SetSlotTable.class, true);
    }

    public SetSlot(int reps, double weight) {
        this();
        this.reps = reps;
        this.weight = weight;
    }

    public SetSlot(int reps, double weight, Date lastUsed) {
        this(reps, weight);
        this.lastUsed = lastUsed;
    }

    private SetSlot(int id, int reps, double weight, String lastUsed) {
        this(reps, weight, DBHandler.convertStringTime(lastUsed));
        this.id = id;
        setIsNewRecord(false);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<SetSlot> selectAllByDate(Context context) {
        QueryParams queryParams = new QueryParams();
        queryParams.orderBy = SetSlotTable.LAST_USED + " DESC";
        return Arrays.asList((SetSlot[]) new SetSlot().selectAll(context, queryParams));
    }

    public static SetSlot first(Context context) {
        QueryParams queryParams = new QueryParams();
        queryParams.orderBy = SetSlotTable.LAST_USED + " DESC";
        queryParams.limit = "1";
        return(SetSlot) new SetSlot().selectAll(context, queryParams)[0];
    }

    public int getReps() {
        return reps;
    }

    public double getWeight() {
        return weight;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    @Override
    protected ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SetSlotTable.ID, id);
        contentValues.put(SetSlotTable.NAME, name);
        contentValues.put(SetSlotTable.REPS, reps);
        contentValues.put(SetSlotTable.WEIGHT, weight);
        contentValues.put(SetSlotTable.LAST_USED, DBHandler.dateToString(lastUsed));
        return contentValues;
    }

    @Override
    protected Object bindCursor(Cursor cursor) {
        SetSlot set = new SetSlot(
                cursor.getInt(0),
                cursor.getInt(2),
                cursor.getDouble(3),
                cursor.getString(4)
        );
        set.setName(cursor.getString(1));
        return set;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getText() {
        return
                ;
    }
}