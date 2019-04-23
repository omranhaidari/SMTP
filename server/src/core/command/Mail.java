package core.command;

import core.Connection;
import core.state.Receiving;
import core.state.State;
import database.Transaction;
import database.User;
import transport.Packet;

public class Mail extends Command {
    private Transaction transaction;

    public Mail(State state, Connection connection, Transaction transaction) {
        super(state, connection);
        this.transaction = transaction;
    }

    @Override
    public State execute(String[] args) {
        if (args.length == 2) {
            String param = args[1].split(":")[0].toUpperCase();
            String senderMail = args[1].split(":")[1];
            if(param.equals("FROM") && senderMail.startsWith("<") && senderMail.endsWith(">")) {
                transaction.setSender(new User(senderMail));
                connection.getSender().sendPacket(new Packet("250 OK"));
                return new Receiving(connection, transaction);
            }
        }
        connection.getSender().sendPacket(new Packet("550 Bad parameters"));
        return state;
    }
}