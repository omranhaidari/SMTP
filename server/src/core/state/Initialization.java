package core.state;

import core.Connection;
import core.command.Quit;

public class Initialization extends State {
    public Initialization(Connection connection) {
        super(connection);
        commands.put("QUIT", new Quit(this, connection));
    }
}
