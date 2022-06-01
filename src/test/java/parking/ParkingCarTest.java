package parking;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParkingCarTest {

    @Test
    public void whenAddNewAutomobile() {
        ParkingControl parkingControl = new ParkingControl(new AutomobilePlace(2), new TrackPlace(3));
        Car ford = new Automobile("ford", "а303аа 178");
        assertTrue(parkingControl.distribution(ford));
        int expected = parkingControl.getAutomobilePlace().getFreePlaces();
        assertEquals(expected, 1);
    }

    @Test
    public void whenRemoveAutomobile() {
        ParkingControl parkingControl = new ParkingControl(new AutomobilePlace(5), new TrackPlace(2));
        Car ford = new Automobile("ford", "а303аа 178");
        assertTrue(parkingControl.distribution(ford));
        assertTrue(parkingControl.getAutomobilePlace().removeCar(ford));
        int expected = parkingControl.getAutomobilePlace().getFreePlaces();
        assertEquals(expected, 5);
    }

    @Test
    public void whenAddTrack() {
        ParkingControl parkingControl = new ParkingControl(new AutomobilePlace(5), new TrackPlace(2));
        Car kamaz = new Track("Kamaz", "л666ох 178", 2);
        assertTrue(parkingControl.distribution(kamaz));
        int expected = parkingControl.getTrackPlace().getFreePlaces();
        assertEquals(expected, 1);
    }

    @Test
    public void whenAddTrackInAutomobilePlace() {
        ParkingControl parkingControl = new ParkingControl(new AutomobilePlace(3), new TrackPlace(0));
        Car kamaz = new Track("Kamaz", "л666ох 178", 2);
        assertTrue(parkingControl.distribution(kamaz));
        int expected = parkingControl.getAutomobilePlace().getFreePlaces();
        assertEquals(expected, 1);
    }

    @Test
    public void whenAddBigTrackInAutomobilePlace() {
        ParkingControl parkingControl = new ParkingControl(new AutomobilePlace(5), new TrackPlace(1));
        Car ford = new Automobile("Ford", "а303аа 178");
        Car kamaz = new Track("Kamaz", "л666ох 178", 2);
        Car belaz = new Track("Belaz", "т656се 178", 3);
        assertTrue(parkingControl.distribution(kamaz));
        assertTrue(parkingControl.distribution(ford));
        assertTrue(parkingControl.distribution(belaz));
        int expected1 = parkingControl.getAutomobilePlace().getFreePlaces();
        int expected2 = parkingControl.getTrackPlace().getFreePlaces();
        assertEquals(expected1, 1);
        assertEquals(expected2, 0);
    }

    @Test
    public void whenAddBigTrackButNotPlace() {
        ParkingControl parkingControl = new ParkingControl(new AutomobilePlace(3), new TrackPlace(1));
        Car ford = new Automobile("Ford", "а303аа 178");
        Car kamaz = new Track("Kamaz", "л666ох 178", 2);
        Car belaz = new Track("Belaz", "т656се 178", 3);
        assertTrue(parkingControl.distribution(kamaz));
        assertTrue(parkingControl.distribution(ford));
        assertFalse(parkingControl.distribution(belaz));
    }

}