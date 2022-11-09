package neuro.expenses.register.common.viewmodel

import androidx.lifecycle.ViewModel
import com.exchangebot.common.schedulers.SchedulerProvider
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel(
  private val schedulerProvider: SchedulerProvider
) : ViewModel() {

  protected val disposable = CompositeDisposable()

  override fun onCleared() {
    super.onCleared()
    disposable.clear()
  }

  fun <T> Maybe<T>.baseSubscribe(
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui(),
    onError: ((Throwable) -> Unit)? = null,
    onSuccess: (T) -> Unit
  ): Disposable {
    if (onError != null) {
      return subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(
          { onSuccess.invoke(it) },
          { onError.invoke(it) }
        )
    } else {
      return subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(
          { onSuccess.invoke(it) }
        )
    }
  }

  fun <T : Any> Single<T>.baseSubscribe(
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui(),
    onError: ((Throwable) -> Unit)? = null,
    onSuccess: (T) -> Unit
  ): Disposable {
    if (onError != null) {
      return subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(onSuccess, onError)
    } else {
      return subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(onSuccess)
    }
  }

  fun <T : Any> Observable<T>.baseSubscribe(
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui(),
    onError: ((Throwable) -> Unit)? = null,
    onSuccess: (T) -> Unit
  ): Disposable {
    if (onError != null) {
      return subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(
          { onSuccess.invoke(it) },
          { onError.invoke(it) }
        )
    } else {
      return subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(
          { onSuccess.invoke(it) }
        )
    }
  }

  fun Completable.baseSubscribe(
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui(),
    onError: ((Throwable) -> Unit)? = null,
    onComplete: () -> Unit
  ): Disposable {
    if (onError != null) {
      return subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(
          { onComplete() },
          { onError.invoke(it) }
        )
    } else {
      return subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(
          { onComplete() }
        )
    }
  }
}
