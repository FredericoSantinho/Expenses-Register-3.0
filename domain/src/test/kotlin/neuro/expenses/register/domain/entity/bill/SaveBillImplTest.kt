package neuro.expenses.register.domain.entity.bill

import neuro.expenses.register.domain.mapper.toDomain
import neuro.expenses.register.domain.repository.bill.SaveBillRepository
import neuro.expenses.register.entity.test.mocks.billMock
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions

internal class SaveBillImplTest {
  @Test
  fun saveBill() {
    val saveBillRepository = mock<SaveBillRepository>()

    val saveBillImpl = SaveBillImpl(saveBillRepository)

    val bill = billMock()

    verifyNoInteractions(saveBillRepository)

    saveBillImpl.saveBill(bill)

    verify(saveBillRepository, times(1)).saveBill(bill.toDomain())
  }
}
