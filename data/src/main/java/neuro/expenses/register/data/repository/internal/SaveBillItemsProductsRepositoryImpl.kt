package neuro.expenses.register.data.repository.internal

import neuro.expenses.register.data.mapper.bill.RoomBillItemMapper
import neuro.expenses.register.data.model.bill.RoomBillItem
import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.repository.SaveProductRepository

class SaveBillItemsProductsRepositoryImpl(
  private val roomBillItemMapper: RoomBillItemMapper,
  private val saveProductRepository: SaveProductRepository
) : SaveBillItemsProductsRepository {
  override fun saveBillItemsProducts(billDto: BillDto): List<RoomBillItem> {
    val billId = billDto.id
    return billDto.billItems.map { billItemDto ->
      val product = billItemDto.product

      val description = product.description
      val category = product.category
      val price = product.price
      val pricedProductId = saveProductRepository.saveProduct(description, category, price)

      roomBillItemMapper.map(billItemDto, billId, pricedProductId)
    }
  }
}