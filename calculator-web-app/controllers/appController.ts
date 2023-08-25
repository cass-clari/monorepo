import {LoginUser, UserService} from '../user-service';
import {CalcService, CalculationRequest} from '../calculator-service';
import * as grpc from '@grpc/grpc-js';
import * as pbjs from 'protobufjs';

const UserServiceClient = grpc.makeGenericClientConstructor({}, 'UserService');
const CalcServiceClient = grpc.makeGenericClientConstructor({}, 'CalcService');

const client = new UserServiceClient(
    process.env.USER_SERVICE_HOST_PORT,
    grpc.credentials.createInsecure()
);

const calcClient = new CalcServiceClient(
    process.env.CALC_SERVICE_HOST_PORT,
    grpc.credentials.createInsecure()
);

const transport: pbjs.RPCImpl = function (method, data, callback) {
    client.makeUnaryRequest(
        `/UserService/${method.name}`,
        (arg) => Buffer.from(arg),
        (arg) => arg,
        data,
        callback
    );
};

const calcTransport: pbjs.RPCImpl = function (method, data, callback) {
    calcClient.makeUnaryRequest(
        `/CalcService/${method.name}`,
        (arg) => Buffer.from(arg),
        (arg) => arg,
        data,
        callback
    );
};


exports.calc = (req, res) => {
    let dig1 = parseFloat(req.query.dig1) || 0.0;
    let dig2 = parseFloat(req.query.dig2) || 0.0;
    let op = req.query.op || 'undeclared';
    console.log(`dig1 dig2 op: ${dig1} ${dig2} ${op}`);
    var answer;

    if (op !== 'undeclared') {
        const cService = CalcService.create(calcTransport, false, false);
        console.log(`dig1 dig2 op: ${dig1} ${dig2} ${op}`);
        console.log(`diggity diggity diggity`);
        var operationNum;

        switch (op) {
            case "add":
                operationNum = 1;
                break;
            case "subtract":
                operationNum = 2;
                break;
            case "multiply":
                operationNum = 3;
                break;
            case "divide":
                operationNum = 4;
                break;
            case "remainder":
                operationNum = 5;
                break;
        }
        cService
            .performCalc(CalculationRequest.create({calculation: {number1: dig1, number2: dig2, operation: operationNum}}))
            .then((calcResponse) => {
                console.log(`calcResponse!!`);
                console.log(`calcResponse!!123`);
                console.log(
                    `response: ${JSON.stringify(calcResponse)}`
                );
                answer = calcResponse.calculation.answer;
                console.log(`answer: ${answer}`);
                res.render('calc.hbs', {'result': answer, 'dig1': dig1, 'dig2': dig2, "op": op});
            });

    } else {
        answer = "The answer will appear here"
        res.render('calc.hbs', {'result': answer, 'dig1': dig1, 'dig2': dig2, "op": op});
    }

    //res.render('calc.hbs', {'result': answer, 'dig1': dig1, 'dig2': dig2, "op": op });
}

exports.home = (req, res) => {

    let username = req.query.username || "";
    let password = req.query.password || "";

    console.log(`username password: ${username} ${password}`);

    if (username !== "" && password !== "") {
        const userService = UserService.create(transport, false, false);

        userService
            .login(LoginUser.create({username: username, pwd: password}))
            .then((loginResponse) => {
                console.log(`LoginResponse`);
                console.log(
                    `User: ${JSON.stringify(loginResponse)}`
                );
            });
        res.render('calc.hbs', {'result': 0, 'dig1': 0, 'dig2': 0, "op": "add"})
    } else {
        res.render('new_home.hbs')
    }
}
