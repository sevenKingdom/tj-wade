package com.carry.control.model.po;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by songxianying on 17/7/15.
 */
@ResponseBody
public class Test {
    private int id;
    private String name;

    public int getId() {
        return id;
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
}
