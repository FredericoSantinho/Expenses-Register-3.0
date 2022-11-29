package neuro.expenses.register.domain

import neuro.test.CorrectPackages
import org.junit.jupiter.api.Test

class CorrectPackagesTest {
  @Test
  fun correctPackages() {
    CorrectPackages(javaClass).checkPackages()
  }
}
