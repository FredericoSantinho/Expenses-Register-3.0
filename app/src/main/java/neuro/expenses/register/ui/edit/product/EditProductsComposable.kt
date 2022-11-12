package neuro.expenses.register.ui.edit.product

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import neuro.expenses.register.viewmodel.edit.product.EditProductsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EditProductsComposable(editProductsViewModel: EditProductsViewModel = getViewModel()) {
  Text("EditProductsComposable")
}