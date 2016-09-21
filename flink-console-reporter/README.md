Console Metrics Reporter for Apache Flink
=============
This project just shows how to create a simple metrics reporter for Apache Flink.  It's just meant to be an example.

To use it add the following configuration to your flink-conf.yaml file and place the JAR this project
builds into your flink/lib directory:

    metrics.reporters: console
    metrics.reporter.console.class: com.jgrier.flinkstuff.metrics.ConsoleReporter
    metrics.reporter.influxdb.interval: 10 SECONDS
    
