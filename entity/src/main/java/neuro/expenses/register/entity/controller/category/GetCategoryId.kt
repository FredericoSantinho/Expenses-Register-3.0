package neuro.expenses.register.entity.controller.category

import io.reactivex.rxjava3.core.Single

interface GetCategoryId {
  fun getCategoryId(nameLowercase: String): Single<Long>
}