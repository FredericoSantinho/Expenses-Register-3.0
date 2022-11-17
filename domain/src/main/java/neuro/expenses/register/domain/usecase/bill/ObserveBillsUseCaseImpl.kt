package neuro.expenses.register.domain.usecase.bill

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.repository.ObserveBillsRepository

class ObserveBillsUseCaseImpl(private val observeBillsRepository: ObserveBillsRepository) :
  ObserveBillsUseCase {
  override fun observeBills(): Observable<List<BillDto>> {
    return observeBillsRepository.observeBills()
  }
}