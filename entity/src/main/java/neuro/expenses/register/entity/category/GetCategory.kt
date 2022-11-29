package neuro.expenses.register.entity.category

import io.reactivex.rxjava3.core.Maybe
import neuro.expenses.register.entity.model.Category

interface GetCategory {
  fun getCategory(nameLowercase: String): Maybe<Category>
}