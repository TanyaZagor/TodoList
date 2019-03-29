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

import javax.enterprise.inject.Default;
import java.lang.Exception;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Default
@NoArgsConstructor
public class Bootstrap implements ServiceLocator {

    @NotNull private final Map<String, AbstractCommand> commands = new HashMap<>();
    @NotNull private final ProjectEndpointService  projectEndpointService = new ProjectEndpointService();
    @NotNull private final TaskEndpointService taskEndpointService = new TaskEndpointService();
    @NotNull private final UserEndpointService userEndpointService= new UserEndpointService();
    @NotNull private final SessionEndpointService sessionEndpointService = new SessionEndpointService();
    @NotNull private final AdminEndpointService adminEndpointService = new AdminEndpointService();
    @NotNull private final ProjectEndpoint projectService = projectEndpointService.getProjectEndpointPort();
    @NotNull private final TaskEndpoint taskService = taskEndpointService.getTaskEndpointPort();
    @NotNull private final UserEndpoint userService = userEndpointService.getUserEndpointPort();
    @NotNull private final SessionEndpoint sessionService = sessionEndpointService.getSessionEndpointPort();
    @NotNull private final AdminEndpoint adminService = adminEndpointService.getAdminEndpointPort();

    @NotNull private final TerminalService terminalService = new TerminalService(this);
    @Nullable private Session session;


    public void init(@NotNull Class[] commandClasses) {
        this.initCommands(this, commandClasses);
        this.start();
    }

    private void start() {
        terminalService.start();
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
