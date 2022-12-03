package neuro.expenses.register.domain.service.location

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class NoLocationExceptionsTest {
  @Test
  fun test() {
    assertEquals(NoLocationException(), NoLocationException())
    assertEquals(NoLocationPermissionException(), NoLocationPermissionException())

    val map = mutableMapOf<Any, Boolean>()
    map.put(NoLocationException(), true)
    map.put(NoLocationPermissionException(), true)

    assertEquals(2, map.values.size)

    map.put(NoLocationException(), true)
    map.put(NoLocationPermissionException(), true)

    assertEquals(2, map.values.size)
  }
}