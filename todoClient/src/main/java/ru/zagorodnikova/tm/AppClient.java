package ru.zagorodnikova.tm;


import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.bootstrap.Bootstrap;
import ru.zagorodnikova.tm.command.admin.AdminRemoveUsersCommand;
import ru.zagorodnikova.tm.command.data.*;
import ru.zagorodnikova.tm.command.project.*;
import ru.zagorodnikova.tm.command.system.AboutCommand;
import ru.zagorodnikova.tm.command.system.HelpCommand;
import ru.zagorodnikova.tm.command.task.*;
import ru.zagorodnikova.tm.command.user.*;

public class AppClient {
    private static final Class[] commandClasses = new Class[] {
            ProjectClearCommand.class,
            ProjectCreateCommand.class,
            ProjectListCommand.class,
            ProjectFindOneCommand.class,
            ProjectRemoveCommand.class,
            ProjectUpdateCommand.class,
            ProjectSortByCreateCommand.class,
            ProjectSortByStartCommand.class,
            ProjectSortByFinishCommand.class,
            ProjectSortByStatusCommand.class,
            AboutCommand.class,
            HelpCommand.class,
            SaveCommand.class,
            SaveToXmlJaxbCommand.class,
            SaveToJsonJaxbCommand.class,
            SaveToXmlCommand.class,
            SaveToJsonCommand.class,
            LoadCommand.class,
            LoadFromXmlJaxbCommand.class,
            LoadFromJsonJaxbCommand.class,
            LoadFromXmlCommand.class,
            LoadFromJsonCommand.class,
            TaskClearCommand.class,
            TaskCreateCommand.class,
            TaskFindOneCommand.class,
            TaskListCommand.class,
            TaskRemoveCommand.class,
            TaskUpdateCommand.class,
            TaskSortByStartCommand.class,
            TaskSortByCreateCommand.class,
            TaskSortByFinishCommand.class,
            TaskSortByStatusCommand.class,
            UserUpdateCommand.class,
            UserShowCommand.class,
            UserSignOutCommand.class,
            UserSignUpCommand.class,
            UserSignInCommand.class,
            UserChangePasswordCommand.class,
            UserListCommand.class,
            UserRemoveCommand.class,
            AdminRemoveUsersCommand.class

    };

    public static void main(String[] args) {
        System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level","INFO");
        System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.init(commandClasses);

    }


}
