package com.demo_project.dto;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Teacherdto 
{
	@UniqueElements
	private int id;
	@NotBlank(message = "U Have To Pass Any Value")
	private String name;
	@Min(value = 18 , message = "Age Should be Greater Than 18")
	private Integer age;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	

}
