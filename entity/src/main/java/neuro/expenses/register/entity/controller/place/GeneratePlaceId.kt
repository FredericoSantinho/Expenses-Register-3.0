package neuro.expenses.register.entity.controller.place

import io.reactivex.rxjava3.core.Single

interface GeneratePlaceId {
  fun newId(): Single<Long>
}