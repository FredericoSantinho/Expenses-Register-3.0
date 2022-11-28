package neuro.expenses.register.mocks.placeproduct

import neuro.expenses.register.viewmodel.home.model.PlaceProductCardModel
import neuro.expenses.register.viewmodel.model.CategoryModel

fun placeProductCardModelMock(): PlaceProductCardModel {
  val description = "Tosta Mista Pão Caseiro"
  val categoryModel = CategoryModel(1L, "Restau", "")
  val place = "Riviera"
  val price = "4.20 €"
  val amount = 1.0
  val iconUrl =
    "https://www.iguaria.com/wp-content/uploads/2016/03/Iguaria_Tosta-de-Bacon-Queijo-Fiambre.jpg"

  return PlaceProductCardModel(0, description, categoryModel, place, price, iconUrl, amount)
}
