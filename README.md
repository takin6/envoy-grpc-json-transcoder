

## How to Run

### Compiling Proto
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
