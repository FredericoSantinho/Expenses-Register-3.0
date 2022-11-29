package neuro.expenses.register.domain.mapper

import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.entity.model.BillItem

fun BillItem.toDomain(): BillItemDto = BillItemDto(id, placeProduct.toDomain(), amount, total)
fun List<BillItem>.toDomain(): List<BillItemDto> = map { it.toDomain() }

fun BillItemDto.toEntity(): BillItem = BillItem(id, placeProductDto.toEntity(), amount, total)
fun List<BillItemDto>.toEntity(): List<BillItem> = map { it.toEntity() }
