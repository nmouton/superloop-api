package com.nmouton.superloopapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmouton.superloopapi.entities.ToDo;
import com.nmouton.superloopapi.repository.TodoStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class TestToDoController {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ObjectMapper mapper;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testToDOControllerShouldReturn() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/todos").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testAddToDo() throws Exception {

        ToDo testToDo = ToDo.create()
                .setDueDate(LocalDate.now())
                .setDescription("Test to make sure add functionality works")
                .setName("Test Add")
                .setStatus(ToDo.Status.Pending);

        String toDoJson = mapper.writerFor(ToDo.class).writeValueAsString(testToDo);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/todo").contentType(MediaType.APPLICATION_JSON).content(toDoJson);
        mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$..name").value("Test Add"))
                .andExpect(jsonPath("$..description").value("Test to make sure add functionality works"))
                .andExpect(jsonPath("$..dueDate").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$..status").value("Pending"));
    }

}
