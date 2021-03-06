package com.nmouton.superloopapi.entities;

import java.time.LocalDate;
import java.util.Objects;

public class ToDo {

    public enum Status { Pending, Done }

    private String name;
    private String description;
    private LocalDate dueDate;
    private Status status;

    public ToDo() {
    }

    public static ToDo create(){
        return new ToDo();
    }

    public String getName() {
        return name;
    }

    public ToDo setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ToDo setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public ToDo setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public ToDo setStatus(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return Objects.equals(name, toDo.name) &&
                Objects.equals(description, toDo.description) &&
                Objects.equals(dueDate, toDo.dueDate) &&
                status == toDo.status;
    }
}
