package core.command;

import core.Connection;
import core.state.State;
import database.Transaction;
import database.User;
import transport.Packet;

public class Reception extends Command {
    private Transaction transaction;

    public Reception(State state, Connection connection, Transaction transaction) {
        super(state, connection);
        this.transaction = transaction;
    }

    @Override
    public State execute(String[] args) {
        if (args.length == 2 && args[1].split(":")[0].equals("TO")) {
            if (transaction.addUser(new User(args[1].split(":")[1]))) {
                connection.getSender().sendPacket(new Packet("250 OK"));
            } else {
                connection.getSender().sendPacket(new Packet("550 No such user here"));
            }
            return state;
        }
        connection.getSender().sendPacket(new Packet("550 Bad parameters"));
        return state;
    }
}