package neuro.expenses.register.ui.manual.register.composable

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

  val isEmpty = number.text.isEmpty()

  val out = if (isEmpty) "" else number.text + ' ' + suffix
  val suffixOffset = if (isEmpty) 0 else suffix.length + 1

  val numberOffsetTranslator = object : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
      return offset + suffixOffset
    }

    override fun transformedToOriginal(offset: Int): Int {
      if (offset <= suffixOffset - 1) return suffixOffset
      return offset - suffixOffset
    }
  }

  return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}
