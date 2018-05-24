package com.nmouton.superloopapi.repository;

import com.nmouton.superloopapi.entities.ToDo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import java.util.HashMap;

//Here we define a simple HashMap to be initialized and injected into our context at startup
//Also provides abstraction from the HashMap Object through implementation of its own accessor
public class TodoStore {

    private HashMap<String, ToDo> toDos;

    @Autowired
    public void setToDos(HashMap<String, ToDo> toDos) {
        this.toDos = toDos;
    }

    public HashMap<String, ToDo> getToDos () {
        return toDos;
    }

    public void addToDo(ToDo toDo) {
        toDos.put(UUID.randomUUID().toString(),toDo);
    }

    public ToDo getToDoById(String id) {
        return toDos.get(id);
    }

    public ToDo removeToDoById(String id) {
        return toDos.remove(id);
    }

    public ToDo updateToDoById(String id, ToDo toDo) {
        return toDos.replace(id, toDo);
    }

}