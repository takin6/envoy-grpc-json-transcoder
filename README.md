

## How to Run

### Compiling Proto
1. Install Protoc 

2. Set GOOGLEAPIS_DIR 
`GOOGLEAPIS_DIR=/Users/takayuki.inoue/MyWork/gRPC/rest-api-transcoder/protos/util/googleapis`

3. Run Protoc Command
```
protoc -I$GOOGLEAPIS_DIR -I. --include_imports --include_source_info \
    --descriptor_set_out=protos/helloworld.pb protos/helloworld.proto
```

### Running Grpc Application
1. build jar file
```
cd server
./gradlew build
```

2. Create Docker Image
```
cd server
docker build ./ -t grpc-json-transcoder-server
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
cd server
java -jar ./build/libs/server.jar
evans -r --host localhost -p 50051
call SayHello
```