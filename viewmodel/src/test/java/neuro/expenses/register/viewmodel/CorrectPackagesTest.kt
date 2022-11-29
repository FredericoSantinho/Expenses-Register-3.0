package neuro.expenses.register.viewmodel

import neuro.test.CorrectPackages
import org.junit.jupiter.api.Test

class CorrectPackagesTest {
  @Test
  fun CorrectPackages() {
    CorrectPackages(javaClass).checkPackages()
  }
}
