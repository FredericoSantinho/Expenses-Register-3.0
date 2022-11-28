package neuro.expenses.register.domain.entity

import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.repository.bill.GenerateBillIdRepository
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

internal class GenerateBillIdImplTest {
  @Test
  fun test() {
    val generateBillIdRepository = mock<GenerateBillIdRepository>()

    val generateBillId = GenerateBillIdImpl(generateBillIdRepository)

    whenever(generateBillIdRepository.newId()).thenReturn(Single.just(1L))

    verifyNoInteractions(generateBillIdRepository)
    generateBillId.newId().test().assertValue(1L)
    verify(generateBillIdRepository, times(1)).newId()
  }
}