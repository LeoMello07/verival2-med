package src.model;

import junit.framework.TestCase;
import org.junit.Test;

public class SurgeryRoomTypeTest extends TestCase {

//    Testing room default pricing

    @Test
    public void testSmallRoomPrice() {
        int expected = 400;
        int actual = SurgeryRoomType.small.price();
        assertEquals(expected, actual);
    }

    @Test
    public void testBigRoomPrice() {
        int expected = 650;
        int actual = SurgeryRoomType.big.price();
        assertEquals(expected, actual);
    }

    @Test
    public void testHighRigskRoomPrice() {
        int expected = 1200;
        int actual = SurgeryRoomType.high_risk.price();
        assertEquals(expected, actual);
    }

//    Testing default room minimum time

    @Test
    public void testSmallRoomMinimumTime() {
        int expected = 2;
        int actual = SurgeryRoomType.small.minimumTime();
        assertEquals(expected, actual);
    }

    @Test
    public void testBigRoomMinimumTime() {
        int expected = 2;
        int actual = SurgeryRoomType.big.minimumTime();
        assertEquals(expected, actual);
    }

    @Test
    public void testHighRigskRoomMinimumTime() {
        int expected = 3;
        int actual = SurgeryRoomType.high_risk.minimumTime();
        assertEquals(expected, actual);
    }
}