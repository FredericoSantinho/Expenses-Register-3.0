package neuro.expenses.register.ui.edit.category

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import neuro.expenses.register.viewmodel.edit.category.EditCategoriesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EditCategoriesComposable(editCategoriesViewModel: EditCategoriesViewModel = getViewModel()) {
  Text("EditCategoriesComposable")
}