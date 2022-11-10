package neuro.expenses.register.ui.home.mapper

import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.ui.common.formatter.DoubleFormatter
import neuro.expenses.register.ui.home.model.ProductCardModel

class ProductCardModelMapperImpl(
  private val doubleFormatter: DoubleFormatter,
  private val currency: String
) : ProductCardModelMapper {
  override fun map(productDto: ProductDto, place: String): ProductCardModel {
    val description = productDto.description
    val category = productDto.category
    val price = doubleFormatter.format(productDto.price) + ' ' + currency
    val amount = productDto.defaultAmount

    return ProductCardModel(description, category, place, price, amount, productDto.iconUrl)
  }
}