package neuro.expenses.register.domain.usecase.populate

import io.reactivex.rxjava3.core.Completable

class PrePopulateImpl(
  private val populatePlaces: PopulatePlaces,
  private val populateExpenses: PopulateExpenses
) : PrePopulate {
  override fun prePopulate(): Completable {
    return populatePlaces.populatePlaces().andThen(populateExpenses.populateExpenses())
  }
}