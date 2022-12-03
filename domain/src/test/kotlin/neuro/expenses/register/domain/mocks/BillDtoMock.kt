package neuro.expenses.register.entity.mocks

import neuro.expenses.register.domain.dto.BillDto
import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.domain.dto.PlaceDto
import java.util.*

fun billDtoMock(
  id: Long = 1L,
  calendar: Calendar = Calendar.getInstance(),
  placeDto: PlaceDto = placeDtoMock(),
  billItemsDtos: List<BillItemDto> = billItemsDtoMock(),
  isOpen: Boolean = true,
  iconUrl: String = ""
): BillDto {
  return BillDto(id, placeDto, calendar, 1.0, billItemsDtos, iconUrl = iconUrl, isOpen = isOpen)
}