package neuro.expenses.register.viewmodel.common

import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import neuro.expenses.register.viewmodel.common.schedulers.SchedulerProvider

open class BaseViewModelModule(
  private val schedulerProvider: SchedulerProvider
) {

  protected val disposable = CompositeDisposable()

  fun onCleared() {
    disposable.clear()
  }

  fun <T> Maybe<T>.baseSubscribe(
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui(),
    onError: ((Throwable) -> Unit)? = null,
    onSuccess: (T) -> Unit
  ) {
    onError?.let {
      subscribeOn(subscribeOn).observeOn(observeOn)
        .subscribe({ onSuccess.invoke(it) }, { onError.invoke(it) }).addToCompositeDisposable()
    } ?: subscribeOn(subscribeOn).observeOn(observeOn).subscribe({ onSuccess.invoke(it) })
      .addToCompositeDisposable()
  }

  fun <T : Any> Single<T>.baseSubscribe(
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui(),
    onError: ((Throwable) -> Unit)? = null,
    onSuccess: (T) -> Unit
  ) {
    onError?.let {
      subscribeOn(subscribeOn).observeOn(observeOn).subscribe(onSuccess, onError)
        .addToCompositeDisposable()
    } ?: subscribeOn(subscribeOn).observeOn(observeOn).subscribe(onSuccess)
      .addToCompositeDisposable()
  }

  fun <T : Any> Observable<T>.baseSubscribe(
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui(),
    onError: ((Throwable) -> Unit)? = null,
    onSuccess: (T) -> Unit
  ) {
    onError?.let {
      subscribeOn(subscribeOn).observeOn(observeOn)
        .subscribe({ onSuccess.invoke(it) }, { onError.invoke(it) }).addToCompositeDisposable()
    } ?: subscribeOn(subscribeOn).observeOn(observeOn).subscribe({ onSuccess.invoke(it) })
      .addToCompositeDisposable()
  }

  fun Completable.baseSubscribe(
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui(),
    onError: ((Throwable) -> Unit)? = null,
    onComplete: () -> Unit
  ) {
    onError?.let {
      subscribeOn(subscribeOn).observeOn(observeOn)
        .subscribe({ onComplete() }, { onError.invoke(it) }).addToCompositeDisposable()
    } ?: subscribeOn(subscribeOn).observeOn(observeOn).subscribe({ onComplete() })
      .addToCompositeDisposable()
  }

  private fun Disposable.addToCompositeDisposable() {
    disposable.add(this)
  }
}
