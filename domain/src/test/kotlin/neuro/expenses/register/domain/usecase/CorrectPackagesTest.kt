package neuro.expenses.register.domain.usecase

import neuro.test.CorrectPackages
import org.junit.jupiter.api.Test

class CorrectPackagesTest {
  @Test
  fun test() {
    CorrectPackages(javaClass).checkPackages()
  }
}
