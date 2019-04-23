package core.command;

import core.Connection;
import core.state.BeforeReceiving;
import core.state.Receiving;
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
        if (args.length == 2) {
            String param = args[1].split(":")[0].toUpperCase();
            String receiverMail = args[1].split(":")[1];
            if(param.equals("TO") && receiverMail.startsWith("<") && receiverMail.endsWith(">")) {
                if (transaction.addUser(new User(receiverMail))) {
                    connection.getSender().sendPacket(new Packet("250 OK"));
                } else {
                    connection.getSender().sendPacket(new Packet("550 No such user here"));
                }

                if(state instanceof BeforeReceiving) {
                    return new Receiving(connection, transaction);
                } else {
                    return state;
                }
            }
        }
        connection.getSender().sendPacket(new Packet("550 Bad parameters"));
        return state;
    }
}