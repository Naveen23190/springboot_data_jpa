package com.demo_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo_project.Repository.TeacherRepository;
import com.demo_project.dto.Teacherdto;
import com.demo_project.entity.Teacher;

@Service
public class TeacherServiceImpl implements TeacherService
{
	
	@Autowired
	TeacherRepository teacherRepository;
	
	public Teacher copyTo(Teacherdto teacherdto)
	{
		Teacher teacher = new Teacher();
		teacher.setId(teacherdto.getId());
		teacher.setName(teacherdto.getName());
		teacher.setAge(teacherdto.getAge());
		return teacher;
	}

	@Override
	public Teacher saveTeacher(Teacher teacher) 
	{
		return teacherRepository.save(teacher);
	}

	@Override 
	public List<Teacher> getAllTeacher() 
	{
		return teacherRepository.findAll();
	}

	@Override
	public Teacher updateTeacher(int id , String name , int age) 
	{
	    Teacher teacher = teacherRepository.findById(id).orElseThrow(()->new RuntimeException("Data Not Found"));
	    teacher.setName(name);
	    teacher.setAge(age);
	    return teacherRepository.save(teacher);
	    
	}

	@Override
	public Teacher deleteTeacher(int id) 
	{
		Teacher teacher = teacherRepository.findById(id).orElseThrow(()->new RuntimeException("Invalid Teacher With The ID : "+id));
	    teacherRepository.delete(teacher);
	    return teacher;
	}

	@Override
	public Teacher getTeacherById(int id) 
	{
		return teacherRepository.findById(id).orElseThrow(()->new RuntimeException("No Teacher Found With ID : "+id));
	}
		
}
