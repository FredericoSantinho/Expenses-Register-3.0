package neuro.expenses.register.ui.edit.place

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import neuro.expenses.register.viewmodel.edit.place.EditPlacesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EditPlacesComposable(editPlacesViewModel: EditPlacesViewModel = getViewModel()) {
  Text("EditPlacesComposable")
}