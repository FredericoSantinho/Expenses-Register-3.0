package neuro.expenses.register.viewmodel.home.mapper

import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatter
import neuro.expenses.register.viewmodel.home.model.ProductCardModel
import neuro.expenses.register.viewmodel.mapper.toViewmodel
import java.util.*

class ProductCardModelMapperImpl(private val currencyFormatter: CurrencyFormatter) :
  ProductCardModelMapper {
  override fun map(placeProductDto: PlaceProductDto, place: String): ProductCardModel {
    val productId = placeProductDto.id
    val description = placeProductDto.productDto.description
    val category = placeProductDto.category
    val price = currencyFormatter.format(placeProductDto.price)
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
      productCardModel.categoryModel.name,
      productCardModel.place,
      productCardModel.price.substring(0, productCardModel.price.length - 2).toDouble(),
      productCardModel.amount,
      calendar
    )
  }
}