package core.Utils;

import database.Transaction;
import database.User;

import java.util.Date;
import java.util.Objects;

public class Mail {

    private String from;
    private String to;
    private String subject;
    private Date date;
    private String messageID;
    private String body;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("\r" + "\n");
        builder.append("From: <" + from + ">" + "\r" + "\n");
        builder.append("To: <" + to + ">" + "\r" + "\n");
        builder.append("Subject: " + subject + "\r" + "\n");
        builder.append("\r\n");

        if(body != null) {
            builder.append(body);
        }

        return builder.toString();
    }

    public static Mail createFromTransaction(User receiver, Transaction transaction) {
        Mail mail = new Mail();

        mail.from = transaction.getSender().getAddress();
        mail.to = receiver.getAddress();

        // Index du début du body
        int bodyIndex = transaction.getData().indexOf("");
        for(int i = 0; i < bodyIndex; i++) {
            String[] header = transaction.getData().get(i).split(":");
            if(header.length >= 2) {
                switch (header[0].toUpperCase()) {
                    case "SUBJECT":
                        mail.subject = header[1];
                        break;
                    case "FROM":
                    case "TO":
                    default:
                        break;
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for(int i = bodyIndex + 1; i < transaction.getData().size(); i++) {
            builder.append(transaction.getData().get(i))
                    .append("\r\n");
        }
        builder.append(".\r\n");
        mail.body = builder.toString();

        return mail;
    }
}
