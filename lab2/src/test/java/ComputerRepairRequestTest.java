import ubb.scs.mpp.model.ComputerRepairRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComputerRepairRequestTest {
    @Test
    @DisplayName("First Test")
    public void firstTest() {
        ComputerRepairRequest computerRepairRequest = new ComputerRepairRequest();
        assertEquals("",computerRepairRequest.getOwnerName());
        assertEquals("",computerRepairRequest.getOwnerAddress());
        assertEquals("",computerRepairRequest.getModel());
    }

    @Test
    @DisplayName("Test Example")
    public void exampleTest() {
        assertEquals(2,2,"Numerele trebuie sa fie egale");
    }
}
