package ru.zagorodnikova.tm.command.system;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.util.VersionUtil;

@Component
public class AboutCommand extends AbstractCommand {

    @NotNull
    @Override
    public String command() {
        return "about";
    }

    @NotNull
    @Override
    public String description() {
        return "about program";
    }

    @Override
    public void execute() throws Exception {
        System.out.println(VersionUtil.getManifestInfo());
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
