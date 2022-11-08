package neuro.expenses.register.ui.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import neuro.expenses.register.ui.home.view.model.ProductsListViewModel

@Composable
fun ProductsListComposable(
  productsListViewModel: ProductsListViewModel
) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(productsListViewModel.products.value, key = {
      it.description
    }) { productCardViewModel ->
      ProductCardComposable(productCardViewModel)
    }
  }
}
