package com.carry.control.model.po;

import java.io.Serializable;

public class UserData implements Serializable {
     private Long id;
     private String name;
     private String password;
     private String department;
     private Long dutyid;
     private Long infoid;
     private Integer state;
     private String token;
     private Integer role;
     private Integer post;
    private Integer score;
    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

     public Long getId(){
     	return id;
     }
     
     public void setId(Long id){
	 	this.id=id;
	 }

     public String getName(){
     	return name;
     }
     
     public void setName(String name){
	 	this.name=name;
	 }

     public String getPassword(){
     	return password;
     }
     
     public void setPassword(String password){
	 	this.password=password;
	 }

     public String getDepartment(){
     	return department;
     }
     
     public void setDepartment(String department){
	 	this.department=department;
	 }

     public Long getDutyid(){
     	return dutyid;
     }
     
     public void setDutyid(Long dutyid){
	 	this.dutyid=dutyid;
	 }

     public Long getInfoid(){
     	return infoid;
     }
     
     public void setInfoid(Long infoid){
	 	this.infoid=infoid;
	 }

     public Integer getState(){
     	return state;
     }
     
     public void setState(Integer state){
	 	this.state=state;
	 }

     public String getToken(){
     	return token;
     }
     
     public void setToken(String token){
	 	this.token=token;
	 }

     public Integer getRole(){
     	return role;
     }
     
     public void setRole(Integer role){
	 	this.role=role;
	 }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
	@Override
    public String toString() {
        return "UserData{" +
                	"id=" + id +
                	"name=" + name +
                	"password=" + password +
                	"department=" + department +
                	"dutyid=" + dutyid +
                	"infoid=" + infoid +
                	"state=" + state +
                	"token=" + token +
                	"role=" + role +
                '}';
    }
}