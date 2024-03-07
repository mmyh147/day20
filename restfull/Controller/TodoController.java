package com.example.restfull.Controller;

import com.example.restfull.API.ApiResponse;
import com.example.restfull.Model.Todo;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {


    ArrayList<Todo> todos = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Todo> getTodos(){

        return todos;
    }

    @PostMapping("/post")
    public ApiResponse addTodo(@RequestBody Todo todo){

        todos.add(todo);
        return new ApiResponse("successfully added the Task");
    }

    @PutMapping("update/{index}")
    public ApiResponse updateTodo(@PathVariable int index, @RequestBody Todo todo){
        if (todos.size() - 1 >= index){

            todos.set(index, todo);
            return new ApiResponse("Task updated successfully");
        }else{
            return new ApiResponse("There no ID with number : " + index);
        }

    }

    @PutMapping("updateStatus/{index}")
    public ApiResponse updateStatus(@PathVariable int index){
        if(todos.size() - 1 >= index ){

            if(!todos.get(index).isStatus()){

                todos.get(index).setStatus(true);
                return new ApiResponse("The task : " + todos.get(index).getTitle() + " is updated to done");
            }else{
                return new ApiResponse("The task already done ");
            }

        }else{
            return new ApiResponse("There are no index with number : " + index);
        }
    }

    @DeleteMapping("delete/{index}")
    public ApiResponse deleteTodo(@PathVariable int index){
        if(todos.size() - 1 >= index){

            todos.remove(index);
            return new ApiResponse("Task removed successfully");
        }else{
            return new ApiResponse("there no ID with number: " + index);
        }
    }

    @GetMapping("search/{searchByTitle}")
    public Todo getByTitle(@PathVariable String searchByTitle){
        return todos.stream().filter(todo -> todo.getTitle().equals(searchByTitle))
                .findFirst()
                .orElse(null);
    }
}
