package com.jgrier.flinkstuff.jobs

import com.jgrier.flinkstuff.sources.IntegerSource
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.api.scala._

object ConsoleReporterTestJob {
  def main(args: Array[String]) {
    val config = new Configuration()
    config.setString("metrics.reporters", "consoleReporter")
    config.setString("metrics.reporter.consoleReporter.class", "com.jgrier.flinkstuff.metrics.ConsoleReporter")
    config.setString("metrics.reporter.consoleReporter.interval", "10 SECONDS")

    val env = new StreamExecutionEnvironment(new LocalStreamEnvironment(config))
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    val stream = env.addSource(new IntegerSource(100))

    stream
      .timeWindowAll(Time.seconds(1))
      .sum(0)
      .print

    env.execute("ConsoleReporterTestJob")
  }
}
