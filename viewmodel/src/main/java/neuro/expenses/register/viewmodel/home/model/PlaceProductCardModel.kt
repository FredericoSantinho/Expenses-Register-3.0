package neuro.expenses.register.viewmodel.home.model

import neuro.expenses.register.viewmodel.model.CategoryModel

data class PlaceProductCardModel(
  val id: Long,
  val description: String,
  val categoryModel: CategoryModel,
  val place: String,
  val price: String,
  val iconUrl: String,
  val amount: Double = 1.0
)