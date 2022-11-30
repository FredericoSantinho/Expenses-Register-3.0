package neuro.expenses.register.presentation.ui.manual.register.composable

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class SuffixTransformation(private val suffix: String) : VisualTransformation {
  override fun filter(text: AnnotatedString): TransformedText {
    return suffixFilter(text, suffix)
  }
}

fun suffixFilter(number: AnnotatedString, suffix: String): TransformedText {

  val out = if (number.text.isEmpty()) "" else number.text + ' ' + suffix

  val numberOffsetTranslator = object : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
      return offset
    }

    override fun transformedToOriginal(offset: Int): Int {
      return if (offset <= number.text.length) offset else number.text.length
    }
  }

  return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}
