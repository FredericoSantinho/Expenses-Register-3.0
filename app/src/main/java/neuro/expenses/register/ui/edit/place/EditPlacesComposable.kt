package neuro.expenses.register.ui.edit.place

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import neuro.expenses.register.viewmodel.edit.place.EditPlacesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun EditPlacesComposable(editPlacesViewModel: EditPlacesViewModel = getViewModel()) {
  Column(modifier = Modifier.fillMaxSize()) {
    Text("EditPlacesComposable")
  }
}