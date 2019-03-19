package ru.zagorodnikova.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.ServiceLocator;
import ru.zagorodnikova.tm.TerminalService;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.*;
import ru.zagorodnikova.tm.endpoint.ProjectEndpointService;
import ru.zagorodnikova.tm.endpoint.TaskEndpointService;
import ru.zagorodnikova.tm.endpoint.UserEndpointService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Bootstrap implements ServiceLocator {

    @NotNull private final Map<String, AbstractCommand> commands = new HashMap<>();
    @NotNull private final ProjectEndpointService  projectEndpointService = new ProjectEndpointService();
    @NotNull private final TaskEndpointService taskEndpointService = new TaskEndpointService();
    @NotNull private final UserEndpointService userEndpointService= new UserEndpointService();
    @NotNull private final SessionEndpointService sessionEndpointService = new SessionEndpointService();
    @NotNull private final ProjectEndpoint projectService = projectEndpointService.getProjectEndpointPort();
    @NotNull private final TaskEndpoint taskService = taskEndpointService.getTaskEndpointPort();
    @NotNull private final UserEndpoint userService = userEndpointService.getUserEndpointPort();
    @NotNull private final SessionEndpoint sessionEndpoint = sessionEndpointService.getSessionEndpointPort();

    @NotNull private final TerminalService terminalService = new TerminalService(this);
    @Nullable private Session session;


    public void init(@NotNull Class[] commandClasses) {
        this.initCommands(this, commandClasses);
        //this.initProjectsAndUsers();
        this.start();
    }

    private void start() {
        terminalService.start();
    }

    @NotNull
    @Override
    public SessionEndpoint getSessionService() {
        return sessionEndpoint;
    }

    public void execute(@Nullable String command) throws IOException, JAXBException, ClassNotFoundException {
        if (command == null || command.isEmpty()) return;
        final AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null) return;
        if (abstractCommand.isSecure()) {
            if (isAuth()) {
                abstractCommand.execute();
            }
        } else {
            abstractCommand.execute();
        }

    }


    private void initProjectsAndUsers() {
//        final User user1 = userService.signUp("login", "password", "first name", "last name", "email@email.ru");
//        final User user2 = userService.signUp("login2", "password2", "first name", "last name", "email@email.ru");

//        final Project project1 = projectService.persist(user1.getId(), "Project1", "Description1", "20.02.2019", "20.05.2019");
//        final Project project2 = projectService.persist(user2.getId(), "Project2", "Description2", "20.05.2019", "20.06.2019");
//        final Project project3 = projectService.persist(user2.getId(), "Project1", "Description1", "20.02.2016", "20.05.2020");
//        taskService.persist(project1.getUserId(), project1.getName(), "task1", "des1", "20.02.2012", "20.02.2013");
//        taskService.persist(project1.getUserId(), project1.getName(), "task2", "des2", "20.02.2010", "20.02.2011");
//        taskService.persist(project2.getUserId(), project2.getName(), "task3", "des3", "20.02.2013", "20.02.2014");
//        taskService.persist(project2.getUserId(), project2.getName(), "task3", "des3", "20.02.2013", "20.02.2014");
//        taskService.persist(project3.getUserId(), project3.getName(), "task1", "des1", "20.02.2013", "20.02.2014");
//        taskService.persist(project3.getUserId(), project3.getName(), "task2", "des2", "20.02.2013", "20.02.2014");

    }

    private void initCommands(@NotNull Bootstrap bootstrap,@NotNull Class[] commandClasses) {

        addCommand(commandClasses, bootstrap);

    }

    private void addCommand(@NotNull Class[] commandClasses, @NotNull ServiceLocator bootstrap){
        for (Class commandClass : commandClasses) {
            if (commandClass.getSuperclass().equals(AbstractCommand.class)) {
                AbstractCommand abstractCommand = null;
                try {
                    abstractCommand = (AbstractCommand) commandClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (abstractCommand != null) {
                    abstractCommand.setServiceLocator(bootstrap);
                    commands.put(abstractCommand.command(), abstractCommand);
                }
            }
        }
    }

    private boolean isAuth() {

        return session != null;
    }

}
