import grpc from 'k6/net/grpc';
import { check } from 'k6';

const client = new grpc.Client();
client.load(['.'], 'calculator.proto'); // تأكد أن البروتو في نفس المجلد

export const options = {
  vus: 1000,         // عدد المستخدمين الافتراضيين
  duration: '10s',   // مدة التشغيل
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

