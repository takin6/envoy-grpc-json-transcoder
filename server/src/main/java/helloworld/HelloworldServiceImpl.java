package helloworld;

import io.grpc.stub.StreamObserver;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import java.util.logging.Logger;

import helloworld.Helloworld.HelloReply.Feeling;
import helloworld.Helloworld.HelloRequest.Weather;

public class HelloworldServiceImpl extends GreeterGrpc.GreeterImplBase {
    private static final Logger logger = Logger.getLogger(HelloworldServiceImpl.class.getName());

    Map<String, Integer> visitCount = new HashMap<>();

    @Override
    public void sayHello(
        Helloworld.HelloRequest request,
        StreamObserver<Helloworld.HelloReply> responseObserver
    ) {
        logger.info("Say Hello~");
        Helloworld.HelloReply reply = Helloworld.HelloReply
            .newBuilder()
            .setMessage("hello " + request.getName())
            .setVisitCount(visitCount.getOrDefault(request.getName(), 0))
            .setFeelingValue(ThreadLocalRandom.current().nextInt(0, 3 + 1))
            .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void postHello(
        Helloworld.HelloRequest request,
        StreamObserver<Helloworld.HelloReply> responseObserver
    ) {
        String name = request.getName();
        Weather weather = request.getWeather();
        logger.info("received weather = " + weather);
        
        if (visitCount.containsKey(name)) {
            visitCount.put(name, visitCount.get(name) + 1);
        } else {
            visitCount.put(name, 1);
        }

        logger.info("visitCount = " + visitCount.get(name));
        Helloworld.HelloReply reply = Helloworld.HelloReply
            .newBuilder()
            .setMessage("hello " + request.getName())
            .setVisitCount(visitCount.getOrDefault(request.getName(), 0))
            .setFeelingValue(ThreadLocalRandom.current().nextInt(0, 3 + 1))
            .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
