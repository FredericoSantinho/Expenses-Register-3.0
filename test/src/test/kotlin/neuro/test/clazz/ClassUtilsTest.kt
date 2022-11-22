package neuro.test.clazz

import neuro.test.CorrectPackages
import neuro.test.CorrectPackagesTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File

internal class ClassUtilsTest {
  @Test
  fun test() {
    val classUtils = ClassUtils()
    val classes = classUtils.findClasses("neuro.test").toSet()
    val expected = setOf(
      ClassUtils::class.java,
      ClassUtilsTest::class.java,
      CorrectPackages::class.java,
      CorrectPackagesTest::class.java,
    )
    assertEquals(expected, classes)
    assertTrue(classUtils.findClassNames(File("Invalid"), "Invalid").isEmpty())
  }
}