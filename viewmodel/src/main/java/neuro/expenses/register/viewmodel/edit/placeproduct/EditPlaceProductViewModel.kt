package neuro.expenses.register.viewmodel.edit.placeproduct

import androidx.compose.runtime.mutableStateOf
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.*
import neuro.expenses.register.domain.usecase.category.ObserveCategoriesUseCase
import neuro.expenses.register.domain.usecase.place.RemovePlaceProductUseCase
import neuro.expenses.register.domain.usecase.place.UpdatePlaceProductUseCase
import neuro.expenses.register.viewmodel.common.BaseViewModelModule
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider

class EditPlaceProductViewModel(
  observeCategoriesUseCase: ObserveCategoriesUseCase,
  private val updatePlaceProductUseCase: UpdatePlaceProductUseCase,
  private val removePlaceProductUseCase: RemovePlaceProductUseCase,
  private val schedulerProvider: SchedulerProvider
) : BaseViewModelModule(schedulerProvider) {
  val placeDto = mutableStateOf(PlaceDto(-1, "", emptyList(), LatLngDto(0.0, 0.0)))

  val productId = mutableStateOf(0L)
  val placeProductId = mutableStateOf(0L)
  var currentDescription = ""
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
      updatePlaceProductUseCase.updatePlaceProduct(placeDto.value, productDto)
    }.baseSubscribe { onFinishEditAction.value() }
  }

  fun onDeleteButton() {
    removePlaceProductUseCase.removePlaceProduct(placeDto.value, placeProductId.value)
      .baseSubscribe { onFinishEditAction.value() }
  }

  fun set(placeDto: PlaceDto, placeProductDto: PlaceProductDto, onFinishEditAction: () -> Unit) {
    this.placeDto.value = placeDto
    placeProductId.value = placeProductDto.id
    currentDescription = placeProductDto.productDto.description
    description.value = placeProductDto.productDto.description
    category.value = placeProductDto.category.name
    price.value = placeProductDto.price.toString()
    iconUrl.value = placeProductDto.productDto.iconUrl
    variableAmount.value = placeProductDto.productDto.variableAmount
    this.onFinishEditAction.value = onFinishEditAction
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
