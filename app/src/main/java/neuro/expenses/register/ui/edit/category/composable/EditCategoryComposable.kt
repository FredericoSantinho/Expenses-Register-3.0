package neuro.expenses.register.ui.edit.category.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import neuro.expenses.register.ui.home.EditCategoryViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EditCategoryComposable(editCategoryViewModel: EditCategoryViewModel = getViewModel()) {
  Text("EditCategoryComposable")
}