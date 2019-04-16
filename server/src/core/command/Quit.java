package core.command;

import core.Connection;
import core.state.State;
import core.state.Terminating;

public class Quit extends Command {
    public Quit(State state, Connection connection) {
        super(state, connection);
    }

    @Override
    public State execute(String[] args) {
        return new Terminating(connection);
    }
}
