package Entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Task {

    private Integer id;
    private String name;
    private String description;
    private Date  dateFinish;

    public Task(Integer id, String name, String  description, Date dateFinish) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateFinish = dateFinish;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public Date getDateFinish() {
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

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }


    @Override
    public String toString() {
        return " Task id: " + id + ", Name: " + name + ", Description: " + description + ", Date: " + dateFinish;
    }
}
