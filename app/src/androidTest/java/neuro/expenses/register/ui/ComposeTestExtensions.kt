package neuro.expenses.register.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.performTouchInput
import org.junit.Assert

fun SemanticsNodeInteraction.performLongClick(): SemanticsNodeInteraction {
  return performTouchInput { longClick() }
}

/**
 * This assertion is highly unreliable as it captures a portion of the screen to check if a certain
 * color appears or not. Still, currently it seems our best shot to get the "background color" .
 */
private fun SemanticsNodeInteraction.assertColor(
  tint: Color,
  b: Boolean = true
): SemanticsNodeInteraction {
  val array = IntArray(10 * 10)
  val captureToImage = captureToImage()
  captureToImage.readPixels(
    array,
    startY = captureToImage.height / 2,
    startX = captureToImage.width / 2,
    height = Math.min(10, captureToImage.height / 2),
    width = Math.min(10, captureToImage.width / 2)
  )
  val any = array.any { tint.convert(ColorSpaces.Srgb).hashCode() == it }
  Assert.assertTrue(any == b)
  return this
}

/**
 * This assertion is highly unreliable as it captures a portion of the screen to check if a certain
 * color appears or not. Still, currently it seems our best shot to get the "background color" .
 */
fun SemanticsNodeInteraction.assertContainsColor(tint: Color): SemanticsNodeInteraction {
  return assertColor(tint, true)
}

/**
 * This assertion is highly unreliable as it captures a portion of the screen to check if a certain
 * color appears or not. Still, currently it seems our best shot to get the "background color" .
 */
fun SemanticsNodeInteraction.assertNotContainsColor(tint: Color): SemanticsNodeInteraction {
  return assertColor(tint, false)
}