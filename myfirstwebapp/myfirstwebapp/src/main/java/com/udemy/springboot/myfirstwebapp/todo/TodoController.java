package com.udemy.springboot.myfirstwebapp.todo;


import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//Commenting @Controller because everything copied to TodoControllerJpa (watch video-130)
//@Controller
@SessionAttributes("name")
public class TodoController {

	private TodoService todoservice;
	

	public TodoController(TodoService todoservice) {
		super();
		this.todoservice = todoservice;
	}
	
	@RequestMapping("todo")
	public String listAllTodos(ModelMap model) {
		String username=getLoggedInUserName(model);
		List<Todo> todo = todoservice.findByUserName(username);
		model.addAttribute("todo", todo);
		return "list-todos";
	}

	
	@RequestMapping(value="add-todo",method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username=getLoggedInUserName(model);
		Todo todo=new Todo(0,username,"",LocalDate.now().plusYears(1),false);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="add-todo",method=RequestMethod.POST)
	public String addNewTodo(ModelMap model,@Valid Todo todo,BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		
		String username=getLoggedInUserName(model);
		todoservice.addTodo(username, todo.getDescription(),todo.getDate(), false);
		return "redirect:todo";
	}
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		//Delete todo
		todoservice.deleteById(id);
		return "redirect:todo";
		
	}
	@RequestMapping(value="update-todo",method=RequestMethod.GET )
	public String showUpdateTodo(@RequestParam int id,ModelMap model) {
		Todo todo=todoservice.findById(id);
		model.addAttribute("todo",todo);
		return "todo";
		
	}
	@RequestMapping(value="update-todo",method=RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid Todo todo,BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		String username=getLoggedInUserName(model);
		todo.setUsername(username);
		todoservice.updateTodo(todo);
		return "redirect:todo";
	}
	
	//getting loggedInUserName
	private String getLoggedInUserName(ModelMap model) {
		Authentication authentication=
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
