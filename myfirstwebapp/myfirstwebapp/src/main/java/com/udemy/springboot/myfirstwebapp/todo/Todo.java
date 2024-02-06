package com.udemy.springboot.myfirstwebapp.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {
	@Id
	@GeneratedValue
	private long id;
	private String username;
	
	@Size(min=10,message = "Enter atleast 10 characters")
	private String description;
	private LocalDate date;
	private boolean done;
	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Todo(long id, String username, String description, LocalDate date, boolean done) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.date = date;
		this.done = done;
	}
	public long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getDescription() {
		return description;
	}
	public LocalDate getDate() {
		return date;
	}
	public boolean isDone() {
		return done;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", date=" + date
				+ ", done=" + done + "]";
	}

}
