package neuro.expenses.register.common.back

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
fun DefaultBackHandler(
  backNavElement: BackNavElement
) = BackHandler {
  backNavElement.tryGoBack()
}
