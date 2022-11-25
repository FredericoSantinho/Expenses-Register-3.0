package neuro.expenses.register.ui.edit.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import neuro.expenses.register.common.koin.startKoinIfNeeded
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.ui.theme.grey_fog_lighter
import neuro.expenses.register.viewmodel.di.viewModelModule
import neuro.expenses.register.viewmodel.edit.category.EditCategoriesViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditCategoriesComposable(editCategoriesViewModel: EditCategoriesViewModel = getViewModel()) {
  Column(
    modifier = Modifier
      .background(color = grey_fog_lighter)
  ) {
    LazyVerticalGrid(
      modifier = Modifier
        .padding(4.dp)
        .fillMaxSize(),
      columns = GridCells.Fixed(2),
      verticalArrangement = Arrangement.spacedBy(8.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(editCategoriesViewModel.categories.value, key = {
        it.id
      }) { categoryModel ->
        CategoryComposable(categoryModel) { editCategoriesViewModel.onCategoryClick(it) }
      }
    }
  }
}

@Preview
@Composable
fun PreviewEditCategoriesComposable() {
  startKoinIfNeeded { modules(viewModelModule) }
  val editCategoriesViewModel = EditCategoriesViewModel()

  ExpensesRegisterTheme {
    EditCategoriesComposable(editCategoriesViewModel)
  }
}