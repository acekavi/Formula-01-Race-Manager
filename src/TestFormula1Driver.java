import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestFormula1Driver {
    private Formula1Driver testDriver = new Formula1Driver("Avishka", "Galle", new Car("Lambo"));

    @Test
    void testDriverDetails() {
        assertEquals(testDriver.getName(),"Avishka");
        assertEquals(testDriver.getLocation(),"Galle");
        assertEquals(testDriver.getTeam().getCarManufacturer(), "Lambo");
    }

    @Test
    void testDriverStatistics() {
        testDriver.setStatistics(0, 1);
        assertEquals(testDriver.getTotalPoints(), 25);

        testDriver.setStatistics(1, 1);
        assertEquals(testDriver.getTotalPoints(), 25+18);

        testDriver.setStatistics(2, 1);
        assertEquals(testDriver.getTotalPoints(), 25+18+15);

        testDriver.setStatistics(3, 1);
        assertEquals(testDriver.getTotalPoints(), 25+18+15+12);

        testDriver.setStatistics(4, 1);
        assertEquals(testDriver.getTotalPoints(), 25+18+15+12+10);

        testDriver.setStatistics(5, 1);
        assertEquals(testDriver.getTotalPoints(), 25+18+15+12+10+8);

        testDriver.setStatistics(6, 1);
        assertEquals(testDriver.getTotalPoints(), 25+18+15+12+10+8+6);

        testDriver.setStatistics(7, 1);
        assertEquals(testDriver.getTotalPoints(), 25+18+15+12+10+8+6+4);

        testDriver.setStatistics(8, 1);
        assertEquals(testDriver.getTotalPoints(), 25+18+15+12+10+8+6+4+2);

        testDriver.setStatistics(9, 1);
        assertEquals(testDriver.getTotalPoints(), 25+18+15+12+10+8+6+4+2+1);

        testDriver.setStatistics(0, 5);
        assertArrayEquals(testDriver.getPositionDetails(), new int[]{6,1,1,1,1,1,1,1,1,1});
    }
}