package ru.zagorodnikova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.endpoint.UserDto;
import ru.zagorodnikova.tm.endpoint.UserEndpoint;

import java.util.List;

@Component
public class UserListCommand extends AbstractCommand {
    @Autowired
    private UserEndpoint userService;

    @Autowired
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String command() {
        return "user list";
    }

    @NotNull
    @Override
    public String description() {
        return "command to show all users";
    }

    @Override
    public void execute() throws Exception {
        @Nullable final List<UserDto> list = userService.findAllUsers(serviceLocator.getSession());
        if (!(list == null || list.isEmpty())) {
            list.forEach(user -> System.out.println(user.getLogin()));
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
