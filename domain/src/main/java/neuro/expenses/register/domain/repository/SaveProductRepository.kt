package neuro.expenses.register.domain.repository

interface SaveProductRepository {
  fun saveProduct(
    description: String,
    category: String,
    price: Double
  ): Long
}