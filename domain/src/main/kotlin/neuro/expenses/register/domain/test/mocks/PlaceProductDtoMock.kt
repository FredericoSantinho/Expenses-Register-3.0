package neuro.expenses.register.entity.mocks

import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.dto.PlaceProductDto
import neuro.expenses.register.domain.dto.ProductDto

fun placeProductDtoMock(
  id: Long = 1L,
  productDto: ProductDto = productDtoMock(id),
  categoryDto: CategoryDto = categoryDtoMock()
): PlaceProductDto {
  return PlaceProductDto(id, productDto, categoryDto, 1.0)
}

fun placeProductsDtoMock(): List<PlaceProductDto> {
  return listOf(placeProductDtoMock(1), placeProductDtoMock(2), placeProductDtoMock(3))
}
