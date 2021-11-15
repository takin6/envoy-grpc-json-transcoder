package helloworld.interceptor;

import io.grpc.*;

import java.util.logging.Logger;

public class HelloworldInterceptor implements io.grpc.ServerInterceptor {
    private static final Logger logger = Logger.getLogger(HelloworldInterceptor.class.getName());

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {

        logger.info("======= [Server Interceptor] : Remote Method Invoked - " + call.getMethodDescriptor().getFullMethodName());
        logger.info("======= [Header] " + headers.toString());
        ServerCall<ReqT, RespT> serverCall = new HelloworldServerCall<>(call);
        return new HelloworldServerCallListener<>(next.startCall(serverCall, headers));
    }
}
