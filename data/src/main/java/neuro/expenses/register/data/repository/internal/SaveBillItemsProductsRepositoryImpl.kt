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
      val productDto = billItemDto.product
      val amount = billItemDto.amount

      val description = productDto.description
      val category = productDto.category
      val price = productDto.price
      val placeProductId = saveProductRepository.saveProduct(description, category, price, amount)

      roomBillItemMapper.map(billItemDto, billId, placeProductId)
    }
  }
}