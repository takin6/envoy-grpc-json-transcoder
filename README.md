# Envoy-gRPC-JSON-Transcoder
Demo app to play around with gRPC-JSON transcoder function of Envoy

## Architecture
```plantuml
participant User

box "Docker"
  participant "Envoy (Expose 51051)" as Envoy
  participant "gRPC (Expose 50051)" as gRPC
end box

User -> Envoy : Hit rest **[GET localhost:51051/v1/hello]**
Envoy -> Envoy : transform to gRPC requests
Envoy -> gRPC : hit RPC
gRPC --> Envoy : rpc response
Envoy --> Envoy : convert to JSON
Envoy --> User : return JSON Response
```

## Preparation
- Java 11
- Envoy v1.16.1, Use v3 api
- Docker

## How to Run

### 1. Compiling Proto
1. Install Protoc 

2. Set GOOGLEAPIS_DIR 
`GOOGLEAPIS_DIR=/Users/takayuki.inoue/MyWork/gRPC/envoy-grpc-json-transcoder/protos/util/googleapis`
`PROTOBUF_DIR=/Users/takayuki.inoue/MyWork/gRPC/envoy-grpc-json-transcoder/protos/util/protobuf`

3. Run Protoc Command
```
protoc -I$GOOGLEAPIS_DIR -I. --include_imports --include_source_info \
    --descriptor_set_out=protos/helloworld.pb protos/helloworld.proto
```

### 2. Build gRPC app
1. Build jar file
```
cd server
./gradlew build
```

2. Create Docker Image
```
cd server
docker build ./ -t grpc-json-transcoder-server
```

### 3. Running Envoy / gRPC Server
```
cd $PROJECT_ROOT
docker-compose up
```

## Make Sample Reqeust
```
-- get request
curl localhost:51051/v1/hello/taka  --header 'token: sample-token' -v

-- sample request
curl -X POST localhost:51051/v1/hello -d '{"name":"taka", "weather":"SUNNY"}' -v
```

## Reference

### Testing Java Application ON Local
```
-- move to directory
cd server
-- execute jar file
java -jar ./build/libs/server.jar
-- try the endpoint of grpc client (evans)
evans -r --host localhost -p 50051
call SayHello
```

### Links
[gRPC JSON Transcoder (Overview)](https://www.envoyproxy.io/docs/envoy/latest/configuration/http/http_filters/grpc_json_transcoder_filter)
[gRPC JSON Transcoder (API Reference)](https://www.envoyproxy.io/docs/envoy/latest/api-v3/extensions/filters/http/grpc_json_transcoder/v3/transcoder.proto.html)
[Envoy Transcoder Proto](https://github.com/envoyproxy/envoy/blob/7136c3ade0a8366a86621a1a3a63993af5573486/api/envoy/extensions/filters/http/grpc_json_transcoder/v3/transcoder.proto)
[Writing Proto for grpc transcoding](https://cloud.google.com/endpoints/docs/grpc/transcoding)
