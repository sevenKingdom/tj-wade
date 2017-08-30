package com.carry.control.model.po;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by songxianying on 17/8/29.
 */
public class Loginfo implements Serializable {

    private Long id;

    private String department;

    private String filepath;

    private Timestamp time;

    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
