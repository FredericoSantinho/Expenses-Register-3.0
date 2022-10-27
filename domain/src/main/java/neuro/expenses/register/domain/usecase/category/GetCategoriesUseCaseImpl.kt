package neuro.expenses.register.domain.usecase.category

import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable

class GetCategoriesUseCaseImpl : GetCategoriesUseCase {

  private val behaviorRelay: BehaviorRelay<List<String>> = BehaviorRelay.create()

  override fun getCategories(): Observable<List<String>> {
    behaviorRelay.accept(
      listOf(
        "aaa",
        "abb",
        "abc"
      )
    )
    return behaviorRelay
  }
}