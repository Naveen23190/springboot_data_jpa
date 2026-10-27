package com.demo_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Engine 
{
   @Id
   private int id;
   private String type;
   private int cc;
   @OneToOne(mappedBy = "engine")
   @JsonIgnore
   private Car car;
   
   public int getId() {
	return id;
   }
   public void setId(int id) {
	this.id = id;
   }
   public String getType() {
	return type;
   }
   public void setType(String type) {
	this.type = type;
   }
   public int getCc() {
	return cc;
   }
   public void setCc(int cc) {
	this.cc = cc;
   }
   public Car getCar() {
	return car;
   }
   public void setCar(Car car) {
	this.car = car;
   }
      
}
