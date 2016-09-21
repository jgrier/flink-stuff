package com.jgrier.flinkstuff.sources

import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction
import org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext
import org.apache.flink.streaming.api.watermark.Watermark

class IntegerSource(val periodMs: Long) extends RichParallelSourceFunction[Long] {
  private var continue = true;
  private var i = 0L;

  override def cancel(): Unit = continue = false

  override def run(ctx: SourceContext[Long]): Unit = {
    while (continue) {
      Thread.sleep(periodMs)
      ctx.collectWithTimestamp(i, i*periodMs)
      ctx.emitWatermark(new Watermark(i*periodMs))
      i += 1
    }
  }
}