package core.command;

import core.Connection;
import core.state.State;

public abstract class Command {
    protected State state;
    protected Connection connection;

    public Command(State state, Connection connection) {
        this.state = state;
        this.connection = connection;
    }

    public abstract State execute(String[] args);
}