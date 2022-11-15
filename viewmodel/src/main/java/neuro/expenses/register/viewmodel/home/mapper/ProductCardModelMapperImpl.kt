package neuro.expenses.register.viewmodel.home.mapper

import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatter
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

class ProductCardModelMapperImpl(
  private val decimalFormatter: DecimalFormatter,
  private val currency: String
) : ProductCardModelMapper {
  override fun map(productDto: ProductDto, place: String): ProductCardModel {
    val productId = productDto.id
    val description = productDto.description
    val category = productDto.category
    val price = decimalFormatter.format(productDto.price) + ' ' + currency
    val amount = productDto.defaultAmount

    return ProductCardModel(
      productId,
      description,
      category.id,
      category.name,
      place,
      price,
      productDto.iconUrl
    )
  }

  override fun map(productCardModel: ProductCardModel, calendar: Calendar): ExpenseDto {
    return ExpenseDto(
      productCardModel.description,
      productCardModel.categoryName,
      productCardModel.place,
      productCardModel.price.substring(0, productCardModel.price.length - 2).toDouble(),
      productCardModel.amount,
      calendar
    )
  }
}