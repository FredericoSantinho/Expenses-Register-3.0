package neuro.expenses.register.ui.manual.register

import androidx.lifecycle.ViewModel
import neuro.expenses.register.ui.manual.register.composable.datetime.Date
import neuro.expenses.register.ui.manual.register.composable.datetime.Time

class ManualRegisterViewModel : ViewModel() {
  fun register(
    description: String,
    category: String,
    place: String,
    price: String,
    amount: String,
    time: Time,
    date: Date
  ) {

  }

  fun getCategories(): List<String> {
    return listOf(
      "aaa",
      "abb",
      "abc"
    )
  }

  fun getNearestPlace(): String {
    return "teste"
  }
}