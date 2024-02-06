package com.udemy.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
	public static List<Todo> todos= new ArrayList<>();
	
	private static int todosCount=0;
	
	static {
		todos.add(new Todo(++todosCount,"Ayush","Java FullStack",
				LocalDate.now().plusYears(1),true));
		todos.add(new Todo(++todosCount,"Radhika","MBA",
				LocalDate.now().plusYears(2),true));
		todos.add(new Todo(++todosCount,"Priya","Service",
				LocalDate.now().plusYears(3),false));
		todos.add(new Todo(++todosCount,"Deepika","ServiceNow",
				LocalDate.now().plusYears(4),true));
	}
	public List<Todo> findByUserName(String username){
		Predicate<? super Todo> predicate= todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
		
	}
	public void addTodo(String username,String description,LocalDate date,boolean done) {
		Todo todo=new Todo(++todosCount,username,description,date,done);
		todos.add(todo);
	}
	public void deleteById(long id) {
		//todo.getId()==id
		
		Predicate<? super Todo> predicate= todo -> todo.getId()==id;
		todos.removeIf(predicate);
	}
	public Todo findById(int id) {
		
		Predicate<? super Todo> predicate= todo -> todo.getId()==id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
		
	}
	

}
