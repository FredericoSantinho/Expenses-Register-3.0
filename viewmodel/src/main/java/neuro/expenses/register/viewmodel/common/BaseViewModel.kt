package neuro.expenses.register.viewmodel.common

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider

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
  ) {
    if (onError != null) {
      subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(
          { onSuccess.invoke(it) },
          { onError.invoke(it) }
        ).addToCompositeDisposable()
    } else {
      subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(
          { onSuccess.invoke(it) }
        ).addToCompositeDisposable()
    }
  }

  fun <T : Any> Single<T>.baseSubscribe(
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui(),
    onError: ((Throwable) -> Unit)? = null,
    onSuccess: (T) -> Unit
  ) {
    if (onError != null) {
      subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(onSuccess, onError).addToCompositeDisposable()
    } else {
      subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(onSuccess).addToCompositeDisposable()
    }
  }

  fun <T : Any> Observable<T>.baseSubscribe(
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui(),
    onError: ((Throwable) -> Unit)? = null,
    onSuccess: (T) -> Unit
  ) {
    if (onError != null) {
      subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(
          { onSuccess.invoke(it) },
          { onError.invoke(it) }
        ).addToCompositeDisposable()
    } else {
      subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(
          { onSuccess.invoke(it) }
        ).addToCompositeDisposable()
    }
  }

  fun Completable.baseSubscribe(
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui(),
    onError: ((Throwable) -> Unit)? = null,
    onComplete: () -> Unit
  ) {
    if (onError != null) {
      subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(
          { onComplete() },
          { onError.invoke(it) }
        ).addToCompositeDisposable()
    } else {
      subscribeOn(subscribeOn)
        .run {
          observeOn(observeOn)
        }
        .subscribe(
          { onComplete() }
        ).addToCompositeDisposable()
    }
  }

  private fun Disposable.addToCompositeDisposable() {
    disposable.add(this)
  }
}