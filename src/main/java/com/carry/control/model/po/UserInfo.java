package com.carry.control.model.po;


import java.io.Serializable;
import java.sql.Timestamp;

public class UserInfo  implements Serializable {
     private Long id;
     private String name;
     private Integer sex;
     private Timestamp birthdate;
     private Long phone;
     private String mail;


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

     public Integer getSex(){
     	return sex;
     }
     
     public void setSex(Integer sex){
	 	this.sex=sex;
	 }

     public Timestamp getBirthdate(){
     	return birthdate;
     }
     
     public void setBirthdate(Timestamp birthdate){
	 	this.birthdate=birthdate;
	 }

     public Long getPhone(){
     	return phone;
     }
     
     public void setPhone(Long phone){
	 	this.phone=phone;
	 }

     public String getMail(){
     	return mail;
     }
     
     public void setMail(String mail){
	 	this.mail=mail;
	 }
	@Override
    public String toString() {
        return "UserInfo{" +
                	"id=" + id +
                	"name=" + name +
                	"sex=" + sex +
                	"birthdate=" + birthdate +
                	"phone=" + phone +
                	"mail=" + mail +
                '}';
    }
}