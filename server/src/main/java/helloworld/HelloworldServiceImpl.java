package helloworld;

import com.google.protobuf.StringValue;
import com.google.protobuf.StringValueOrBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class HelloworldServiceImpl extends GreeterGrpc.GreeterImplBase {
    private static final Logger logger = Logger.getLogger(HelloworldServiceImpl.class.getName());

    @Override
    public void sayHello(
        Helloworld.HelloRequest request,
        StreamObserver<Helloworld.HelloReply> responseObserver
    ) {
        logger.info("Say Hello ~ !!");
        Helloworld.HelloReply reply = Helloworld.HelloReply.newBuilder().setMessage("hello " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
