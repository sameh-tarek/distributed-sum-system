package com.example.calculatorservicegrpc.grpc;

import com.example.calculatorservicegrpc.AddRequest;
import com.example.calculatorservicegrpc.AddResponse;
import com.example.calculatorservicegrpc.CalculatorServiceGrpc;
import com.example.calculatorservicegrpc.kafka.KafkaSumProducer;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@RequiredArgsConstructor
@GrpcService
public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {
    private final KafkaSumProducer kafkaSumProducer;

    @Override
    public void add(AddRequest request, StreamObserver<AddResponse> responseObserver) {
        int sum = request.getNum1() + request.getNum2();

        kafkaSumProducer.sendSum(sum);

        AddResponse response = AddResponse.newBuilder()
                .setSum(sum)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
