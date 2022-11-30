package neuro.expenses.register.presentation

import neuro.test.CorrectPackages
import org.junit.jupiter.api.Test

class CorrectPackagesTest {
  @Test
  fun correctPackages() {
    CorrectPackages(javaClass).checkPackages()
  }
}
