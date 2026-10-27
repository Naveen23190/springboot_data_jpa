package com.demo_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo_project.Repository.EngineRepository;
import com.demo_project.entity.Engine;


@RestController
@RequestMapping("/engine")
public class EngineController 
{
	@Autowired
	EngineRepository engineRepository;
	
   @PostMapping("/add")
   public Engine add(@RequestBody Engine engine)
   {
	   return engineRepository.save(engine);
   }
   
}
