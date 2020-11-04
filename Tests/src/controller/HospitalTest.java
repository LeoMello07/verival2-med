package src.controller;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import src.model.*;

import java.util.ArrayList;
import java.util.List;

public class HospitalTest extends TestCase {


    private List<Doctor> doctors;
    private List <SurgeryRoom> surgeryRooms;
    private List <Allocation> allocations;
    private Hospital hospital;

    @BeforeEach
    public void setUp() {
//        Create doctors list
        doctors = new ArrayList<>();
        doctors.add(new Doctor("Luiz Guerra", 135, Specialization.dermatologist));
        doctors.add(new Doctor("Leonardo de Mello", 468, Specialization.cardiologist));
        doctors.add(new Doctor("Daniel Callegari", 951, Specialization.neurologist));
//        Create rooms list
        surgeryRooms = new ArrayList<>();
        surgeryRooms.add(new SurgeryRoom("Aeolus", SurgeryRoomType.small));
        surgeryRooms.add(new SurgeryRoom("Demeter", SurgeryRoomType.big));
        surgeryRooms.add(new SurgeryRoom("Hera", SurgeryRoomType.high_risk));
        surgeryRooms.add(new SurgeryRoom("Nyx", SurgeryRoomType.high_risk));
//        Create allocations list
        allocations = new ArrayList<>();
//        Dermatologist surgery
        allocations.add(new Allocation(doctors.get(0), surgeryRooms.get(0), new Period(0, 10, 12)));
        allocations.add(new Allocation(doctors.get(0), surgeryRooms.get(0), new Period(3, 10, 12)));
        allocations.add(new Allocation(doctors.get(0), surgeryRooms.get(0), new Period(5, 10, 12)));
        allocations.add(new Allocation(doctors.get(0), surgeryRooms.get(0), new Period(31, 10, 12)));
//        Cardiologist surgery
        allocations.add(new Allocation(doctors.get(1), surgeryRooms.get(1), new Period(1, 10, 12)));
        allocations.add(new Allocation(doctors.get(1), surgeryRooms.get(2), new Period(2, 10, 12)));
        allocations.add(new Allocation(doctors.get(1), surgeryRooms.get(3), new Period(4, 10, 12)));
        allocations.add(new Allocation(doctors.get(1), surgeryRooms.get(3), new Period(30, 10, 12)));
//        Neurologist surgery
        allocations.add(new Allocation(doctors.get(2), surgeryRooms.get(1), new Period(0, 10, 12)));
        allocations.add(new Allocation(doctors.get(2), surgeryRooms.get(2), new Period(3, 10, 12)));
        allocations.add(new Allocation(doctors.get(2), surgeryRooms.get(2), new Period(5, 10, 12)));
        allocations.add(new Allocation(doctors.get(2), surgeryRooms.get(3), new Period(31, 10, 12)));
//        Creates hospital
        hospital = new Hospital(doctors, surgeryRooms, allocations);
    }


//    Test method allocate new allocation
    @Test
    public void testPositiveAllocation() {
        Allocation allocation = new Allocation(
                doctors.get(0),
                surgeryRooms.get(0),
                (new Period(50, 10, 15))
        );
        boolean expected = true;
        boolean actual = hospital.allocate(allocation);
        assertEquals(expected, actual);
    }

    @Test
    public void testNullAllocation() {
        boolean expected = false;
        boolean actual = hospital.allocate(null);
        assertEquals(expected, actual);
    }

    @Test
    public void testDoctorWithIllegalRoomAllocation() {
        Allocation allocation = new Allocation(
                doctors.get(0),
                surgeryRooms.get(2),
                (new Period(50, 10, 15))
        );
        boolean expected = false;
        boolean actual = hospital.allocate(allocation);
        assertEquals(expected, actual);
    }

    @Test
    public void testIllegalTimeStampRoomAllocation() {
        Allocation allocation = new Allocation(
                doctors.get(0),
                surgeryRooms.get(2),
                (new Period(50, 2, 6))
        );
        boolean expected = false;
        boolean actual = hospital.allocate(allocation);
        assertEquals(expected, actual);
    }

    @Test
    public void testAllocationWithConflictingTimes() {
        Allocation allocation = new Allocation(
                doctors.get(0),
                surgeryRooms.get(2),
                (new Period(0, 10, 12))
        );
        boolean expected = false;
        boolean actual = hospital.allocate(allocation);
        assertEquals(expected, actual);
    }

//    Test respectTimeStamp function
    @Test
    public void testRespectTimeStampWithNullRoomType() {
        boolean expected = false;
        boolean actual = hospital.respectTimeStamp(
                null,
                new Period(0, 8, 15 )
        );
        assertEquals(expected, actual);
    }

    @Test
    public void testRespectTimeStampWithNullPeriod() {
        boolean expected = false;
        boolean actual = hospital.respectTimeStamp(
                SurgeryRoomType.small,
                null
        );
        assertEquals(expected, actual);
    }

    @Test
    public void testFullPeriodBeforeOpening() {
        boolean expected = false;
        boolean actual = hospital.respectTimeStamp(
                SurgeryRoomType.small,
                new Period(0, 2, 5 )
        );
        assertEquals(expected, actual);
    }

    @Test
    public void testPeriodStartsBeforeOpening() {
        boolean expected = false;
        boolean actual = hospital.respectTimeStamp(
                SurgeryRoomType.small,
                new Period(0, 4, 8 )
        );
        assertEquals(expected, actual);
    }

    @Test
    public void testFullPeriodAfterClosing() {
        boolean expected = false;
        boolean actual = hospital.respectTimeStamp(
                SurgeryRoomType.small,
                new Period(0, 22, 24 )
        );
        assertEquals(expected, actual);
    }

    @Test
    public void testPeriodEndAfterClosing() {
        boolean expected = false;
        boolean actual = hospital.respectTimeStamp(
                SurgeryRoomType.small,
                new Period(0, 18, 24 )
        );
        assertEquals(expected, actual);
    }

    @Test
    public void testMinimumSurgeryTimeForSmallRoomIsIllegal() {
        boolean expected = false;
        boolean actual = hospital.respectTimeStamp(
                SurgeryRoomType.small,
                new Period(0, 8, 9 )
        );
        assertEquals(expected, actual);
    }

    @Test
    public void testMinimumSurgeryTimeForBigRoomIsIllegal() {
        boolean expected = false;
        boolean actual = hospital.respectTimeStamp(
                SurgeryRoomType.big,
                new Period(0, 8, 9 )
        );
        assertEquals(expected, actual);
    }

    @Test
    public void testMinimumSurgeryTimeForBigRoomForTwoHoursIsIllegal() {
        boolean expected = false;
        boolean actual = hospital.respectTimeStamp(
                SurgeryRoomType.high_risk,
                new Period(0, 8, 9 )
        );
        assertEquals(expected, actual);
    }

    @Test
    public void testMinimumSurgeryTimeForBigRoomForThreeHoursIsIllegal() {
        boolean expected = false;
        boolean actual = hospital.respectTimeStamp(
                SurgeryRoomType.high_risk,
                new Period(0, 8, 10 )
        );
        assertEquals(expected, actual);
    }

    @Test
    public void testRespectTimeStamp() {
        boolean expected = true;
        boolean actual = hospital.respectTimeStamp(
                SurgeryRoomType.small,
                new Period(0, 8, 12 )
        );
        assertEquals(expected, actual);
    }

//    Test doctor allocation to room

    @Test
    public void testSpecializationIsNull() {
        boolean expected = false;
        boolean actual = hospital.canAllocateDoctorToRoom(
                null, SurgeryRoomType.small);
        assertEquals(expected, actual);
    }

    @Test
    public void testSurgeryRoomTypeIsNull() {
        boolean expected = false;
        boolean actual = hospital.canAllocateDoctorToRoom(
                Specialization.dermatologist, null);
        assertEquals(expected, actual);
    }

    @Test
    public void testDermatologistOnSmallRoom() {
        boolean expected = true;
        boolean actual = hospital.canAllocateDoctorToRoom(
                Specialization.dermatologist, SurgeryRoomType.small);
        assertEquals(expected, actual);
    }

    @Test
    public void testDermatologistOnBigRoom() {
        boolean expected = false;
        boolean actual = hospital.canAllocateDoctorToRoom(
                Specialization.dermatologist, SurgeryRoomType.big);
        assertEquals(expected, actual);
    }

    @Test
    public void testDermatologistOnHighRiskRoom() {
        boolean expected = false;
        boolean actual = hospital.canAllocateDoctorToRoom(
                Specialization.dermatologist, SurgeryRoomType.high_risk);
        assertEquals(expected, actual);
    }

    @Test
    public void testNeurologistOnSmallRoom() {
        boolean expected = false;
        boolean actual = hospital.canAllocateDoctorToRoom(
                Specialization.neurologist, SurgeryRoomType.small);
        assertEquals(expected, actual);
    }

    @Test
    public void testNeurologistOnOnBigRoom() {
        boolean expected = true;
        boolean actual = hospital.canAllocateDoctorToRoom(
                Specialization.neurologist, SurgeryRoomType.big);
        assertEquals(expected, actual);
    }

    @Test
    public void testNeurologistOnOnHighRiskRoom() {
        boolean expected = true;
        boolean actual = hospital.canAllocateDoctorToRoom(
                Specialization.neurologist, SurgeryRoomType.high_risk);
        assertEquals(expected, actual);
    }

    @Test
    public void testCardiologistOnSmallRoom() {
        boolean expected = false;
        boolean actual = hospital.canAllocateDoctorToRoom(
                Specialization.cardiologist, SurgeryRoomType.small);
        assertEquals(expected, actual);
    }

    @Test
    public void testCardiologistOnOnBigRoom() {
        boolean expected = true;
        boolean actual = hospital.canAllocateDoctorToRoom(
                Specialization.cardiologist, SurgeryRoomType.big);
        assertEquals(expected, actual);
    }

    @Test
    public void testCardiologistOnOnHighRiskRoom() {
        boolean expected = true;
        boolean actual = hospital.canAllocateDoctorToRoom(
                Specialization.cardiologist, SurgeryRoomType.high_risk);
        assertEquals(expected, actual);
    }

}