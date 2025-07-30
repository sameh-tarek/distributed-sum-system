import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 1000, // virtual users
  duration: '10s', // test duration
};

export default function () {
  const res = http.get("http://172.17.0.1:8080/total");
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(1);
}
