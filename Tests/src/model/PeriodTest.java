package src.model;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class PeriodTest extends TestCase {

    private Period period;

    @BeforeEach
    public void setUp() {
        period = new Period(0, 10, 15);
    }

    @Test
    public void testNullPointer() {
        final boolean expected = false;
        final boolean actual = period.overlaps(null);
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferentDays() {
        Period testPeriod = new Period(1, 10, 15);
        final boolean expected = false;
        final boolean actual = period.overlaps(testPeriod);
        assertEquals(expected, actual);
    }

    @Test
    public void testSameDayDifferentHours() {
        Period testPeriod = new Period(0, 16, 20);
        final boolean expected = false;
        final boolean actual = period.overlaps(testPeriod);
        assertEquals(expected, actual);
    }

    @Test
    public void testSameDayOverlapsStartHour() {
        Period testPeriod = new Period(0, 8, 12);
        final boolean expected = true;
        final boolean actual = period.overlaps(testPeriod);
        assertEquals(expected, actual);
    }

    @Test
    public void testSameDayOverlapsEndHour() {
        Period testPeriod = new Period(0, 12, 20);
        final boolean expected = true;
        final boolean actual = period.overlaps(testPeriod);
        assertEquals(expected, actual);
    }

    @Test
    public void testSameDayOverlapsInside() {
        Period testPeriod = new Period(0, 11, 14);
        final boolean expected = true;
        final boolean actual = period.overlaps(testPeriod);
        assertEquals(expected, actual);
    }
}