package connect.gcm.akshay.compufest.Schedule;

/**
 * Created by Akshay on 22-07-2016.
 */
public class Schedule {

    private int event_day;
    private String event_name;
    private String event_place;
    private String event_time;

    public Schedule(int event_day, String event_name, String event_place, String event_time) {
        this.event_day = event_day;
        this.event_name = event_name;
        this.event_place = event_place;
        this.event_time = event_time;
    }

    public int getEvent_day() {
        return event_day;
    }

    public void setEvent_day(int event_day) {
        this.event_day = event_day;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_place() {
        return event_place;
    }

    public void setEvent_place(String event_place) {
        this.event_place = event_place;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }
}
