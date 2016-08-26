InfluxDB Metrics Reporter for Apache Flink
=============

To use this add the following configuration to your flink-conf.yaml file and place the JAR this project
builds into your flink/lib directory:

    metrics.reporters: influxdb
    metrics.reporter.influxdb.server: localhost
    metrics.reporter.influxdb.port: 8086
    metrics.reporter.influxdb.user: admin
    metrics.reporter.influxdb.password: admin
    metrics.reporter.influxdb.db: flink
    metrics.reporter.influxdb.class: com.jgrier.flinkstuff.metrics.InfluxDbReporter
    metrics.reporter.influxdb.interval: 1 SECONDS
    
    metrics format: host.process_type.process_id.job_name.task_name.index
    
    metrics.scope.jm: <host>.jobmanager.1.jobmanager.jobmanager.1
    metrics.scope.jm.job: <host>.jobmanager.1.<job_name>.jobmanager.1
    metrics.scope.tm: <host>.taskmanager.<tm_id>.taskmanager.taskmanager.1
    metrics.scope.tm.job: <host>.taskmanager.<tm_id>.<job_name>.taskmanager.1
    metrics.scope.tm.task: <host>.taskmanager.<tm_id>.<job_name>.<task_name>.<subtask_index>
    metrics.scope.tm.operator: <host>.taskmanager.<tm_id>.<job_name>.<operator_name>.<subtask_index>

This along with InfluxDB and Grafana allows you to monitor Apache Flink with nice dashoboard like the following:

![Alt text](flink-metrics.png)