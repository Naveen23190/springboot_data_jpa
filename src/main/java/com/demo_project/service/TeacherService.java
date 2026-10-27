package com.demo_project.service;

import java.util.List;

import com.demo_project.dto.Teacherdto;
import com.demo_project.entity.Teacher;


public interface TeacherService 
{
   public Teacher saveTeacher(Teacher teacher);
   public List<Teacher> getAllTeacher();
   public Teacher updateTeacher(int id , String name , int age);
   public Teacher deleteTeacher(int id);
   public Teacher getTeacherById(int id);
   public Teacher copyTo(Teacherdto teacherdto);
}
