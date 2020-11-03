package src.model;

public class Time implements Comparable<Time> {
    int day, hour, minute;

    public Time(int day, int hour, int minute) {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public int rawValue() {
        return minute + hour*60 + day*60*24;
    }

    @Override
    public int compareTo(Time o) {
        return rawValue() - o.rawValue();
    }

    @Override
    public String toString() {
        return "Day " + day +
                " - " + hour +
                ": minute=" + minute;
    }
}
