package command;

public abstract class AbstractCommand {

    abstract public String command();
    abstract public String description();
    abstract public void execute();
}
