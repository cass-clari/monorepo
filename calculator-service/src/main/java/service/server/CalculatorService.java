package service.server;

import com.google.protobuf.Timestamp;
import com.google.protobuf.util.JsonFormat;
import io.grpc.stub.StreamObserver;
import main.java.org.example.Calculator;
import protos.calculation.CalculationOuterClass.Calculation;
import protos.user.UserOuterClass.User;
import service.protos.AllCalcsPerUser;
import service.protos.CalcServiceGrpc;
import service.protos.CalculationRequest;
import service.protos.CalculationResponse;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;


public class CalculatorService extends CalcServiceGrpc.CalcServiceImplBase {

//    @Autowired
//    private UserDAL myUserDAL;

    @Override
    public void performCalc(CalculationRequest calculationRequest, StreamObserver<CalculationResponse> responseObserver) {
        try {
            System.out.println("Inside performCalc - REQUEST:");
            System.out.println("Inside performCalc - REQUEST:");
            System.out.println("Inside performCalc - REQUEST:");
            System.out.println("Inside performCalc - REQUEST:");
            System.out.println(JsonFormat.printer().print(calculationRequest));


            User u = calculationRequest.getUser();
            Calculation c = calculationRequest.getCalculation();
            Timestamp ts = c.getTimestamp();
            LocalDateTime ld = Instant
                    .ofEpochSecond( ts.getSeconds() , ts.getNanos() )
                    .atZone( ZoneId.of( "America/Montreal" ) )
                    .toLocalDateTime();
            System.out.println(ld.toString());

            Calculation.Builder responseCalc = Calculation.newBuilder(c);
            Calculator calculator = new Calculator();

            switch (c.getOperation()) {
                case ADD:
                    responseCalc.setAnswer(calculator.addTwoNumbers(c.getNumber1(), c.getNumber2()));
                    break;
                case SUBTRACT:
                    responseCalc.setAnswer(calculator.subtractTwoNumbers(c.getNumber1(), c.getNumber2()));
                    break;
                case MULTIPLY:
                    responseCalc.setAnswer(calculator.multiplyTwoNumbers(c.getNumber1(), c.getNumber2()));
                    break;
                case DIVIDE:
                    responseCalc.setAnswer(calculator.divideTwoNumbers(c.getNumber1(), c.getNumber2()));
                    break;
                case REMAINDER:
                    responseCalc.setAnswer(calculator.modTwoNumbers(c.getNumber1(), c.getNumber2()));
                    break;
            }

            CalculationResponse.Builder crb = CalculationResponse.newBuilder();
            //Calculation r = responseCalc.build();
            crb.setCalculation(responseCalc);
            CalculationResponse resp = crb.build();
            System.out.println("Inside performCalc - RESPONSE::");
            System.out.println(JsonFormat.printer().print(resp));
            responseObserver.onNext(resp);
            System.out.println("onnext done");

        } catch (Exception e) {
            e.printStackTrace();
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getAllCalsPerUser(User user, StreamObserver<AllCalcsPerUser> response) {
        System.out.println("Inside getAllCalsPerUser");
    }

}