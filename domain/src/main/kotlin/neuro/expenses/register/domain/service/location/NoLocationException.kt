package neuro.expenses.register.domain.service.location

class NoLocationException : IllegalStateException() {
  override fun equals(other: Any?): Boolean {
    return other is NoLocationException
  }

  override fun hashCode(): Int {
    return NoLocationException::class.toString().hashCode()
  }
}