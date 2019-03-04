package ru.zagorodnikova.tm.Entity;


public class Task {

    private Integer id;
    private String name;
    private String description;
    private String dateStart;
    private String dateFinish;

    public Task(Integer id, String name, String  description, String dateStart, String dateFinish) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public String getDateFinish() {
        return dateFinish;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    @Override
    public String toString() {
        return " Task id: " + id + ", Name: " + name + ", Description: " + description + ", Date: " + dateFinish;
    }
}
