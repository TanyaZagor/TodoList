package Repository;

import Entity.Task;

import java.util.HashMap;

public class TaskRepository {
    private HashMap<String, Task> tasks;

    public TaskRepository() {
        tasks = new HashMap<>();
    }

    public boolean addTask(Task task) {
        if (!tasks.containsKey(task.getName())) {
            tasks.put(task.getName(), task);
            return true;
        }
        return false;
    }

    public boolean hasKey (String key) {
        return tasks.containsKey(key);
    }

    public boolean deleteTask(String key) {
        if (hasKey(key)) {
            tasks.remove(key);
            return true;
        }
        return false;
    }

    public void deleteAll() {
        tasks.clear();
    }

    public void update(String name, String upd, String newData) {
        tasks.get(name).update(upd, newData);
    }

    public HashMap<String, Task> getTasks() {
        return tasks;
    }

    public void print() {
        tasks.forEach((key, value) -> System.out.println(value));
    }


}
