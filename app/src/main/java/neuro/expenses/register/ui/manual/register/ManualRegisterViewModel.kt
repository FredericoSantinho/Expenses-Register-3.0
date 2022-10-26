package neuro.expenses.register.ui.manual.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import neuro.expenses.register.ui.manual.register.composable.datetime.Date
import neuro.expenses.register.ui.manual.register.composable.datetime.Time

class ManualRegisterViewModel : ViewModel() {

  var description = mutableStateOf("")
  var category = mutableStateOf("")
  var place = mutableStateOf("")
  var price = mutableStateOf("")
  var amount = mutableStateOf("")

  fun getCategories(): List<String> {
    return listOf(
      "aaa",
      "abb",
      "abc"
    )
  }

  fun onNearestPlaceButton() {
    place.value = getNearestPlace()
  }

  fun onRegisterButton(time: Time, date: Date) {
    register(time, date)
  }

  private fun register(
    time: Time,
    date: Date
  ) {

  }

  private fun getNearestPlace(): String {
    return "teste"
  }
}