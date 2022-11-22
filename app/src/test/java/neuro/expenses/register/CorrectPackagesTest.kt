package neuro.expenses.register

import neuro.test.CorrectPackages
import org.junit.Test

class CorrectPackagesTest {
  @Test
  fun test() {
    CorrectPackages(javaClass).checkPackages()
  }
}
