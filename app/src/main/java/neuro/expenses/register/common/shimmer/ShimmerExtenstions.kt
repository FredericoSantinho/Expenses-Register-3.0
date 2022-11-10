package neuro.expenses.register.common.viewmodel

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.valentinilk.shimmer.shimmer

fun Modifier.shimmerBackground(loading: Boolean): Modifier =
  if (loading) this.background(Color.LightGray) else this

fun Modifier.shimmer(loading: Boolean): Modifier =
  if (loading) this.shimmer() else this