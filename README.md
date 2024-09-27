# Readme

This is a work in progress.  Commits are pushed up when incremental progress is made, but may not be working correctly.

## Start the Docker Container

Set environment variables for the containers:

```shell
export CASSANDRA_VERSION=latest
export TEMPORAL_UI_VERSION=latest
export ELASTICSEARCH_VERSION=7.17.24
export TEMPORAL_VERSION=latest
export TEMPORAL_ADMINTOOLS_VERSION=latest
```

Bring up the environment:

```shell
docker compose up
```

## Test the Services 

The services are all started as a bridge, so the ports that are exposed are exposed to the host.

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


Run the worker:

```shell

```

Run the app:

```shell

```

