package neuro.expenses.register.entity.controller.category

import io.reactivex.rxjava3.core.Single

interface GenerateCategoryId {
  fun newId(): Single<Long>
}