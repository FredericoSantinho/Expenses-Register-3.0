package neuro.expenses.register.entity.controller.category

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.Category

interface GetCategory {
  fun getCategory(nameLowercase: String): Maybe<Category>
}