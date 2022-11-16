package neuro.expenses.register.viewmodel.edit.product

import androidx.compose.runtime.mutableStateOf
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.dto.ProductDto
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCase
import neuro.expenses.register.domain.usecase.place.RemovePlaceProductUseCase
import neuro.expenses.register.domain.usecase.place.UpdatePlaceProductUseCase
import neuro.expenses.register.viewmodel.common.model.CategoryModel
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider

class EditPlaceProductViewModel(
  observeCategoriesUseCase: ObserveCategoriesUseCase,
  private val updatePlaceProductUseCase: UpdatePlaceProductUseCase,
  private val removePlaceProductUseCase: RemovePlaceProductUseCase,
  private val schedulerProvider: SchedulerProvider
) {
  val placeId = mutableStateOf(0L)
  val productId = mutableStateOf(0L)
  val description = mutableStateOf("")
  val categoryModel = mutableStateOf(CategoryModel(-1L, ""))
  val price = mutableStateOf("")
  val iconUrl = mutableStateOf("")

  val categories = observeCategoriesUseCase.observeCategories()
  val categoriesNames =
    categories.flatMapSingle { Single.just(it).flattenAsObservable { it }.map { it.name }.toList() }

  fun onDescriptionChange() {

  }

  fun onCategoryChange() {

  }

  fun onSaveButton() {
    buildProductDto().flatMapCompletable { productDto ->
      updatePlaceProductUseCase.updatePlaceProduct(productDto)
    }.subscribeOn(schedulerProvider.io()).subscribe()
  }

  fun onDeleteButton() {
    removePlaceProductUseCase.removePlaceProduct(placeId.value, productId.value)
      .subscribeOn(schedulerProvider.io()).subscribe()
  }

  private fun buildProductDto(): Single<ProductDto> {
    return getCategory().map { categoryDto ->
      ProductDto(
        productId.value,
        description.value,
        categoryDto,
        price.value.toDouble(),
        0.0,
        iconUrl.value,
        placeId.value
      )
    }
  }

  private fun getCategory(): Single<CategoryDto> {
    return categories.flatMapIterable { it }.filter { it.name == categoryModel.value.name.value }
      .firstOrError()
  }
}