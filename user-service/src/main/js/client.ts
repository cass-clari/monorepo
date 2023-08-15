import { UserService, LoginUser, LoginResponse } from './user-service';
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

const userService = UserService.create(transport, false, false);

userService
    .login(LoginUser.create({ username: 'ben', pwd: 'ben' }))
    .then((loginResponse) => {
        console.log(`LoginResponse`);
        console.log(
            `User: ${JSON.stringify(loginResponse)}`
        );
    });