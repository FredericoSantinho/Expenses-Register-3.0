package neuro.expenses.register.presentation.ui.common.composables.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import neuro.expenses.register.presentation.R

@Composable
fun SaveDeleteComposable(
  onSaveButton: () -> (Unit), onDeleteButton: () -> (Unit), modifier: Modifier = Modifier
) {
  Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
    Button(
      onClick = {
        onDeleteButton()
      }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
    ) {
      Text(stringResource(R.string.delete), color = Color.White)
    }
    Button(modifier = Modifier.padding(start = 64.dp), onClick = {
      onSaveButton()
    }) {
      Text(stringResource(R.string.save))
    }
  }
}