package src.model;

public class Period implements Comparable <Period> {
    public int day, startHour, endHour;

    public Period(int day, int startHour, int endHour) {
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public boolean overlaps(Period period) {
        if (period == null || day != period.day) { return false; }
        if ((startHour > period.startHour && endHour < period.endHour) ||
            period.startHour > startHour && period.endHour < endHour) {
            return true;
        }
        return (startHour >= period.startHour && startHour <= period.endHour) ||
                (endHour >= period.startHour && endHour <= period.endHour);
    }

    public int rawValue() {
        return day*1000 + startHour;
    }

    @Override
    public int compareTo(Period o) {
        return rawValue() - o.rawValue();
    }

    @Override
    public String toString() {
        return "Period { " +
                "day=" + day +
                ", startHour=" + startHour +
                ", endHour=" + endHour +
                " }";
    }
}
