package neuro.expenses.register.domain.dto

data class BillItemDto(val product: ProductDto, val place: String, val amount: Double)