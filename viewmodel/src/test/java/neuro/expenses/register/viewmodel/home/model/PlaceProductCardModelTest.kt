package neuro.expenses.register.viewmodel.home.model

import neuro.expenses.register.viewmodel.model.CategoryModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PlaceProductCardModelTest {
  @Test
  fun placeProductCardModel() {
    val id = 1L
    val description = "description"
    val categoryModel = CategoryModel(1L, "category", "")
    val place = "place"
    val price = "price"
    val amount = 1.0
    val iconUrl = "iconUrl"

    val placeProductCardModel =
      PlaceProductCardModel(id, description, categoryModel, place, price, iconUrl)

    Assertions.assertEquals(description, placeProductCardModel.description)
    Assertions.assertEquals("category", placeProductCardModel.categoryModel.name)
    Assertions.assertEquals(place, placeProductCardModel.place)
    Assertions.assertEquals(price, placeProductCardModel.price)
    Assertions.assertEquals(amount, placeProductCardModel.amount, 0.0)
    Assertions.assertEquals(iconUrl, placeProductCardModel.iconUrl)
  }
}