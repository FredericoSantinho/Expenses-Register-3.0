package neuro.expenses.register.mocks.category

import neuro.expenses.register.viewmodel.model.CategoryModel

class CategoryModelMock {
  fun createCategoryModel(): CategoryModel {
    return CategoryModel(1, "Super", "")
  }
}