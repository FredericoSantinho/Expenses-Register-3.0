package neuro.expenses.register.domain.mapper

import neuro.expenses.register.entity.mocks.productDtoMock
import neuro.expenses.register.entity.test.mocks.productMock
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ProductMapperTest {
  @Test
  fun test() {
    val product = productMock(33)
    val products = listOf(product)
    val productDto = productDtoMock(33)
    val productDtos = listOf(productDto)

    assertEquals(productDto, product.toDomain())
    assertEquals(product, productDto.toEntity())
    assertEquals(productDtos, products.toDomain())
    assertEquals(products, productDtos.toEntity())
  }
}