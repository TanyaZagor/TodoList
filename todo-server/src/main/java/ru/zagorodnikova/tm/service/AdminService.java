package ru.zagorodnikova.tm.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zagorodnikova.tm.api.service.IAdminService;
import ru.zagorodnikova.tm.api.service.IUserService;

@Service
@NoArgsConstructor
public class AdminService implements IAdminService {

    @Autowired
    private IUserService userService;

    public void removeAllUsers() {
        userService.removeAll();
    }
}
