package neuro.expenses.register.domain.usecase.expense


class RegisterExpenseException(val errors: List<RegisterExpenseError>) :
  java.lang.IllegalArgumentException() {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as RegisterExpenseException

    if (!errors.equals(other.errors)) return false

    return true
  }

  override fun hashCode(): Int {
    return errors.hashCode()
  }
}