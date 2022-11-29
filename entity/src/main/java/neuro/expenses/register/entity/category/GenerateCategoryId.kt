package neuro.expenses.register.entity.category

import io.reactivex.rxjava3.core.Single

interface GenerateCategoryId {
  fun newId(): Single<Long>
}