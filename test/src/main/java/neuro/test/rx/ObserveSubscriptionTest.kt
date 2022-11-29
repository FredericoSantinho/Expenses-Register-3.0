package neuro.test.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import neuro.test.OffsetIncrementer
import org.junit.jupiter.api.Assertions.assertTrue

open class ObserveSubscriptionTest(private val offsetIncrementer: OffsetIncrementer = OffsetIncrementer()) {
  private val map = mutableMapOf<Int, Boolean>()

  fun <T : Any> Observable<T>.observeSubscription(i: Int = 0, offset: Int = 0): Observable<T> {
    return doOnSubscribe { setSubscribed(i + offset) }
  }

  fun <T : Any> Single<T>.observeSubscription(i: Int = 0, offset: Int = 0): Single<T> {
    return doOnSubscribe { setSubscribed(i + offset) }
  }

  fun <T : Any> Maybe<T>.observeSubscription(i: Int = 0, offset: Int = 0): Maybe<T> {
    return doOnSubscribe { setSubscribed(i + offset) }
  }

  fun Completable.observeSubscription(i: Int = 0, offset: Int = 0): Completable {
    return doOnSubscribe { setSubscribed(i + offset) }
  }

  private fun setSubscribed(i: Int) {
    map.put(i, true)
  }

  fun assertSubscription(i: Int = 0, offset: Int = 0) {
    if (map.contains(i + offset)) {
      assertTrue(map.get(i + offset)!!)
    } else {
      throw java.lang.IllegalArgumentException("Subscription $i not found!")
    }
  }

  fun assertSubscriptions(list: List<Int>, offset: Int = 0) {
    list.forEach { assertSubscription(it, offset) }
  }

  fun getAndIncrementOffset(): Int {
    return offsetIncrementer.getAndIncrement()
  }
}