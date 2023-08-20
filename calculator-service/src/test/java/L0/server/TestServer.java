package service.server;

import io.grpc.testing.StreamRecorder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import protos.calculation.CalculationOuterClass;
import protos.calculation.CalculationOuterClass.Calculation;
import service.protos.CalculationRequest;
import service.protos.CalculationResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = UserServiceUnitTestConfiguration.class)
@ActiveProfiles("L0")
public class TestServer {

//    @Autowired
//    private UserService userService;

    CalculatorService service = new CalculatorService();

    private static final double DELTA = 1e-15;

    @Test
    public void testAddingTwoNumbers() throws Exception {
        Calculation.Builder c = Calculation.newBuilder();
        CalculationRequest.Builder cr = CalculationRequest.newBuilder();
        c.setNumber1(1).setNumber2(2).setOperation(CalculationOuterClass.Operation.ADD);
        cr.setCalculation(c);

        StreamRecorder<CalculationResponse> responseObserver = StreamRecorder.create();
        service.performCalc(cr.build(), responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<CalculationResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        Calculation responseCalc = results.get(0).getCalculation();

        assertEquals(3, responseCalc.getAnswer(), DELTA);
    }

    @Test
    public void testSubtractingTwoNumbers() throws Exception {
        Calculation.Builder c = Calculation.newBuilder();
        CalculationRequest.Builder cr = CalculationRequest.newBuilder();
        c.setNumber1(1).setNumber2(2).setOperation(CalculationOuterClass.Operation.SUBTRACT);
        cr.setCalculation(c);

        StreamRecorder<CalculationResponse> responseObserver = StreamRecorder.create();
        service.performCalc(cr.build(), responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<CalculationResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        Calculation responseCalc = results.get(0).getCalculation();

        assertEquals(-1, responseCalc.getAnswer(), DELTA);
    }

    @Test
    public void testMultiplyingTwoNumbers() throws Exception {
        CalculatorService service = new CalculatorService();
        Calculation.Builder c = Calculation.newBuilder();

        CalculationRequest.Builder cr = CalculationRequest.newBuilder();
        c.setNumber1(1).setNumber2(2).setOperation(CalculationOuterClass.Operation.MULTIPLY);
        cr.setCalculation(c);

        StreamRecorder<CalculationResponse> responseObserver = StreamRecorder.create();
        service.performCalc(cr.build(), responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<CalculationResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        Calculation responseCalc = results.get(0).getCalculation();

        assertEquals(2, responseCalc.getAnswer(), DELTA);
    }

    @Test
    public void testDividingTwoNumbers() throws Exception {
        CalculatorService service = new CalculatorService();
        Calculation.Builder c = Calculation.newBuilder();
        CalculationRequest.Builder cr = CalculationRequest.newBuilder();
        c.setNumber1(1).setNumber2(2).setOperation(CalculationOuterClass.Operation.DIVIDE);
        cr.setCalculation(c);

        StreamRecorder<CalculationResponse> responseObserver = StreamRecorder.create();
        service.performCalc(cr.build(), responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<CalculationResponse> results = responseObserver.getValues();
        assertEquals(1, results.size());
        Calculation responseCalc = results.get(0).getCalculation();

        assertEquals(.5, responseCalc.getAnswer(), DELTA);
    }
}