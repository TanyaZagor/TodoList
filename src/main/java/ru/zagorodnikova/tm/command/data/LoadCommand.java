package ru.zagorodnikova.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.zagorodnikova.tm.command.AbstractCommand;
import ru.zagorodnikova.tm.entity.Domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadCommand extends AbstractCommand {
    @NotNull
    @Override
    public String command() {
        return "load";
    }

    @NotNull
    @Override
    public String description() {
        return "command to load";
    }

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("file.txt"));
        Domain domain = (Domain) inputStream.readObject();
        inputStream.close();
        System.out.println(domain.getProjects());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
