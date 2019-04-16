package core.state;

import core.Connection;
import core.command.Mail;
import core.command.Quit;
import database.Transaction;

public class Waiting extends State {
    public Waiting(Connection connection, Transaction transaction) {
        super(connection);
        commands.put("QUIT", new Quit(this, connection));
        commands.put("MAIL", new Mail(this, connection, transaction));
    }
}
