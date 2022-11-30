package neuro.expenses.register.presentation.ui.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import neuro.expenses.register.viewmodel.home.ProductsListViewModel

@Composable
fun ProductsListComposable(
  productsListViewModel: ProductsListViewModel,
  modifier: Modifier = Modifier
) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    verticalArrangement = Arrangement.spacedBy(4.dp),
    horizontalArrangement = Arrangement.spacedBy(4.dp),
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colors.surface)
  ) {
    items(productsListViewModel.products.value, key = {
      it.placeProductId
    }) { productCardViewModel ->
      PlaceProductCardComposable(productCardViewModel)
    }
  }
}
