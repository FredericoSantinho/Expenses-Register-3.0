package neuro.expenses.register.domain.usecase.register.validator

import neuro.expenses.register.domain.dto.BillItemDto
import neuro.expenses.register.domain.usecase.category.IsValidCategory

class BillItemValidatorImpl(val isValidCategory: IsValidCategory) : BillItemValidator {
  override fun validate(billItemDto: BillItemDto): List<RegisterExpenseError> {
    val errors = mutableListOf<RegisterExpenseError>()

    if (billItemDto.product.description.isBlank()) {
      errors.add(RegisterExpenseError.EMPTY_DESCRIPTION)
    }
    if (!isValidCategory.isValidCategory(billItemDto.product.category)) {
      errors.add(RegisterExpenseError.INVALID_CATEGORY)
    }
    if (billItemDto.place.isBlank()) {
      errors.add(RegisterExpenseError.EMPTY_PLACE)
    }

    return errors
  }
}