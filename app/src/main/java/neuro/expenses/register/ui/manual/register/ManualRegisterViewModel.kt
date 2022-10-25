package neuro.expenses.register.ui.manual.register

import androidx.lifecycle.ViewModel

class ManualRegisterViewModel : ViewModel() {
  fun register(
    description: String,
    category: String,
    place: String,
    price: String,
    amount: String
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