package neuro.expenses.register.viewmodel.home.model

import neuro.expenses.register.viewmodel.common.model.CategoryModel

data class ProductCardModel(
  val id: Long,
  val description: String,
  val categoryModel: CategoryModel,
  val place: String,
  val price: String,
  val iconUrl: String,
  val amount: Double = 1.0
)