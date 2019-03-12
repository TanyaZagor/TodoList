package ru.zagorodnikova.tm;


import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.project.*;
import ru.zagorodnikova.tm.command.system.AboutCommand;
import ru.zagorodnikova.tm.command.system.HelpCommand;
import ru.zagorodnikova.tm.command.task.*;
import ru.zagorodnikova.tm.command.user.*;

public class App {
    private static final Class[] commandClasses = new Class[] {
            ProjectClearCommand.class,
            ProjectCreateCommand.class,
            ProjectListCommand.class,
            ProjectFindOneCommand.class,
            ProjectRemoveCommand.class,
            ProjectUpdateCommand.class,
            AboutCommand.class,
            HelpCommand.class,
            TaskClearCommand.class,
            TaskCreateCommand.class,
            TaskFindOneCommand.class,
            TaskListCommand.class,
            TaskRemoveCommand.class,
            TaskUpdateCommand.class,
            TaskSortByStartCommand.class,
            UserUpdateCommand.class,
            UserShowCommand.class,
            UserSignOutCommand.class,
            UserSignUpCommand.class,
            UserSignInCommand.class,
            UserChangePasswordCommand.class,
            UserClearCommand.class,
            UserListCommand.class,
            UserRemoveCommand.class

    };

    public static void main(String[] args) {

        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.init(commandClasses);

    }


}
