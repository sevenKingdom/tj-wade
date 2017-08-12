package com.carry.control.model.po;

import java.io.Serializable;

public class Notice implements Serializable {

     private Long id;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String department;
    private String data;
    private Integer level;

     public Long getId(){
     	return id;
     }
     
     public void setId(Long id){
	 	this.id=id;
	 }


     public Integer getLevel(){
     	return level;
     }
     
     public void setLevel(Integer level){
	 	this.level=level;
	 }

}