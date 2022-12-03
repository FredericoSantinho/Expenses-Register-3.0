package neuro.expenses.register.domain.usecase.populate

import io.reactivex.rxjava3.core.Completable
import neuro.test.rx.Incrementer
import neuro.test.rx.ObserveSubscriptionTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class PrePopulateImplTest() : ObserveSubscriptionTest() {
  @Test
  fun prePopulate() {
    val incrementer = Incrementer()
    val offset = getAndIncrementOffset()

    val populatePlaces = mock<PopulatePlaces>()
    val populateExpenses = mock<PopulateExpenses>()

    val prePopulate = PrePopulateImpl(populatePlaces, populateExpenses)


    whenever(populatePlaces.populatePlaces()).thenReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )
    whenever(populateExpenses.populateExpenses()).thenReturn(
      Completable.complete().observeSubscription(incrementer, offset)
    )

    prePopulate.prePopulate()
      .test()
      .assertComplete()
      .assertNoErrors()

    assertSubscriptions(incrementer.getAll(), offset)
  }
}
