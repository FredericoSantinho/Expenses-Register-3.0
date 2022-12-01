package neuro.expenses.register.viewmodel.mock

import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.dto.ProductDto

fun placeProductMock(
  id: Long = 1L, product: ProductDto = productMock(id), category: CategoryDto = categoryDtoMock()
): PlaceProductDto {
  return PlaceProductDto(id, product, category, 1.0)
}

fun placeProductsMock(): List<PlaceProductDto> {
  return listOf(placeProductMock(1), placeProductMock(2), placeProductMock(3))
}
