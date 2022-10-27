package neuro.expenses.register.domain.usecase.near

interface GetNearestPlaceUseCase {
  /**
   * @return the nearest Place available based on current location.
   */
  fun getNearestPlace(): String
}