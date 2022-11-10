package neuro.expenses.register.viewmodel.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import neuro.expenses.register.common.livedata.SingleLiveEvent

fun <T> MutableState<T>.asState(): State<T> =
  this

fun <T> SingleLiveEvent<T>.asLiveData(): LiveData<T> =
  this

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> =
  this