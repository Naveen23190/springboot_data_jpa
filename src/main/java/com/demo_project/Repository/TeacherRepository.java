package com.demo_project.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo_project.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> 
{
	//Explicitly Using SQL Query To Insert
	@Transactional
	@Modifying
	@Query(value = "INSERT into Teacher (id,name,age) VALUES (:id,:name,:age)",nativeQuery = true)
	int saveTeacher(int id , String name , int age);
	// It Will Return 1-> If Data Is Saved
	
	
	
	//@Query("SELECT t FROM Teacher t WHERE t.age = :age") // Hibernate Query
	@Query(value = "Select * from Teacher Where age = ?1",nativeQuery = true) //SQL Query
	public List<Teacher> findAllByAge(@Param("age") int age);
	
	@Query("SELECT t FROM Teacher t WHERE t.age <= :age")
	List<Teacher> findAllByAgeLessThanEqual(@Param("age") int age);
	
	//Count The No.of Teachers
	@Query(value = "SELECT COUNT(*) FROM Teacher ",nativeQuery = true)
	int countTeacher();
	
	
	//boolean existByName(String name);
	
	
	
	
}
