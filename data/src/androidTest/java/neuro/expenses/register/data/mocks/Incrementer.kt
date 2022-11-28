package neuro.expenses.register.data.mocks

import java.util.concurrent.atomic.AtomicLong

val incrementer = Incrementer()

class Incrementer {
  private val counter = AtomicLong(0)

  fun getAndIncrement(): Long {
    return counter.incrementAndGet()
  }
}