package neuro.expenses.register.ui.common.composables.appbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import neuro.expenses.register.ui.common.composables.appbar.suggestions.place.PlaceSearchSuggestion
import neuro.expenses.register.ui.common.composables.appbar.suggestions.product.ProductSearchSuggestion
import neuro.expenses.register.ui.common.composables.search.SearchSuggestion

class MockedSuggestions {
  fun create(): MutableList<SearchSuggestion> {
    val list = mutableListOf<SearchSuggestion>()
    for (i in 1..3) {
      list.add(
        ProductSearchSuggestion(
          "Cerveja Sagres 0,33cl",
          "1.30 €",
          "https://thexicos-wp.ams3.digitaloceanspaces.com/uploads/sites/5/2022/07/sagr.png"
        )
      )
    }
    for (i in 1..2) {
      list.add(PlaceSearchSuggestion(i.toLong(), "place $i", { }))
    }
    return list
  }
}

class MockedSuggestion1(private val i: Int) : SearchSuggestion {
  override fun filter(value: String): Boolean {
    return true
  }

  override fun text(): String {
    return "test $i"
  }

  @Composable
  override fun composable() {
    Column {
      Text(text = text())
    }
  }

  @Composable
  override fun titleComposable() {
    Column {
      Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Text("Products", fontWeight = FontWeight.Bold)
      }
      Divider(thickness = 2.dp, color = Color.LightGray)
    }
  }
}

class MockedSuggestion2(private val i: Int) : SearchSuggestion {
  override fun filter(value: String): Boolean {
    return true
  }

  override fun text(): String {
    return "test $i"
  }

  @Composable
  override fun composable() {
    Column {
      Text(text = text())
    }
  }

  @Composable
  override fun titleComposable() {
    Column {
      Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Text("Places", fontWeight = FontWeight.Bold)
      }
      Divider(thickness = 2.dp, color = Color.LightGray)
    }
  }

}