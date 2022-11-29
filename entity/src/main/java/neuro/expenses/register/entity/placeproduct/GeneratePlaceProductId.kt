package neuro.expenses.register.entity.placeproduct

import io.reactivex.rxjava3.core.Single

interface GeneratePlaceProductId {
  fun newId(): Single<Long>
}