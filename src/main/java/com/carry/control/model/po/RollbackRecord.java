package com.carry.control.model.po;

import java.io.Serializable;
import java.sql.Timestamp;

public class RollbackRecord implements Serializable {
     private Long id;
     private Long inspectorid;
     private Long planid;
     private String about;
     private Timestamp time;
     private String department;

     public Long getId(){
     	return id;
     }
     
     public void setId(Long id){
	 	this.id=id;
	 }

     public Long getInspectorid(){
     	return inspectorid;
     }
     
     public void setInspectorid(Long inspectorid){
	 	this.inspectorid=inspectorid;
	 }

     public Long getPlanid(){
     	return planid;
     }
     
     public void setPlanid(Long planid){
	 	this.planid=planid;
	 }

     public String getAbout(){
     	return about;
     }
     
     public void setAbout(String about){
	 	this.about=about;
	 }

     public Timestamp getTime(){
     	return time;
     }
     
     public void setTime(Timestamp time){
	 	this.time=time;
	 }

     public String getDepartment(){
     	return department;
     }
     
     public void setDepartment(String department){
	 	this.department=department;
	 }
	@Override
    public String toString() {
        return "RollbackRecord{" +
                	"id=" + id +
                	"inspectorid=" + inspectorid +
                	"planid=" + planid +
                	"about=" + about +
                	"time=" + time +
                	"department=" + department +
                '}';
    }
}