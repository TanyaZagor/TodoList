import Entity.Project;
import Entity.Task;
import Repository.ProjectRepository;
import Repository.TaskRepository;

import java.util.Scanner;

public class Main {
    private static final String COMMAND_HELP= "help";
    private static final String COMMAND_DELETE_TASK = "delete task";
    private static final String COMMAND_DELETE_PROJECT = "delete project";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_DELETE_ALL = "delete all tasks";
    private static final String COMMAND_PRINT = "print";
    private static final String COMMAND_UPDATE = "update";

    public static void main(String[] args) {

        TaskRepository tasks = new TaskRepository();
        TaskRepository tasks2 = new TaskRepository();
        String command;
        String projectName;
        String taskName;
        String description;
        String dateFinish;

        Task task1 = new Task("Task1", "description1", "01.05.2019");
        Task task2 = new Task("Task2", "description2", "21.03.2019");
        Task task3 = new Task("Task3", "description3", "22.03.2019");
        Task task4 = new Task("Task4", "description4", "23.03.2019");
        Task task5 = new Task("Task5", "description5", "24.03.2019");

        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);
        tasks2.addTask(task4);
        tasks2.addTask(task5);

        ProjectRepository projectRepository = new ProjectRepository();
        Project project1 = new Project("Project1", tasks);
        Project project2 = new Project("Project2", tasks2);
        projectRepository.addProject(project1);
        projectRepository.addProject(project2);

        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        if (COMMAND_HELP.equals(command)) {
//            projectRepository.print();
//            System.out.println("Commands \nHELP: help" +
//                    "\nADD TASK: add_project name_task name_description_dateFinish(dd.mm.yyyy)" +
//                    "\nDELETE TASK: delete_project name_task name" +
//                    "\nEXIT: exit" +
//                    "\nDELETE ALL: delete all tasks_project name" +
//                    "\nPRINT TASKS: print" +
//                    "\nUPDATE: update_project name_task name_what to update_new");
            projectRepository.print();
            System.out.println("Commands \nHELP: help" +
                    "\nADD TASK: add_project name_task name_description_dateFinish(dd.mm.yyyy)" +
                    "\nDELETE TASK: delete task_project name_task name" +
                    "\nDELETE PROJECT: delete project_project name" +
                    "\nEXIT: exit" +
                    "\nDELETE ALL: delete all tasks_project name" +
                    "\nPRINT TASKS: print" +
                    "\nUPDATE: update_task name_what to update_new");
        }

        while (true) {
            command = in.nextLine();
            String[] str = command.split("_");
            command = str[0];

            if (str.length > 1) {
                if (COMMAND_DELETE_TASK.equals(command)) {
                    projectName = str[1];
                    taskName = str[2];
                    projectRepository.deleteTask(projectName, taskName);
                    projectRepository.print();
                } else if (COMMAND_ADD.equals(command)) {
                    if (str.length == 5) {
                        projectName = str[1];
                        taskName = str[2];
                        description = str[3];
                        dateFinish = str[4];
                        Task task = new Task(taskName, description, dateFinish);
                        projectRepository.addTask(projectName, task);
                        tasks.print();
                    } else {
                        System.out.println("Not enough info");
                    }
                } else if (COMMAND_UPDATE.equals(command)) {
                    tasks.update(str[1], str[2], str[3]);
                    tasks.print();
                }else if (COMMAND_DELETE_ALL.equals(command)) {
                    projectName = str[1];
                    projectRepository.deleteAllTasks(projectName);
                    projectRepository.print();
                } else if (COMMAND_DELETE_PROJECT.equals(command)) {
                    projectName = str[1];
                    projectRepository.deleteProject(projectName);
                }
            } else if (COMMAND_PRINT.equals(command)) {
                projectRepository.print();
            } else if (COMMAND_EXIT.equals(command)) {
                System.out.println("Exit");
                break;
            } else {
                System.out.println("Wrong command");
            }
        }

    }


}
