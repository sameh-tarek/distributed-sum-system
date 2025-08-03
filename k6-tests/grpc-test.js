import grpc from 'k6/net/grpc';
import { check } from 'k6';

const client = new grpc.Client();
client.load(['.'], 'calculator.proto'); 

export const options = {
  vus: 1000,         
  duration: '10s',   
};

export default () => {
  client.connect('localhost:9090', {
    plaintext: true,
  });

  const response = client.invoke('/CalculatorService/Add', {
    num1: 5,
    num2: 10,
  });

  check(response, {
    'status is OK': (r) => r && r.status === grpc.StatusOK,
  });

  client.close();
};

