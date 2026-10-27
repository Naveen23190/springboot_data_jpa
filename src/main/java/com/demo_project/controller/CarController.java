package com.demo_project.controller;

import com.demo_project.Repository.CarRepository;
import com.demo_project.Repository.EngineRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo_project.entity.Car;
import com.demo_project.entity.Engine;



@Controller
@RequestMapping("/car")
public class CarController 
{
   @Autowired
   CarRepository carRepository;
   
   @Autowired
   EngineRepository engineRepository;

   @PostMapping("/add/{engineId}")
   public ResponseEntity add(@PathVariable int engineId , @RequestBody Car car)
   {
	   Engine engine = engineRepository.findById(engineId).orElseThrow(()->new RuntimeException("No Engine Found"));
	   car.setEngine(engine);
	   carRepository.save(car);
	   engine.setCar(car);
	   return ResponseEntity.status(HttpStatus.OK).body(car);
	   
   }
}
