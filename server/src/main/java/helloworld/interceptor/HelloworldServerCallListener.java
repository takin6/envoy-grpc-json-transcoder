package helloworld.interceptor;

import io.grpc.ForwardingServerCallListener;
import io.grpc.ServerCall;

import java.util.logging.Logger;

public class HelloworldServerCallListener<R> extends ForwardingServerCallListener<R>  {
    private static final Logger logger = Logger.getLogger(HelloworldServerCallListener.class.getName());

    private final ServerCall.Listener<R> delegate;

    HelloworldServerCallListener(ServerCall.Listener<R> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected ServerCall.Listener<R> delegate() {
        return delegate;
    }

    @Override
    public void onMessage(R message) {
        logger.info("INTERCEPTOR: Message Received from Client -> Service " + message);
        super.onMessage(message);
    }
}
