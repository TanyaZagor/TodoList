package ru.zagorodnikova.tm.command.system;

import ru.zagorodnikova.tm.api.ServiceLocator;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.util.Version;

public class AboutCommand extends AbstractCommand {
    public AboutCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String command() {
        return "about";
    }

    @Override
    public String description() {
        return "about program";
    }

    @Override
    public void execute() {
        System.out.println(Version.getManifestInfo());
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
