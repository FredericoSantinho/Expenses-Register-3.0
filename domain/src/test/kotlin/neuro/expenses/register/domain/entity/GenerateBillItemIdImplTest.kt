package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.bill.GenerateBillItemIdRepository
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

internal class GenerateBillItemIdImplTest {
  @Test
  fun test() {
    val generateBillItemIdRepository = mock<GenerateBillItemIdRepository>()
    val generateBillItemId = GenerateBillItemIdImpl(generateBillItemIdRepository)

    whenever(generateBillItemIdRepository.newId()).thenReturn(Single.just(1L))

    verifyNoInteractions(generateBillItemIdRepository)
    generateBillItemId.newId().test().assertValue(1L).assertNoErrors().assertComplete()
    verify(generateBillItemIdRepository, times(1)).newId()
  }
}