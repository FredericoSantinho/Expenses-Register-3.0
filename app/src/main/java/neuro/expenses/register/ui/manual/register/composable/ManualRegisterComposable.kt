package neuro.expenses.register.ui.manual.register.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.FragmentActivity
import com.exchangebot.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.R
import neuro.expenses.register.common.picker.date.ShowDatePicker
import neuro.expenses.register.common.picker.date.ShowMaterialDatePicker
import neuro.expenses.register.common.picker.time.DefaultShowTimePicker
import neuro.expenses.register.common.picker.time.ShowTimePicker
import neuro.expenses.register.ui.common.bill.BillComposableContainer
import neuro.expenses.register.ui.composables.datetime.DateTimeComposable
import neuro.expenses.register.ui.composables.datetime.mapper.DateTextMapper
import neuro.expenses.register.ui.composables.datetime.mapper.DateTextMapperImpl
import neuro.expenses.register.ui.composables.datetime.mapper.TimeTextMapper
import neuro.expenses.register.ui.composables.datetime.mapper.TimeTextMapperImpl
import neuro.expenses.register.ui.composables.snackbar.showSnackbar
import neuro.expenses.register.ui.composables.text.CurrencyTextField
import neuro.expenses.register.ui.composables.text.TextFieldWithDropdown
import neuro.expenses.register.ui.composables.text.TextFieldWithError
import neuro.expenses.register.ui.manual.register.ManualRegisterViewModel
import neuro.expenses.register.ui.manual.register.UiEvent
import neuro.expenses.register.ui.manual.register.UiState
import neuro.expenses.register.ui.manual.register.UiStateError
import neuro.expenses.register.ui.manual.register.mapper.ManualRegisterMessageMapper
import neuro.expenses.register.ui.manual.register.mapper.ManualRegisterMessageMapperImpl
import org.koin.androidx.compose.getViewModel

@Composable
fun ManualRegisterComposable(
  manualRegisterViewModel: ManualRegisterViewModel = getViewModel(),
  fragmentActivity: FragmentActivity,
  showTimePicker: ShowTimePicker = DefaultShowTimePicker(),
  showDatePicker: ShowDatePicker = ShowMaterialDatePicker(),
  timeTextMapper: TimeTextMapper = TimeTextMapperImpl(),
  dateTextMapper: DateTextMapper = DateTextMapperImpl(),
  messageMapper: ManualRegisterMessageMapper = ManualRegisterMessageMapperImpl(),
  currency: String = "€"
) {
  val uiEvent by manualRegisterViewModel.uiEvent.observeAsState(null)
  val uiState by manualRegisterViewModel.uiState

  val descriptionIsError = remember { mutableStateOf(false) }
  val descriptionErrorMessage = remember { mutableStateOf("") }
  val categoryIsError = remember { mutableStateOf(false) }
  val placeIsError = remember { mutableStateOf(false) }
  val placeErrorMessage = remember { mutableStateOf("") }

  descriptionIsError.value = false
  descriptionErrorMessage.value = ""
  categoryIsError.value = false
  placeIsError.value = false
  placeErrorMessage.value = ""

  onUiState(
    uiState,
    descriptionIsError,
    descriptionErrorMessage,
    categoryIsError,
    placeIsError,
    placeErrorMessage,
    messageMapper
  )
  onUiEvent(uiEvent)

  Column(
    Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.Bottom
  ) {
    val amountVar = remember { mutableStateOf(0.0) }
    val priceVar = remember { mutableStateOf(0.0) }

    DateTimeComposable(
      fragmentActivity,
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp),
      showTimePicker,
      showDatePicker,
      timeTextMapper,
      dateTextMapper,
      calendar = manualRegisterViewModel.calendar
    )
    TextFieldWithError(
      label = stringResource(R.string.manual_register_description),
      keyboardOptions = keyboardOptionsText,
      modifier = Modifier.padding(start = 8.dp, end = 8.dp),
      value = manualRegisterViewModel.description,
      isError = descriptionIsError,
      errorMessage = descriptionErrorMessage,
      onValueChange = { manualRegisterViewModel.onDescriptionChange() }
    )
    TextFieldWithDropdown(
      modifier = Modifier.padding(start = 8.dp, end = 8.dp),
      dataIn = manualRegisterViewModel.categories.subscribeAsState(initial = emptyList()),
      label = stringResource(R.string.manual_register_category),
      keyboardOptions = keyboardOptionsText,
      value = manualRegisterViewModel.category,
      isError = categoryIsError,
      onValueChange = { manualRegisterViewModel.onCategoryChange() }
    )
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
      val (place, placeAuto) = createRefs()

      TextFieldWithError(
        label = stringResource(R.string.manual_register_place),
        modifier = Modifier.constrainAs(place) {
          start.linkTo(parent.start, margin = 8.dp)
          end.linkTo(placeAuto.start, margin = 8.dp)
          width = Dimension.fillToConstraints
        },
        keyboardOptions = keyboardOptionsText,
        value = manualRegisterViewModel.place,
        isError = placeIsError,
        errorMessage = placeErrorMessage,
        onValueChange = {
          manualRegisterViewModel.onPlaceChange()
        }
      )
      IconButton(onClick = {
        manualRegisterViewModel.onNearestPlaceButton()
      }, modifier = Modifier.constrainAs(placeAuto) {
        end.linkTo(parent.end, margin = 8.dp)
        top.linkTo(place.top, margin = 8.dp)
        bottom.linkTo(place.bottom)
      }) {
        Icon(
          painter = painterResource(id = R.drawable.ic_edit_place_24),
          contentDescription = null,
          tint = MaterialTheme.colors.primary
        )
      }
    }
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
      val (price, amount, totalLabel, total) = createRefs()

      val totalVar =
        remember { mutableStateOf(getTotalStr(amountVar.value, priceVar.value, currency)) }

      CurrencyTextField(
        label = stringResource(R.string.manual_register_price),
        modifier = Modifier.constrainAs(price) {
          start.linkTo(parent.start, margin = 8.dp)
          width = Dimension.value(96.dp)
        },
        keyboardOptions = keyboardOptionsNumeric,
        onValueChange = {
          priceVar.value = if (it.isNotEmpty()) it.toDouble() else 0.0
          totalVar.value = getTotalStr(amountVar.value, priceVar.value, currency)
        },
        value = manualRegisterViewModel.price
      )
      TextFieldWithError(
        label = stringResource(R.string.manual_register_amount),
        modifier = Modifier.constrainAs(amount) {
          start.linkTo(price.end, margin = 8.dp)
          width = Dimension.value(96.dp)
        },
        keyboardOptions = keyboardOptionsNumeric,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
        onValueChange = {
          amountVar.value = if (it.isNotEmpty()) it.toDouble() else 0.0
          totalVar.value = getTotalStr(amountVar.value, priceVar.value, currency)
        },
        value = manualRegisterViewModel.amount
      )
      Text(
        text = stringResource(R.string.manual_register_total) + ':',
        modifier = Modifier.constrainAs(totalLabel) {
          end.linkTo(total.start, margin = 8.dp)
          top.linkTo(amount.top, margin = 8.dp)
          bottom.linkTo(amount.bottom)
        },
        fontSize = 16.sp
      )
      Text(text = totalVar.value, modifier = Modifier.constrainAs(total) {
        end.linkTo(parent.end, margin = 16.dp)
        top.linkTo(amount.top, margin = 8.dp)
        bottom.linkTo(amount.bottom)
      }, fontSize = 16.sp)
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
      Button(onClick = {
        manualRegisterViewModel.onRegisterButton()
      }, modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)) {
        Text(text = stringResource(R.string.manual_register_register))
      }
    }
    BillComposableContainer(manualRegisterViewModel.billViewModel)
  }
}

@Composable
fun onUiEvent(uiEvent: UiEvent?) {
  when (uiEvent) {
    is UiEvent.ShowRegisterSuccess -> {
      showSuccessSnackbar(uiEvent)
    }
    null -> {}
  }
}

@Composable
fun showSuccessSnackbar(uiEvent: UiEvent.ShowRegisterSuccess) {
  showSnackbar(text = uiEvent.productDescription, key = uiEvent)
}

@Composable
private fun onUiState(
  uiState: UiState,
  descriptionIsError: MutableState<Boolean>,
  descriptionErrorMessage: MutableState<String>,
  categoryIsError: MutableState<Boolean>,
  placeIsError: MutableState<Boolean>,
  placeErrorMessage: MutableState<String>,
  messageMapper: ManualRegisterMessageMapper
) {
  when (uiState) {
    UiState.Ready -> {}
    is UiState.Error -> onUiError(
      uiState.errors, descriptionIsError,
      descriptionErrorMessage,
      categoryIsError,
      placeIsError,
      placeErrorMessage,
      messageMapper
    )
  }
}

@Composable
fun onUiError(
  errors: List<UiStateError>,
  descriptionIsError: MutableState<Boolean>,
  descriptionErrorMessage: MutableState<String>,
  categoryIsError: MutableState<Boolean>,
  placeIsError: MutableState<Boolean>,
  placeErrorMessage: MutableState<String>,
  messageMapper: ManualRegisterMessageMapper
) {
  errors.forEach { error ->
    when (error) {
      is UiStateError.ShowPlaceError -> showPlaceError(
        stringResource(messageMapper.map(error.message)),
        placeIsError,
        placeErrorMessage
      )
      is UiStateError.ShowCategoryError -> showCategoryError(categoryIsError)
      is UiStateError.ShowDescriptionError -> showDescriptionError(
        stringResource(
          messageMapper.map(
            error.message
          )
        ), descriptionIsError, descriptionErrorMessage
      )
    }
  }
}

private fun showPlaceError(
  message: String,
  placeIsError: MutableState<Boolean>,
  placeErrorMessage: MutableState<String>
) {
  placeErrorMessage.value = message
  placeIsError.value = true
}

private fun showCategoryError(categoryIsError: MutableState<Boolean>) {
  categoryIsError.value = true
}

private fun showDescriptionError(
  message: String,
  descriptionIsError: MutableState<Boolean>,
  descriptionErrorMessage: MutableState<String>
) {
  descriptionErrorMessage.value = message
  descriptionIsError.value = true
}

private fun getTotalStr(amount: Double, price: Double, currency: String): String {
  return (amount * price).toString() + ' ' + currency
}

private val keyboardOptionsNumeric = KeyboardOptions.Default.copy(
  keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
)

private val keyboardOptionsText = KeyboardOptions.Default.copy(
  keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
)

@Preview
@Composable
fun PreviewManualRegisterComposable() {
  ExpensesRegisterTheme {
    ManualRegisterComposable(fragmentActivity = FragmentActivity())
  }
}
