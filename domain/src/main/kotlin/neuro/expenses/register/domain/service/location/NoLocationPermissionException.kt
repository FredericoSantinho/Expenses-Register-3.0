package neuro.expenses.register.domain.service.location

class NoLocationPermissionException : IllegalStateException() {
  override fun equals(other: Any?): Boolean {
    return other is NoLocationPermissionException
  }

  override fun hashCode(): Int {
    return NoLocationPermissionException::class.toString().hashCode()
  }
}