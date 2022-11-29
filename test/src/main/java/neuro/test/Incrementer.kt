package neuro.test

import java.util.concurrent.atomic.AtomicInteger

val incrementer = Incrementer()

class Incrementer {
  private val counter = AtomicInteger(0)

  fun getAndIncrement(): Int {
    return counter.incrementAndGet()
  }

  fun getAll(): List<Int> {
    val list = mutableListOf<Int>()
    for (i in 1..counter.get()) {
      list.add(i)
    }
    return list
  }
}