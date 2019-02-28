package Entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Task {

    private String name;
    private String description;
    private Date  dateFinish;

    public Task(String name, String  description, String dateFinish) {
        this.name = name;
        this.description = description;
        setDateFinish(dateFinish);
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public void update(String upd, String newData) {
        if ("Description".equals(upd)) {
            setDescription(newData);
        } else if ("Date".equals(upd)) {
            setDateFinish(newData);
        } else if ("Name".equals(upd)) {
            setName(newData);
        }
    }

    public void setDateFinish(String dateFinish) {
        DateFormat dateFormat = new SimpleDateFormat("dd.M.yyyy", Locale.ENGLISH);
        try {
            this.dateFinish = dateFormat.parse(dateFinish);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Description: " + description + ", Date: " + dateFinish;
    }
}
