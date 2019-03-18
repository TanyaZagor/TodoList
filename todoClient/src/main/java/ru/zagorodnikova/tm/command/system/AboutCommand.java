package ru.zagorodnikova.tm.command.system;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;

public class AboutCommand extends AbstractCommand {


    public AboutCommand() {
    }

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
    public void execute() {
        //System.out.println(Version.getManifestInfo());
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}