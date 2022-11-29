package neuro.test

import java.util.concurrent.atomic.AtomicLong

val incrementer = Incrementer()

class Incrementer {
  private val counter = AtomicLong(0)

  fun getAndIncrement(): Long {
    return counter.incrementAndGet()
  }

  fun getAll(): List<Long> {
    val list = mutableListOf<Long>()
    for (i in 1..counter.get()) {
      list.add(i)
    }
    return list
  }
}