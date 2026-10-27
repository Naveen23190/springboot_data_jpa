package com.demo_project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo_project.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer>
{

}
