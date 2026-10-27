package com.demo_project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo_project.entity.Engine;

public interface EngineRepository extends JpaRepository<Engine, Integer> 
{

}
