package neuro.expenses.register.ui.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import neuro.expenses.register.R
import neuro.expenses.register.common.compose.rememberUnit
import neuro.expenses.register.ui.edit.category.EditCategoriesComposable
import neuro.expenses.register.ui.edit.place.EditPlacesComposable
import neuro.expenses.register.ui.edit.placeproduct.EditPlaceProductsComposable
import neuro.expenses.register.ui.edit.product.EditProductsComposable
import neuro.expenses.register.viewmodel.edit.EditUiEvent.Destination
import neuro.expenses.register.viewmodel.edit.EditUiEvent.UiEvent
import neuro.expenses.register.viewmodel.edit.EditViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EditComposable(editViewModel: EditViewModel = getViewModel()) {
  rememberUnit { editViewModel.onComposition() }

  val uiEvent by editViewModel.uiEvent

  val pagerState = rememberPagerState()
  val coroutineScope = rememberCoroutineScope()

  val pages = editViewModel.pages
  Column {
    TabRow(
      selectedTabIndex = pagerState.currentPage,
      indicator = { tabPositions ->
        TabRowDefaults.Indicator(
          Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
        )
      }) {
      pages.forEachIndexed { index, directions ->
        Tab(
          text = {
            Text(
              getDirectionTitle(directions),
              style = MaterialTheme.typography.caption.copy(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
              )
            )
          },
          selected = pagerState.currentPage == index,
          onClick = { editViewModel.onPageClick(index) },
        )
      }
    }

    HorizontalPager(
      count = pages.size,
      state = pagerState,
    ) { page ->
      when (page) {
        0 -> EditCategoriesComposable()
        1 -> EditProductsComposable()
        2 -> EditPlaceProductsComposable()
        3 -> EditPlacesComposable()
      }
    }
    onUiEvent(uiEvent, editViewModel, pagerState, coroutineScope)
  }
}

@Composable
private fun getDirectionTitle(destination: Destination): String {
  return when (destination) {
    Destination.categories -> stringResource(R.string.edit_categories_title)
    Destination.products -> stringResource(R.string.edit_products_title)
    Destination.placeProducts -> stringResource(R.string.edit_place_products_title)
    Destination.places -> stringResource(R.string.edit_places_title)
  }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun onUiEvent(
  uiEvent: UiEvent?,
  editViewModel: EditViewModel,
  pagerState: PagerState,
  coroutineScope: CoroutineScope
) {
  when (uiEvent) {
    is UiEvent.NavigateTo -> navigate(uiEvent, pagerState, coroutineScope)
    null -> {}
  }
  editViewModel.eventConsumed()
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun navigate(
  uiEvent: UiEvent?,
  pagerState: PagerState,
  coroutineScope: CoroutineScope
) {
  uiEvent?.let { coroutineScope.launch { pagerState.animateScrollToPage(uiEvent.index) } }
}