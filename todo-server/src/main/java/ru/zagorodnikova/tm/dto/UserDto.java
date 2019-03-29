package ru.zagorodnikova.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.zagorodnikova.tm.entity.User;
import ru.zagorodnikova.tm.entity.enumeration.RoleType;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    @Nullable
    private String login;

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    private String email;

    @NotNull
    private RoleType roleType;

    public UserDto(@NotNull final User user){
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.roleType = user.getRoleType();
    }
}
