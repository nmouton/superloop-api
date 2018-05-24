package com.nmouton.superloopapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmouton.superloopapi.entities.ToDo;
import com.nmouton.superloopapi.repository.TodoStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

@Controller
@RequestMapping(path="/api")
@CrossOrigin
public class ToDoController {

    @Autowired
    TodoStore toDoStore;

    @Autowired
    ObjectMapper objectMapper;

    private final Logger logger = LoggerFactory.getLogger(ToDoController.class);

    @GetMapping(path = "todos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<HashMap<String, ToDo>> getTodos(){
        logger.trace("received request for list of ToDos, TodoStore: {}", toDoStore);
        return new ResponseEntity<>(toDoStore.getToDos(), HttpStatus.OK);
    }

    @GetMapping(path = "todos/{status}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<HashMap<String, ToDo>> getTodosFilterByStatus(@PathVariable("status") ToDo.Status status){
        logger.trace("received request for list of ToDos filtered by {}, TodoStore: {}", status, toDoStore);
        return new ResponseEntity<>(toDoStore.getToDosByStatus(status), HttpStatus.OK);
    }

    @GetMapping(path = "todo/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  ResponseEntity<ToDo> getToDo(@PathVariable("id") String toDoId) {
        logger.trace("received request to get ToDo: {}", toDoId);
        return new ResponseEntity<>(toDoStore.getToDoById(toDoId), HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "todo",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  ResponseEntity<HashMap<String, ToDo>> addToDo(@RequestBody ToDo todo) {
        toDoStore.addToDo(todo);
        return new ResponseEntity<>(toDoStore.getToDos(), HttpStatus.OK);
    }

    @PutMapping(path = "todo/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  ResponseEntity<HashMap<String, ToDo>> updateToDoById(@PathVariable("id") String toDoId, @RequestBody ToDo toDo) {
        logger.trace("received request to update ToDo: {} {}", toDoId, toDo);
        toDoStore.updateToDoById(toDoId,toDo);
        return new ResponseEntity<>(toDoStore.getToDos(), HttpStatus.OK);
    }

    @DeleteMapping(path = "todo/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  ResponseEntity<ToDo> deleteToDoById(@PathVariable("id") String toDoId) {
        logger.trace("received request to delete ToDo: {}", toDoId);
        return new ResponseEntity<>(toDoStore.removeToDoById(toDoId), HttpStatus.OK);
    }

    @DeleteMapping(path = "todos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  ResponseEntity deleteAllToDos() {
        logger.trace("received request to delete all ToDos");
        toDoStore.setToDos(new HashMap<>());
        return new ResponseEntity(HttpStatus.OK);
    }

}
