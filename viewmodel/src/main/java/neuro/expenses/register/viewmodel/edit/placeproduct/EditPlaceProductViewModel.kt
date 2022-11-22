package neuro.expenses.register.viewmodel.edit.placeproduct

import androidx.compose.runtime.mutableStateOf
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCase
import neuro.expenses.register.domain.usecase.place.RemovePlaceProductUseCase
import neuro.expenses.register.domain.usecase.place.UpdatePlaceProductUseCase
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider

class EditPlaceProductViewModel(
  observeCategoriesUseCase: ObserveCategoriesUseCase,
  private val updatePlaceProductUseCase: UpdatePlaceProductUseCase,
  private val removePlaceProductUseCase: RemovePlaceProductUseCase,
  private val schedulerProvider: SchedulerProvider
) {
  val placeId = mutableStateOf(0L)
  val productId = mutableStateOf(0L)
  val placeProductId = mutableStateOf(0L)
  val description = mutableStateOf("")
  val category = mutableStateOf("")
  val price = mutableStateOf("")
  val iconUrl = mutableStateOf("")
  val variableAmount = mutableStateOf(false)
  val onFinishEditAction = mutableStateOf({ })

  val categories = observeCategoriesUseCase.observeCategories()
  val categoriesNames =
    categories.flatMapSingle { Single.just(it).flattenAsObservable { it }.map { it.name }.toList() }

  fun onDescriptionChange() {

  }

  fun onCategoryChange() {

  }

  fun onSaveButton() {
    buildProductDto().flatMapCompletable { productDto ->
      updatePlaceProductUseCase.updatePlaceProduct(placeId.value, productDto)
    }.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())
      .subscribe { onFinishEditAction.value() }
  }

  fun onDeleteButton() {
    removePlaceProductUseCase.removePlaceProduct(placeId.value, placeProductId.value)
      .subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())
      .subscribe { onFinishEditAction.value() }
  }

  private fun buildProductDto(): Single<PlaceProductDto> {
    return getCategory().map { categoryDto ->
      PlaceProductDto(
        placeProductId.value,
        ProductDto(productId.value, description.value, variableAmount.value, iconUrl.value),
        categoryDto,
        price.value.toDouble()
      )
    }
  }

  private fun getCategory(): Single<CategoryDto> {
    return categories.firstOrError().flattenAsObservable { it }.filter { it.name == category.value }
      .firstOrError()
  }
}
