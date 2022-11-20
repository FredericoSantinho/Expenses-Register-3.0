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
import neuro.expenses.register.ui.common.composables.search.SearchSuggestion

class MockedSuggestions {
  fun create(): MutableList<SearchSuggestion> {
    val list = mutableListOf<SearchSuggestion>()
    for (i in 1..3) {
      list.add(MockedSuggestion1(i))
    }
    for (i in 1..2) {
      list.add(MockedSuggestion2(i))
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