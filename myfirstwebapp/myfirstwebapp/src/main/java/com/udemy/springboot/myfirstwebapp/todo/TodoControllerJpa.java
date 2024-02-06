package com.udemy.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {

	
	private TodoRepository todoRepository;
	

	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		this.todoRepository=todoRepository;
	}
	
	@RequestMapping("todo")
	public String listAllTodos(ModelMap model) {
		String username=getLoggedInUserName(model);
		
		List<Todo> todo = todoRepository.findByUserName(username);
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
		todo.setUsername(username);
		todoRepository.save(todo);
		//todoService.addTodo(username, todo.getDescription(),todo.getDate(), todo.isDone());
		return "redirect:todo";
	}
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam long id) {
		//Delete todo
		
		//Todo todo=	todoRepository.findById(id).get();
		//todoRepository.delete(todo);
		//or we can directly delete it->
		todoRepository.deleteById(id);
	
		return "redirect:todo";
		
	}
	@RequestMapping(value="update-todo",method=RequestMethod.GET )
	public String showUpdateTodo(@RequestParam long id,ModelMap model) {
		Todo todo=todoRepository.findById(id).get();
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
		todoRepository.save(todo);
		return "redirect:todo";
	}
	
	
	
	//getting loggedInUserName
	private String getLoggedInUserName(ModelMap model) {
		Authentication authentication=
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
