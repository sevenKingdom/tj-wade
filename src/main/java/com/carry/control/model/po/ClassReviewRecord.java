package com.carry.control.model.po;

import java.io.Serializable;

public class ClassReviewRecord  implements Serializable {
     private Long id;
     private Long classid;
     private Long planid;
     private String lowquality;
     private Integer style;
     private Long createdat;


     public Long getId(){
     	return id;
     }
     
     public void setId(Long id){
	 	this.id=id;
	 }

     public Long getClassid(){
     	return classid;
     }
     
     public void setClassid(Long classid){
	 	this.classid=classid;
	 }

     public Long getPlanid(){
     	return planid;
     }
     
     public void setPlanid(Long planid){
	 	this.planid=planid;
	 }

     public String getLowquality(){
     	return lowquality;
     }
     
     public void setLowquality(String lowquality){
	 	this.lowquality=lowquality;
	 }

     public Integer getStyle(){
     	return style;
     }
     
     public void setStyle(Integer style){
	 	this.style=style;
	 }

     public Long getCreatedat(){
     	return createdat;
     }
     
     public void setCreatedat(Long createdat){
	 	this.createdat=createdat;
	 }
	@Override
    public String toString() {
        return "ClassReviewRecord{" +
                	"id=" + id +
                	"classid=" + classid +
                	"planid=" + planid +
                	"lowquality=" + lowquality +
                	"style=" + style +
                	"createdat=" + createdat +
                '}';
    }
}