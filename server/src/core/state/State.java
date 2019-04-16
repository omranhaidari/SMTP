package core.state;

import core.Connection;
import core.command.Command;
import transport.Packet;

import java.util.HashMap;
import java.util.Map;

public abstract class State {
    protected Connection connection;
    protected Map<String, Command> commands;

    public State(Connection connection) {
        this.connection = connection;
        this.commands = new HashMap<>();
    }

    public State accept(Packet packet) {
        String[] inputs = packet.getData().split(" ");
        Command command;
        if ((command = commands.get(inputs[0])) != null) {
            return command.execute(inputs);
        }
        if ((command = commands.get("*")) != null) {
            return command.execute(new String[]{packet.getData()});
        }
        connection.getSender().sendPacket(new Packet("550 Bad request"));
        return this;
    }
}

