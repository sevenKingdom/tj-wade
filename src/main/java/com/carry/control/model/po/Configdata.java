package com.carry.control.model.po;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by songxianying on 17/7/15.
 */

public class Configdata {
    private int id;
    private String name;
    private String data;
    private JsonObject configdata;
    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonObject getConfigdata() {
        if (this.configdata == null && data != null) {
            JsonParser jsonParser = new JsonParser();
            this.configdata = (JsonObject) jsonParser.parse(data);
        }
        return configdata;
    }
}
