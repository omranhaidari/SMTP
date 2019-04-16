package core.state;

import core.Connection;
import core.command.Data;
import core.command.Quit;
import core.command.Reception;
import database.Transaction;

public class Receiving extends State {
    public Receiving(Connection connection, Transaction transaction) {
        super(connection);
        commands.put("QUIT", new Quit(this, connection));
        commands.put("RCPT", new Reception(this, connection, transaction));
        commands.put("DATA", new Data(this, connection, transaction));
    }
}
