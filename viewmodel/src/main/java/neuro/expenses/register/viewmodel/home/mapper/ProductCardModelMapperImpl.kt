package neuro.expenses.register.viewmodel.home.mapper

import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.viewmodel.common.formatter.DecimalFormatter
import neuro.expenses.register.viewmodel.common.mapper.toViewmodel
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import java.util.*

class ProductCardModelMapperImpl(
  private val decimalFormatter: DecimalFormatter,
  private val currency: String
) : ProductCardModelMapper {
  override fun map(placeProductDto: PlaceProductDto, place: String): ProductCardModel {
    val productId = placeProductDto.id
    val description = placeProductDto.productDto.description
    val category = placeProductDto.category
    val price = decimalFormatter.format(placeProductDto.price) + ' ' + currency
    val iconUrl = placeProductDto.productDto.iconUrl

    return ProductCardModel(
      productId,
      description,
      category.toViewmodel(),
      place,
      price,
      iconUrl
    )
  }

  override fun map(productCardModel: ProductCardModel, calendar: Calendar): ExpenseDto {
    return ExpenseDto(
      productCardModel.description,
      productCardModel.categoryModel.name.value,
      productCardModel.place,
      productCardModel.price.substring(0, productCardModel.price.length - 2).toDouble(),
      productCardModel.amount,
      calendar
    )
  }
}