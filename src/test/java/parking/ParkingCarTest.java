package parking;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingCarTest {

    @Test
    public void whenAddNewAutomobile() {
        ParkingControl parkingControl = new ParkingControl(new AutomobilePlace(15), new TrackPlace(15));
        Car ford = new Automobile("ford", "а303аа 178");
        parkingControl.distribution(ford);
        int expected = parkingControl.getAutomobilePlace().getSize();
        assertEquals(expected, 14);
    }

    @Test
    public void whenRemoveAutomobile() {
        ParkingControl parkingControl = new ParkingControl(new AutomobilePlace(15), new TrackPlace(15));
        Car ford = new Automobile("ford", "а303аа 178");
        parkingControl.distribution(ford);
        parkingControl.getAutomobilePlace().removeCar(ford);
        int expected = parkingControl.getAutomobilePlace().getSize();
        assertEquals(expected, 15);
    }

    @Test
    public void whenAddTrack() {
        ParkingControl parkingControl = new ParkingControl(new AutomobilePlace(15), new TrackPlace(5));
        Car kamaz = new Track("Kamaz", "л666ох 178");
        parkingControl.distribution(kamaz);
        int expected = parkingControl.getTrackPlace().getSize();
        assertEquals(expected, 4);
    }

    @Test
    public void whenAddTrackInAutomobilePlace() {
        ParkingControl parkingControl = new ParkingControl(new AutomobilePlace(15), new TrackPlace(0));
        Car kamaz = new Track("Kamaz", "л666ох 178");
        parkingControl.distribution(kamaz);
        int expected = parkingControl.getAutomobilePlace().getSize();
        assertEquals(expected, 13);
    }

    @Test
    public void whenAddBigTrackInAutomobilePlace() {
        ParkingControl parkingControl = new ParkingControl(new AutomobilePlace(15), new TrackPlace(1));
        Car ford = new Automobile("Ford", "а303аа 178");
        Car kamaz = new Track("Kamaz", "л666ох 178");
        Car belaz = new BigTrack("Belaz", "т656се 178");
        parkingControl.distribution(kamaz);
        parkingControl.distribution(ford);
        parkingControl.distribution(belaz);
        int expected1 = parkingControl.getAutomobilePlace().getSize();
        int expected2 = parkingControl.getTrackPlace().getSize();
        assertEquals(expected1, 11);
        assertEquals(expected2, 0);
    }

    @Test (expected = IllegalStateException.class)
    public void whenAddBigTrackButNotPlace() {
        ParkingControl parkingControl = new ParkingControl(new AutomobilePlace(3), new TrackPlace(1));
        Car ford = new Automobile("Ford", "а303аа 178");
        Car kamaz = new Track("Kamaz", "л666ох 178");
        Car belaz = new BigTrack("Belaz", "т656се 178");
        parkingControl.distribution(kamaz);
        parkingControl.distribution(ford);
        parkingControl.distribution(belaz);
    }

}