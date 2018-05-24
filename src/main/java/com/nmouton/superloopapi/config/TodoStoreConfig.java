package com.nmouton.superloopapi.config;


import com.nmouton.superloopapi.entities.ToDo;
import com.nmouton.superloopapi.repository.TodoStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

//A config file to set any defaults and inject the TodoStore
@Configuration
public class TodoStoreConfig {

    @Bean
    public TodoStore toDoStore() {
        return new TodoStore();
    }

    @Bean
    public HashMap<String, ToDo> toDos () {
        return new HashMap<String, ToDo>();
    }
}
