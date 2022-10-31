package neuro.expenses.register.common.view.model

import androidx.lifecycle.ViewModel
import com.exchangebot.common.schedulers.SchedulerProvider
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel(
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun <T> Single<T>.baseSubscribe(
        subscribeOn: Scheduler = schedulerProvider.io(),
        observeOn: Scheduler = schedulerProvider.ui(),
        onError: ((Throwable) -> Unit)? = null,
        onSuccess: (T) -> Unit
    ): Disposable {
        return this.subscribeOn(subscribeOn)
            .run {
                if (observeOn != null) {
                    observeOn(observeOn)
                } else {
                    this
                }
            }
            .subscribe(
                { onSuccess.invoke(it) },
                { onError?.invoke(it) }
            )
    }

    fun <T> Observable<T>.baseSubscribe(
        subscribeOn: Scheduler = schedulerProvider.io(),
        observeOn: Scheduler = schedulerProvider.ui(),
        onError: ((Throwable) -> Unit)? = null,
        onSuccess: (T) -> Unit
    ): Disposable {
        return this.subscribeOn(subscribeOn)
            .run {
                if (observeOn != null) {
                    observeOn(observeOn)
                } else {
                    this
                }
            }
            .subscribe(
                { onSuccess.invoke(it) },
                { onError?.invoke(it) }
            )
    }

    fun Completable.baseSubscribe(
        subscribeOn: Scheduler = schedulerProvider.io(),
        observeOn: Scheduler = schedulerProvider.ui(),
        onError: ((Throwable) -> Unit)? = null,
        onComplete: () -> Unit
    ): Disposable {
        return this.subscribeOn(subscribeOn)
            .run {
                if (observeOn != null) {
                    observeOn(observeOn)
                } else {
                    this
                }
            }
            .subscribe(
                { onComplete() },
                { onError?.invoke(it) }
            )
    }
}
