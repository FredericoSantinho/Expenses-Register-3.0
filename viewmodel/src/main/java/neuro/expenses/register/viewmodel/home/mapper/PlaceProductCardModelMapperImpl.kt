package neuro.expenses.register.viewmodel.home.mapper

import neuro.expenses.register.domain.dto.ExpenseDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.viewmodel.common.formatter.CurrencyFormatter
import neuro.expenses.register.viewmodel.home.model.PlaceProductCardModel
import neuro.expenses.register.viewmodel.mapper.toViewmodel
import java.util.*

class PlaceProductCardModelMapperImpl(private val currencyFormatter: CurrencyFormatter) :
  PlaceProductCardModelMapper {
  override fun map(placeProductDto: PlaceProductDto, place: String): PlaceProductCardModel {
    val placeProductId = placeProductDto.id
    val description = placeProductDto.productDto.description
    val category = placeProductDto.category
    val price = currencyFormatter.format(placeProductDto.price)
    val iconUrl = placeProductDto.productDto.iconUrl

    return PlaceProductCardModel(
      placeProductId,
      description,
      category.toViewmodel(),
      place,
      price,
      iconUrl
    )
  }

  override fun map(placeProductCardModel: PlaceProductCardModel, calendar: Calendar): ExpenseDto {
    return ExpenseDto(
      placeProductCardModel.description,
      placeProductCardModel.categoryModel.name,
      placeProductCardModel.place,
      placeProductCardModel.price.substring(0, placeProductCardModel.price.length - 2).toDouble(),
      placeProductCardModel.amount,
      calendar
    )
  }
}