package neuro.expenses.register.domain.repository.place

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.PlaceDto

interface ObservePlacesRepository {
  fun observePlaces(): Observable<List<PlaceDto>>
}