package neuro.expenses.register.ui.edit.product.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import neuro.expenses.register.viewmodel.edit.product.EditProductViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EditProductComposable(editProductViewModel: EditProductViewModel = getViewModel()) {
  Text("EditProductComposable")
}