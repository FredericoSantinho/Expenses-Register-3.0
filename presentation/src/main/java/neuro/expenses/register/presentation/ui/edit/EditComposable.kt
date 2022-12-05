package neuro.expenses.register.presentation.ui.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import neuro.expenses.register.presentation.common.compose.rememberSaveableUnit
import neuro.expenses.register.presentation.ui.edit.mapper.toPresentation
import neuro.expenses.register.viewmodel.edit.EditUiEvent.UiEvent
import neuro.expenses.register.viewmodel.edit.EditViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EditComposable(editViewModel: EditViewModel = getViewModel()) {
  rememberSaveableUnit { editViewModel.onComposition() }

  val uiEvent by editViewModel.uiEvent

  val pagerState = rememberPagerState()
  val coroutineScope = rememberCoroutineScope()

  val pages = editViewModel.pages.map { it.toPresentation() }
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
              directions.title,
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
      pages[page].composable()
    }
    onUiEvent(uiEvent, editViewModel, pagerState, coroutineScope)
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