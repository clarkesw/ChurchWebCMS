/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;

import com.milford.churchcms.dao.Todo;
import com.milford.churchcms.service.TodoService;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class TodoController {
    
    @Autowired
    TodoService service;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
        
    @GetMapping("/list-todos")
    public String showTodo(ModelMap model){
        String username = getLoggedInName(model);
        model.put("todos", service.retrieveTodos(username));
        return "list-todos";
    }

    private String getLoggedInName(ModelMap model) {
        Collection<Object> values = model.values();
//        for(Object val: values){
//             System.out.println("++++++++ getLoggedInName" + val);
//        }
        return (String)model.get("user");
    }
 
    @GetMapping("/add-todos")
    public String showAddTodo(ModelMap model, @ModelAttribute("todo") Todo todo){     
        return "add-todo";
    }
    
    @PostMapping("/add-todos")
    public String addTodo(ModelMap model,@Valid @ModelAttribute("todo") Todo todo, BindingResult result){
        if(result.hasErrors())
            return "add-todo";
        service.addTodo(getLoggedInName(model), todo.getDesc(), new Date(), true);
        return "redirect:/list-todos";
    }
    
    @GetMapping("/delete-todo")
    public String deleteTodo(@RequestParam int id){
        service.deleteTodo(id);
        return "redirect:/list-todos";
    }
    
    @GetMapping("/update-todo")
    public String updateTodo(ModelMap model, @RequestParam int id){
        Todo todo = service.retrieveOneTodo(id);
        model.put("todo", todo);
        return "add-todo";
    }    
    
    @PostMapping("/update-todo")
    public String updateTodoPost(ModelMap model,@Valid @ModelAttribute("todo") Todo todo, BindingResult result){
        if(result.hasErrors())
            return "add-todo";
        
        todo.setUser(getLoggedInName(model));
        service.updateTodo(todo);
       
        return "redirect:/list-todos";
    }
}
