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
    onSuccess: (T) -> Unit,
    onError: ((Throwable) -> Unit)? = null,
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui()
  ) {
    onError?.let {
      subscribeOn(subscribeOn).observeOn(observeOn)
        .subscribe({ onSuccess.invoke(it) }, { onError.invoke(it) }).addToCompositeDisposable()
    } ?: subscribeOn(subscribeOn).observeOn(observeOn).subscribe({ onSuccess.invoke(it) })
      .addToCompositeDisposable()
  }

  fun <T : Any> Single<T>.baseSubscribe(
    onSuccess: (T) -> Unit,
    onError: ((Throwable) -> Unit)? = null,
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui()
  ) {
    onError?.let {
      subscribeOn(subscribeOn).observeOn(observeOn).subscribe(onSuccess, onError)
        .addToCompositeDisposable()
    } ?: subscribeOn(subscribeOn).observeOn(observeOn).subscribe(onSuccess)
      .addToCompositeDisposable()
  }

  fun <T : Any> Observable<T>.baseSubscribe(
    onSuccess: (T) -> Unit,
    onError: ((Throwable) -> Unit)? = null,
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui()
  ) {
    onError?.let {
      subscribeOn(subscribeOn).observeOn(observeOn)
        .subscribe({ onSuccess.invoke(it) }, { onError.invoke(it) }).addToCompositeDisposable()
    } ?: subscribeOn(subscribeOn).observeOn(observeOn).subscribe({ onSuccess.invoke(it) })
      .addToCompositeDisposable()
  }

  fun Completable.baseSubscribe(
    onComplete: () -> Unit,
    onError: ((Throwable) -> Unit)? = null,
    subscribeOn: Scheduler = schedulerProvider.io(),
    observeOn: Scheduler = schedulerProvider.ui()
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
