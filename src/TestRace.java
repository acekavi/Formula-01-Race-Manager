import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestRace {
    private Race testRace;
    private ArrayList<Formula1Driver> driversList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        driversList.add(new Formula1Driver("Lorem", "Lorem", new Car("Lorem")));
        driversList.add(new Formula1Driver("Ipsum", "Ipsum", new Car("Ipsum")));
        driversList.add(new Formula1Driver("Dolor", "Dolor", new Car("Dolor")));
        driversList.add(new Formula1Driver("Sit", "Sit", new Car("Sit")));
        driversList.add(new Formula1Driver("Amet", "Amet", new Car("Amet")));
        driversList.add(new Formula1Driver("Consectetur", "Consectetur", new Car("Consectetur")));
        driversList.add(new Formula1Driver("Elit", "Elit", new Car("Elit")));
        driversList.add(new Formula1Driver("Sed", "Sed", new Car("Sed")));
        driversList.add(new Formula1Driver("Tempor", "Tempor", new Car("Tempor")));
        driversList.add(new Formula1Driver("Aliqua", "Aliqua", new Car("Aliqua")));

        testRace = new Race(driversList, driversList);
    }

    @Test
    void testDriverStats(){
        assertEquals(driversList.get(0).getTotalRaces(), 1);
        assertEquals(driversList.get(0).getTotalPoints(), 25);
    }

    @Test
    void testSearchDriver(){
        assertTrue(testRace.searchDriver("Lorem"));
        assertFalse(testRace.searchDriver("Avishka"));
    }
}