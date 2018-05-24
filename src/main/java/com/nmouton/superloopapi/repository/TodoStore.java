package com.nmouton.superloopapi.repository;

import com.nmouton.superloopapi.entities.ToDo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.UUID;

import java.util.HashMap;
import java.util.stream.Collectors;

//Here we define a simple HashMap to be initialized and injected into our context at startup
//Also provides abstraction from the HashMap Object through implementation of these accessors
public class TodoStore {

    private HashMap<String, ToDo> toDos;

    @Autowired
    public void setToDos(HashMap<String, ToDo> toDos) {
        this.toDos = toDos;
    }

    public HashMap<String, ToDo> getToDos() {
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

    //Make a new HashMap, stream the entry set and filter by status, put each line in the new map then return it
    public HashMap<String, ToDo> getToDosByStatus(ToDo.Status status) {
        HashMap<String, ToDo> result = new HashMap<>();
        toDos.entrySet()
            .stream()
            .filter(toDo -> status.equals(toDo.getValue().getStatus()))
            .forEach(entry -> result.put(entry.getKey(), entry.getValue()));
        return result;
    }


}