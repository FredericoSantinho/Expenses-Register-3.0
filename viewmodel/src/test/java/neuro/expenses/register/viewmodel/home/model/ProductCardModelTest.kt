package neuro.expenses.register.viewmodel.home.model

import org.junit.Assert
import org.junit.Test

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

    Assert.assertEquals(description, productCardModel.description)
    Assert.assertEquals(category, productCardModel.category)
    Assert.assertEquals(place, productCardModel.place)
    Assert.assertEquals(price, productCardModel.price)
    Assert.assertEquals(amount, productCardModel.amount, 0.0)
    Assert.assertEquals(iconUrl, productCardModel.iconUrl)
  }
}