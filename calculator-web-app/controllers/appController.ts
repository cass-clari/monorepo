import { UserService, LoginUser, LoginResponse } from '../user-service';
import * as grpc from '@grpc/grpc-js';
import * as pbjs from 'protobufjs';

const UserServiceClient = grpc.makeGenericClientConstructor({}, 'UserService');

const client = new UserServiceClient(
    '127.0.0.1:8080',
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


exports.calc = (req, res) => {
  let dig1 = parseInt(req.query.dig1) || 0;
  let dig2 = parseInt(req.query.dig2) || 0;
  let op = req.query.op || 'add';
  console.log(`dig1 dig2 op: ${dig1} ${dig2} ${op}`);
  var answer;
  switch(op) {
    case "add":
      answer = dig1 + dig2;
      break;
    case "subtract":
      answer = dig1 - dig2;
      break;
    case "multiply":
      answer = dig1 * dig2;
      break;
    case "divide":
      answer = dig1 / dig2;
      break;
    default:
      answer = "The answer will appear here"
      // code block
  }
  res.render('calc.hbs', {'result': answer, 'dig1': dig1, 'dig2': dig2, "op": op });
}

exports.home = (req, res) => {

  let username = req.query.username || "";
  let password = req.query.password || "";

  console.log(`username password: ${username} ${password}`);

  if(username !== "" && password !== "") {
    const userService = UserService.create(transport, false, false);

    userService
        .login(LoginUser.create({ username: username, pwd: password }))
        .then((loginResponse) => {
          console.log(`LoginResponse`);
          console.log(
              `User: ${JSON.stringify(loginResponse)}`
          );
        });
    res.render('calc.hbs', {'result': 0, 'dig1': 0, 'dig2': 0, "op": "add" })
  } else {
    res.render('new_home.hbs')
  }
}
