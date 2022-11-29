package neuro.test

class OffsetIncrementer(
  private val incrementer: Incrementer = Incrementer(),
  private val interval: Int = 1000
) {
  fun getAndIncrement(): Int {
    return incrementer.getAndIncrement() * interval
  }
}