# Readme

This is a work in progress.  Commits are pushed up when incremental progress is made, but may not be working correctly.

**Note: You need to use Java 17 to build the jars.**

## Start the Docker Container

Set environment variables for the containers:

```shell
export CASSANDRA_VERSION=latest
export TEMPORAL_UI_VERSION=latest
export ELASTICSEARCH_VERSION=7.17.24
export TEMPORAL_VERSION=latest
export TEMPORAL_ADMINTOOLS_VERSION=latest
```

## Build the Jar

```shell
./gradlew shadowjar
```
Bring up the environment.

This builds the project in a docker container, starts Cassandra, Elastic Search, temporal server, temporal ui, 
the temporal worker, and the temporal codec server:

```shell
docker compose up -d 
```

This will run the demo app within docker and create some workflows.  You can inspect them in the UI.

You can run the demo app locally as well, although it's unnecessary for the demo.  That can be done with the following:

```shell
./gradlew app:run
```

## Test the Services 

The services are all started as a bridge, so the ports that are used are also exposed to the host.

### Cassandra

Locally, run cqlsh:

```shell
cqlsh
```

It should connect to the container.


### ElasticSearch

Open http://localhost:9200/ in your browser.

### Temporal 

http://localhost:8080/

#### Temporal Metrics

http://localhost:8000/metrics




Run the app:

```shell
./gradlew app:run
```

## Table Structure

Temporal creates the following tables in Cassandra:

```shell
cqlsh:temporal> desc tables;

cluster_membership     namespaces        queues
cluster_metadata       namespaces_by_id  schema_update_history
cluster_metadata_info  nexus_endpoints   schema_version
executions             queue             task_queue_user_data
history_node           queue_messages    tasks
history_tree           queue_metadata
```


### 

To view the contents of the Elastic Search Index:

http://localhost:9200/temporal_visibility_v1_dev/_search
