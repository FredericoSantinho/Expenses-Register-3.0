package neuro.expenses.register.entity.category

import io.reactivex.rxjava3.core.Single

interface GenerateCategoryId {
  /**
   * Generate a new Category id.
   *
   * The id returned must be consumed (ie, saved) in order to generate a new one. This means
   * repeated calls to this method will return the same value if it is not consumed between them.
   *
   * @return Single with the new id.
   */
  fun newId(): Single<Long>
}