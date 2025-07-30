package com.example.calculatorservicegrpc.grpc;

import com.example.calculatorservicegrpc.AddRequest;
import com.example.calculatorservicegrpc.AddResponse;
import com.example.calculatorservicegrpc.CalculatorServiceGrpc;
import com.example.calculatorservicegrpc.outboxPattern.service.OutboxService;
import io.grpc.stub.StreamObserver;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@RequiredArgsConstructor
@GrpcService
public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    private final OutboxService outboxService;

    @Timed(value = "grpc.add.latency", description = "Time taken to handle gRPC Add request")
    @Override
    public void add(AddRequest request, StreamObserver<AddResponse> responseObserver) {
        int sum = request.getNum1() + request.getNum2();

        outboxService.saveEvent("SUM", String.valueOf(sum));

        AddResponse response = AddResponse.newBuilder()
                .setSum(sum)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
