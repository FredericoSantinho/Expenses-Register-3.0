package neuro.expenses.register.viewmodel.edit.product

import androidx.compose.runtime.mutableStateOf
import io.reactivex.rxjava3.core.Completable
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
  val place = mutableStateOf("")
  val productId = mutableStateOf(0L)
  val description = mutableStateOf("")
  val category = mutableStateOf("")
  val price = mutableStateOf("")
  val iconUrl = mutableStateOf("")

  val categories = observeCategoriesUseCase.observeCategories()

  fun onDescriptionChange() {

  }

  fun onCategoryChange() {

  }

  fun onSaveButton() {
    updatePlaceProductUseCase.updatePlaceProduct(buildProductDto(), place.value)
      .subscribeOn(schedulerProvider.io())
      .andThen(Completable.fromAction {
        println("")
      })
      .subscribe()
  }

  fun onDeleteButton() {
    removePlaceProductUseCase.removePlaceProduct(place.value, productId.value)
      .subscribeOn(schedulerProvider.io()).subscribe()
  }

  private fun buildProductDto(): ProductDto {
    return ProductDto(
      productId.value,
      description.value,
      category.value,
      price.value.toDouble(),
      0.0,
      iconUrl.value
    )
  }
}