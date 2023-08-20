package service.server;

import protos.calculation.CalculationOuterClass;
import protos.user.UserOuterClass;
import service.protos.AllCalcsPerUser;


public interface CalculatorDAL {

    public CalculationOuterClass.Calculation performCalculation(UserOuterClass.User user, CalculationOuterClass.Calculation calculation);

    public AllCalcsPerUser getAllCalcsPerUser(UserOuterClass.User user);
}
