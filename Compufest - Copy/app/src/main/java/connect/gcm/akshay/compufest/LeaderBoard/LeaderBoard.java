package connect.gcm.akshay.compufest.LeaderBoard;


public class LeaderBoard {

    private String event_name;
    private String one;
    private String two;

    public LeaderBoard(String event_name, String one, String two, String three) {
        this.event_name = event_name;
        this.one = one;
        this.two = two;
        this.three = three;
    }

    private String three;

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }
}
