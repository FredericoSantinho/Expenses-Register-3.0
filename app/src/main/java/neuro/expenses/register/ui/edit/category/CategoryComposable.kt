package neuro.expenses.register.ui.edit.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import neuro.expenses.register.mocks.category.categoryModelMock
import neuro.expenses.register.ui.common.composables.image.AsyncImage
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.viewmodel.model.CategoryModel

@Composable
fun CategoryComposable(
  categoryModel: CategoryModel,
  onCategoryCardClick: (CategoryModel) -> Unit = {}
) {
  val roundedCornerShape = RoundedCornerShape(corner = CornerSize(8.dp))
  Card(
    modifier = Modifier
      .clip(roundedCornerShape)
      .requiredHeight(40.dp + 8.dp * 2)
      .clickable { onCategoryCardClick(categoryModel) },
    elevation = 2.dp,
    backgroundColor = Color.White,
    shape = roundedCornerShape
  ) {
    ConstraintLayout(
      Modifier
        .fillMaxWidth()
        .padding(start = 8.dp)
    ) {
      val (imageC, nameC) = createRefs()

      AsyncImage(modifier = Modifier
        .constrainAs(imageC) {
          start.linkTo(parent.start)
          linkTo(top = parent.top, bottom = parent.bottom)
        }
        .size(40.dp)
        .clip(RoundedCornerShape(corner = CornerSize(8.dp))), categoryModel.iconUrl)
      Text(
        categoryModel.name, modifier = Modifier.constrainAs(nameC) {
          start.linkTo(imageC.end, margin = 8.dp)
          linkTo(top = parent.top, bottom = parent.bottom)
        }, style = MaterialTheme.typography.h6
      )
    }
  }
}

@Preview
@Composable
fun PreviewCategoryComposable() {
  ExpensesRegisterTheme {
    CategoryComposable(
      categoryModelMock()
    )
  }
}
