package connect.gcm.akshay.compufest;

/**
 * Created by Akshay on 24-07-2016.
 */
public class NotificationMessage {

    private String message;
    private String datetime;

    public NotificationMessage() {
    }



    public NotificationMessage(String message, String datetime) {
        this.message = message;
        this.datetime = datetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
