package m2_2.m2_2_1;

public class Time {
    private String hour;
    private String minute;
    private String second;

    public Time(String hour, String minute, String second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    @Override
    public String toString() {
        return hour+":"+minute+":"+second;
    }
}
