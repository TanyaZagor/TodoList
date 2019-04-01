package ru.zagorodnikova.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.*;
import ru.zagorodnikova.tm.service.TerminalService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.Exception;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@ApplicationScoped
public class Bootstrap implements ServiceLocator {

    @NotNull private final Map<String, AbstractCommand> commands = new HashMap<>();

    @Inject
    private ProjectEndpoint projectService;

    @Inject
    private TaskEndpoint taskService;

    @Inject
    private UserEndpoint userService;

    @Inject
    private SessionEndpoint sessionService;

    @Inject
    private AdminEndpoint adminService;

    @Inject
    private TerminalService terminalService;

    @Nullable private Session session;

    public void init(@NotNull Class[] commandClasses) {
        this.initCommands(this, commandClasses);
        this.start();
    }

    private void start() {
        System.out.println("Welcome");
        @NotNull String command = "";
        while (!"exit".equals(command)) {
            System.out.println("Command: ");
            command = terminalService.nextLine();
            try {
                execute(command);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("wrong data");
            } catch (Exception e) {
                System.out.println("exception");
            }
        }
    }

    public void execute(@Nullable String command) throws Exception {
        if (command == null || command.isEmpty()) return;
        @NotNull final AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null) return;
        if (abstractCommand.isSecure()) {
            if (isAuth()) {
                abstractCommand.execute();
            }
        }
        else {
            abstractCommand.execute();
        }
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
                } catch ( InstantiationException | IllegalAccessException e) {
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
