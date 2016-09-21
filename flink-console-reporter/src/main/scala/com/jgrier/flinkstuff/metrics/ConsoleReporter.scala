package com.jgrier.flinkstuff.metrics

import org.apache.flink.metrics._
import org.apache.flink.metrics.reporter.{Scheduled, AbstractReporter}
import scala.collection.JavaConverters._

/**
  * InfluxDB Metrics Reporter for Apache Flink
  *
  * To use this add the following configuration to your flink-conf.yaml file and place the JAR containing
  * this class in your flink/lib directory:
  *
  * #==========================================
  * # Metrics
  * #==========================================
  * metrics.reporters: console
  * metrics.reporter.console.class: com.jgrier.flinkstuff.metrics.ConsoleReporter
  * metrics.reporter.console.interval: 10 SECONDS
  */
class ConsoleReporter extends AbstractReporter with Scheduled {
  override def close(): Unit = {}

  override def open(metricConfig: MetricConfig): Unit = {}

  override def filterCharacters(s: String): String = s

  override def report(): Unit = {
    println("=====================================================================================================")
    println("=====================================================================================================")
    println("=====================================================================================================")
    println("=============== DUMPING METRICS =====================================================================")
    println("=====================================================================================================")
    println("=====================================================================================================")
    println("=====================================================================================================")
    printMetrics(this.gauges.asScala)
    printMetrics(this.counters.asScala)
    printMetrics(this.histograms.asScala)
  }

  private def printMetrics[T <: Metric](metrics: scala.collection.mutable.Map[T, String]): Unit = {
    for ((m, name) <- metrics) {
      println(s"${name} = ${metricValue(m)}")
    }
  }

  private def metricValue(m: Metric): String = {
    m match {
      case g: Gauge[_] => g.getValue.toString
      case c: Counter => c.getCount.toString
      case h: Histogram => h.getStatistics.toString
    }
  }
}
