package service.server;

import protos.calculation.CalculationOuterClass;
import protos.user.UserOuterClass;
import service.protos.AllCalcsPerUser;

public class CalculatorDALImpl implements CalculatorDAL {

    public CalculatorDALImpl() {
        System.out.println("CalculatorDALImpl instance created");
    }

    @Override
    public CalculationOuterClass.Calculation performCalculation(UserOuterClass.User user, CalculationOuterClass.Calculation calculation) {
        return null;
    }

    @Override
    public AllCalcsPerUser getAllCalcsPerUser(UserOuterClass.User user) {
        return null;
    }
}
