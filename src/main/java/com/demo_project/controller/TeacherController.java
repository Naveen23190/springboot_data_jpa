package com.demo_project.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.demo_project.Response.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo_project.Repository.TeacherRepository;
import com.demo_project.dto.Teacherdto;
import com.demo_project.entity.Teacher;
import com.demo_project.service.TeacherService;
import com.demo_project.service.TeacherServiceImpl;

import jakarta.validation.Valid;

@RestController
public class  TeacherController
{

	@Autowired
	TeacherServiceImpl teacherServiceImpl;

	@Autowired
	TeacherService teacherService;

	@Autowired
	TeacherRepository teacherRepository;

	
    @GetMapping("/")
    public String form()
    {
    	return "Home Page";
    }
     
    @GetMapping("/welcome")
    public String welcome()
    {
    	int i = 1/0;
    	return "Welcome To Spring Boot";
    }
    
    //1. Add Data Into The DB
//    @PostMapping("/add")
//    public String add(@RequestBody Teacher teacher) 
//    {
//        if(teacherRepository.existsById(teacher.getId()))
//        {
//        	return "Data Already Exists";
//        }
//        else
//        {
//        	teacherService.saveTeacher(teacher);
//        	return "Data Saved";
//        }
//    }
    // Here , We Can't Return The Teacher Data , Becoz , in if block we are returing String Data
    //** For That We Can Use "ResponseEntity" -> It Will Return Both The Code , Body , Header
    // By Normal If We Got Response From Server Means Whether The Data Either Exists Or Not It Will Give 200 OK Response
    // In ResponseEntity We can Customize The Status Code & Status
    
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Teacher teacher)
    {
    	if(teacherRepository.existsById(teacher.getId()))
    	{
    		return ResponseEntity.status(HttpStatus.FOUND).body("Data Already Exists");
    	}
    	else
    	{
    		Teacher savedTeacher = teacherService.saveTeacher(teacher);
    		return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
    	}
    }
    
//    @PostMapping("/add")
//    public ResponseStructure<Teacher> saveTeacher(@RequestBody Teacher teacher)
//    {
//        Teacher tch = teacherRepository.save(teacher);
//        ResponseStructure<Teacher> res = new ResponseStructure<Teacher>();
//        res.setId(201);
//        res.setMsg("Saved");
//        res.setLocalDateTime(LocalDateTime.now());
//        res.setTeacher(tch);
//        return res;
//    }

	//2. Get All The Data From The DB  
    
    @GetMapping("/getAll")
    public List<Teacher> get()
    {
    	return teacherService.getAllTeacher();
    }
    
    //5. Get Teacher By Id
    
    @GetMapping("/get/{id}")
    public Teacher getById(@PathVariable int id) {

        return teacherService.getTeacherById(id);
    }
    
    //3. Update Teacher
    
    @PutMapping("/update")
    public Teacher update(@RequestParam int id , @RequestParam(required = false) String name , @RequestParam int age )
    {
    	return teacherService.updateTeacher(id , name , age);
    }
    
    //4. Delete Teacher
    
    @DeleteMapping("/delete/{id}")
    public Teacher delete(@PathVariable int id)
    {
    	return teacherService.deleteTeacher(id);
    }
    
    //5. Find All By Age   
    
    @GetMapping("/findAllByAge/{age}")
    public List<Teacher> findAllByAge(@PathVariable int age)
    {
    	return teacherRepository.findAllByAge(age);
    }
    
    //6. Find All By Less Than or Equals 
    
    @GetMapping("/findAllByAgeLessThanEqual/{age}")
    public List<Teacher> findAllByAgeLessThanEqual(@PathVariable int age)
    {
    	return teacherRepository.findAllByAgeLessThanEqual(age);
    }
    
    //7. Save Teacher By Using The *SQL Query*
    
    @PostMapping("/save")
    public int save(@RequestBody Teacher teacher)
    {
    	return teacherRepository.saveTeacher(teacher.getId(),teacher.getName(), teacher.getAge());
    }
    
    //8. Count Teachers
    
    @GetMapping("/count")
    public int count()
    {
    	return teacherRepository.countTeacher();
    }
    
    //9. Save Teacher By Using The Validation
    
    @PostMapping("/saveTeacher")
    public Teacher saveTeacher(@Valid @RequestBody Teacherdto teacherdto)
    {
    	Teacher teacher = teacherServiceImpl.copyTo(teacherdto);
    	return teacherService.saveTeacher(teacher);
    }
    
    //10. Pagination
    
    @GetMapping("/getByPage")
    public Page<Teacher> getByPage(@RequestParam int pageNo , @RequestParam int size)
    {
    	
    	Pageable pageable = PageRequest.of(pageNo, size);
    	return teacherRepository.findAll(pageable);
    }
    
    
    //11. Dynamic Fetch (Pagination , Sorting)
    
    @GetMapping("/queryBy")
    public Page<Teacher> fetch(
            @RequestParam("name") String name,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {

        Teacher teacher = new Teacher();
        teacher.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT)
                .withIgnorePaths("id", "age");

        Example<Teacher> example = Example.of(teacher, matcher);

        Sort sort = direction.equalsIgnoreCase("ASC")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNo, size, sort);

        return teacherRepository.findAll(example, pageable);
    }
}
