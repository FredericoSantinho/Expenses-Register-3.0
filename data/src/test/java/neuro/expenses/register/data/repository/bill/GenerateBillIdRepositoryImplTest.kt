package neuro.expenses.register.data.repository.bill

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.data.dao.BillDao
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

internal class GenerateBillIdRepositoryImplTest {
  @Test
  fun testLastIdPresent() {
    val billDao = mock<BillDao>()
    val generateBillIdRepository = GenerateBillIdRepositoryImpl(billDao)

    whenever(billDao.getLastBillId()).thenReturn(Maybe.just(1L))

    verifyNoInteractions(billDao)

    generateBillIdRepository.newId().test().assertValue(2L)

    verify(billDao, times(1)).getLastBillId()
  }

  @Test
  fun testLastIdNotPresent() {
    val billDao = mock<BillDao>()
    val generateBillIdRepository = GenerateBillIdRepositoryImpl(billDao)

    whenever(billDao.getLastBillId()).thenReturn(Maybe.empty())

    generateBillIdRepository.newId().test().assertValue(1L)

    verify(billDao, times(1)).getLastBillId()
  }

}