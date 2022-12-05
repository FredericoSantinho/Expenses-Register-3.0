package neuro.expenses.register.domain.repository.place

import io.reactivex.rxjava3.core.Observable
import neuro.expenses.register.domain.dto.PlaceDto

interface ObservePlacesRepository {
  /**
   * Observe all places.
   *
   * @return Observable that will emit a list with all places each time places change.
   */
  fun observePlaces(): Observable<List<PlaceDto>>
}