# cc-edge-backend

Use these scripts to setup the backend for the betting demo

```shell script
./create-env.sh
```

Below are the files and what they do:
edge-demo-amq.yml - Configures the AMQ Streams Operator on the cluster in the specified namespace
edge-demo-kafka.yml - Setup the Kafka Cluster and the topic used by the quarkus backend application
edge-demo-app.yml - Creates the quarkus application and the postgres DB pod
