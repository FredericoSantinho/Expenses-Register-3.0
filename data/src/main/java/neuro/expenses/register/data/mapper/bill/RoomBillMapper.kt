package neuro.expenses.register.data.mapper.bill

import neuro.expenses.register.data.model.bill.RoomBill
import neuro.expenses.register.domain.dto.BillDto

fun BillDto.toData(): RoomBill = RoomBill(id, place.id, calendar, total, isOpen, iconUrl)
