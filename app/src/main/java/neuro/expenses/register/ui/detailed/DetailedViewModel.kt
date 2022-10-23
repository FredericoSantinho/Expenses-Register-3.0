package neuro.expenses.register.ui.detailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailedViewModel : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is detailed fragment"
  }
  val text: LiveData<String> = _text
}