package neuro.expenses.register.viewmodel.common.model

import androidx.compose.runtime.mutableStateOf

data class CategoryModel(private val _id: Long, private val _name: String) {
  val id = mutableStateOf(_id)
  val name = mutableStateOf(_name)
}