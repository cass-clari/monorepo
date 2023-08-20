package service.server;

import io.grpc.testing.StreamRecorder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import protos.calculation.CalculationOuterClass;
import protos.calculation.CalculationOuterClass.Calculation;
import service.protos.CalculationRequest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = UserServiceUnitTestConfiguration.class)
@ActiveProfiles("L0")
public class TestServer {

//    @Autowired
//    private UserService userService;

    private static final double DELTA = 1e-15;

    @Test
    public void testLoginResponse() throws Exception {
        CalculatorService service = new CalculatorService();
        Calculation.Builder c = Calculation.newBuilder();
        CalculationRequest.Builder cr = CalculationRequest.newBuilder();
        c.setNumber1(1).setNumber2(2).setOperation(CalculationOuterClass.Operation.ADD);
        cr.setCalculation(c);

        StreamRecorder<Calculation> responseObserver = StreamRecorder.create();
        service.performCalc(cr.build(), responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<Calculation> results = responseObserver.getValues();
        assertEquals(1, results.size());
        Calculation responseCalc = results.get(0);

        assertEquals(3, responseCalc.getAnswer(), DELTA);
    }
}