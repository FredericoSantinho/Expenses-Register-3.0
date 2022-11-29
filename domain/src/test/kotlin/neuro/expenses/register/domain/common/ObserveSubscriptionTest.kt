package neuro.expenses.register.domain.common

import io.reactivex.rxjava3.core.Single
import kotlin.test.assertTrue

open class ObserveSubscriptionTest {
  private var subscribed = false

  fun <T> Single<T>.observeSubscription(): Single<T> {
    return doOnSubscribe { subscribed = true }
  }

  fun assertSubscription() {
    assertTrue(subscribed)
  }
}