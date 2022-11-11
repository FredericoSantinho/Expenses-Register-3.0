package neuro.expenses.register.viewmodel.home.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ProductCardModelTest {
  @Test
  fun test() {
    val description = "description"
    val category = "category"
    val place = "place"
    val price = "price"
    val amount = 1.0
    val iconUrl = "iconUrl"

    val productCardModel = ProductCardModel(description, category, place, price, amount, iconUrl)

    Assertions.assertEquals(description, productCardModel.description)
    Assertions.assertEquals(category, productCardModel.category)
    Assertions.assertEquals(place, productCardModel.place)
    Assertions.assertEquals(price, productCardModel.price)
    Assertions.assertEquals(amount, productCardModel.amount, 0.0)
    Assertions.assertEquals(iconUrl, productCardModel.iconUrl)
  }
}