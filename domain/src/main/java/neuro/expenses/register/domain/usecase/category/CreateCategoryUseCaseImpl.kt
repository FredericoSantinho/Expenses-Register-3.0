package neuro.expenses.register.domain.usecase.category

import io.reactivex.rxjava3.core.Completable
import neuro.expenses.register.domain.dto.CategoryDto
import neuro.expenses.register.domain.repository.category.CreateCategoryRepository
import neuro.expenses.register.entity.controller.category.GenerateCategoryId

class CreateCategoryUseCaseImpl(
  private val createCategoryRepository: CreateCategoryRepository,
  private val generateCategoryId: GenerateCategoryId
) : CreateCategoryUseCase {
  override fun createCategory(name: String, iconUrl: String): Completable {
    return generateCategoryId.newId().flatMapCompletable { newCategoryId ->
      createCategoryRepository.createCategory(
        CategoryDto(
          newCategoryId, name, iconUrl
        )
      )
    }
  }
}