package com.udemy.springboot.myfirstwebapp.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TodoRepository extends JpaRepository<Todo, Long>{
	
	@Query("SELECT t FROM Todo t WHERE t.username = :username")
	public List<Todo> findByUserName(String username);
	

}
