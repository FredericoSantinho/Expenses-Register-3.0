package neuro.expenses.register.presentation.common.alert

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun AlertDialog(
    title: String,
    text: String = "",
    confirmButtonText: String = "",
    onConfirmButton: () -> Unit = {},
    dismissButtonText: String = "",
    onDismissButton: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {

        androidx.compose.material.AlertDialog(
            modifier = modifier,
            onDismissRequest = { onDismissRequest() },
            title = {
                Text(text = title)
            },
            text = {
                Text(text)
            },
            confirmButton = {
                if (confirmButtonText.isNotEmpty()) {
                    Button(
                        onClick = { onConfirmButton() }) {
                        Text(confirmButtonText)
                    }
                }
            },
            dismissButton = {
                if (dismissButtonText.isNotEmpty()) {
                    Button(
                        onClick = { onDismissButton() }) {
                        Text(dismissButtonText)
                    }
                }
            },
            backgroundColor = MaterialTheme.colors.surface
        )
    }
}
