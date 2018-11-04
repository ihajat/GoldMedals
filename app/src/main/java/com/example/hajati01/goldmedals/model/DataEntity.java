package com.example.hajati01.goldmedals.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

@Entity
public class DataEntity {

    private String flag;

    @PrimaryKey
    @NotNull
    private String name;


    public DataEntity(String flag, String name) {
        this.flag = flag;
        this.name = name;

    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static String AssetJSONFile (String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();
        return new String(formArray);
    }

    public static DataEntity[] populateData(Context context) throws IOException, JSONException {
        String json = AssetJSONFile("load.json",context);
        DataEntity[] countries = new Gson().fromJson(json, DataEntity[].class);
        return countries;
    }
}