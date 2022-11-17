package neuro.expenses.register.ui.manual.register.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.FragmentActivity
import neuro.expenses.register.R
import neuro.expenses.register.common.picker.date.ShowDatePicker
import neuro.expenses.register.common.picker.date.ShowMaterialDatePicker
import neuro.expenses.register.common.picker.time.DefaultShowTimePicker
import neuro.expenses.register.common.picker.time.ShowTimePicker
import neuro.expenses.register.ui.bill.BillComposableContainer
import neuro.expenses.register.ui.common.composables.datetime.DateTimeComposable
import neuro.expenses.register.ui.common.composables.datetime.mapper.DateTextMapper
import neuro.expenses.register.ui.common.composables.datetime.mapper.DateTextMapperImpl
import neuro.expenses.register.ui.common.composables.datetime.mapper.TimeTextMapper
import neuro.expenses.register.ui.common.composables.datetime.mapper.TimeTextMapperImpl
import neuro.expenses.register.ui.common.composables.snackbar.showSnackbar
import neuro.expenses.register.ui.common.composables.text.CurrencyTextField
import neuro.expenses.register.ui.common.composables.text.TextFieldWithDropdown
import neuro.expenses.register.ui.common.composables.text.TextFieldWithError
import neuro.expenses.register.ui.common.keyboard.keyboardOptionsNumeric
import neuro.expenses.register.ui.common.keyboard.keyboardOptionsText
import neuro.expenses.register.ui.manual.register.mapper.toPresentation
import neuro.expenses.register.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.ui.theme.ExpensesRegisterTypography
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterViewModel
import neuro.expenses.register.viewmodel.manual.register.UiEvent
import neuro.expenses.register.viewmodel.manual.register.UiState
import neuro.expenses.register.viewmodel.manual.register.UiStateError
import org.koin.androidx.compose.getViewModel

@Composable
fun ManualRegisterComposable(
  fragmentActivity: FragmentActivity,
  manualRegisterViewModel: ManualRegisterViewModel = getViewModel(),
  showTimePicker: ShowTimePicker = DefaultShowTimePicker(),
  showDatePicker: ShowDatePicker = ShowMaterialDatePicker(),
  timeTextMapper: TimeTextMapper = TimeTextMapperImpl(),
  dateTextMapper: DateTextMapper = DateTextMapperImpl()
) {
  val uiEvent by manualRegisterViewModel.uiEvent.observeAsState(null)
  val uiState by manualRegisterViewModel.uiState

  val descriptionIsError = remember { mutableStateOf(false) }
  val descriptionErrorMessage = remember { mutableStateOf("") }
  val categoryIsError = remember { mutableStateOf(false) }
  val placeIsError = remember { mutableStateOf(false) }
  val placeErrorMessage = remember { mutableStateOf("") }
  val amountIsError = remember { mutableStateOf(false) }
  val amountErrorMessage = remember { mutableStateOf("") }

  // Reset all error states on recomposition
  descriptionIsError.value = false
  descriptionErrorMessage.value = ""
  categoryIsError.value = false
  placeIsError.value = false
  placeErrorMessage.value = ""
  amountIsError.value = false
  amountErrorMessage.value = ""

  val focusManager = LocalFocusManager.current
  val placeHasFocus = remember { mutableStateOf(false) }

  Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
    Column(
      Modifier
        .padding(start = 8.dp, end = 8.dp)
        .verticalScroll(rememberScrollState())
    ) {
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
        label = stringResource(R.string.description),
        keyboardOptions = keyboardOptionsText,
        value = manualRegisterViewModel.description,
        isError = descriptionIsError,
        errorMessage = descriptionErrorMessage,
        onValueChange = { manualRegisterViewModel.onDescriptionChange() },
        textStyle = ExpensesRegisterTypography.body2
      )
      TextFieldWithDropdown(
        dataIn = manualRegisterViewModel.categoriesNames.subscribeAsState(initial = emptyList()),
        label = stringResource(R.string.category),
        keyboardOptions = keyboardOptionsText,
        onValueChange = { manualRegisterViewModel.onCategoryChange() },
        value = manualRegisterViewModel.category,
        isError = categoryIsError,
        textStyle = ExpensesRegisterTypography.body2,
        onSelectOption = { focusManager.moveFocus(FocusDirection.Next) }
      )
      ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (place, placeAuto) = createRefs()

        TextFieldWithError(
          label = stringResource(R.string.place),
          modifier = Modifier
            .constrainAs(place) {
              start.linkTo(parent.start)
              end.linkTo(placeAuto.start, margin = 8.dp)
              width = Dimension.fillToConstraints
            }
            .onFocusEvent { placeHasFocus.value = it.isFocused },
          keyboardOptions = keyboardOptionsText,
          value = manualRegisterViewModel.place,
          isError = placeIsError,
          errorMessage = placeErrorMessage,
          onValueChange = {
            manualRegisterViewModel.onPlaceChange()
          },
          textStyle = ExpensesRegisterTypography.body2
        )
        IconButton(onClick = {
          manualRegisterViewModel.onNearestPlaceButton()
          if (placeHasFocus.value) {
            focusManager.moveFocus(FocusDirection.Next)
          }
        }, modifier = Modifier.constrainAs(placeAuto) {
          end.linkTo(parent.end)
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
        val (priceC, amountC, totalLabelC, totalC) = createRefs()

        CurrencyTextField(
          label = stringResource(R.string.price),
          modifier = Modifier.constrainAs(priceC) {
            start.linkTo(parent.start)
            width = Dimension.value(96.dp)
          },
          onValueChange = {
            manualRegisterViewModel.onPriceChange()
          },
          value = manualRegisterViewModel.price,
          textStyle = ExpensesRegisterTypography.body2
        )
        TextFieldWithError(
          label = stringResource(R.string.amount),
          modifier = Modifier.constrainAs(amountC) {
            start.linkTo(priceC.end, margin = 8.dp)
            width = Dimension.value(96.dp)
          },
          keyboardOptions = keyboardOptionsNumeric,
          textStyle = ExpensesRegisterTypography.body2.copy(textAlign = TextAlign.End),
          onValueChange = {
            manualRegisterViewModel.amount.value = it
            manualRegisterViewModel.onAmountChange()
          },
          value = manualRegisterViewModel.amount,
          isError = amountIsError,
          errorMessage = amountErrorMessage
        )
        Text(
          text = stringResource(R.string.manual_register_total) + ':',
          modifier = Modifier.constrainAs(totalLabelC) {
            end.linkTo(totalC.start, margin = 8.dp)
            top.linkTo(amountC.top, margin = 8.dp)
            bottom.linkTo(amountC.bottom)
          },
          fontSize = 16.sp
        )
        Text(
          text = manualRegisterViewModel.total.value,
          modifier = Modifier.constrainAs(totalC) {
            end.linkTo(parent.end, margin = 8.dp)
            top.linkTo(amountC.top, margin = 8.dp)
            bottom.linkTo(amountC.bottom)
          },
          fontSize = 16.sp
        )
      }
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(onClick = {
          manualRegisterViewModel.onRegisterButton()
        }, modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)) {
          Text(text = stringResource(R.string.manual_register_register))
        }
      }
    }
    BillComposableContainer(manualRegisterViewModel.billViewModel)
  }

  onUiState(
    uiState,
    descriptionIsError,
    descriptionErrorMessage,
    categoryIsError,
    placeIsError,
    placeErrorMessage,
    amountIsError,
    amountErrorMessage
  )
  onUiEvent(uiEvent)
}

@Composable
private fun onUiEvent(uiEvent: UiEvent?) {
  when (uiEvent) {
    is UiEvent.ShowRegisterSuccess -> {
      showSuccessSnackbar(uiEvent)
    }
    null -> {}
  }
}

@Composable
private fun showSuccessSnackbar(uiEvent: UiEvent.ShowRegisterSuccess) {
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
  amountIsError: MutableState<Boolean>,
  amountErrorMessage: MutableState<String>
) {
  when (uiState) {
    UiState.Ready -> {}
    is UiState.Error -> onUiError(
      uiState.errors, descriptionIsError,
      descriptionErrorMessage,
      categoryIsError,
      placeIsError,
      placeErrorMessage,
      amountIsError,
      amountErrorMessage
    )
  }
}

@Composable
private fun onUiError(
  errors: List<UiStateError>,
  descriptionIsError: MutableState<Boolean>,
  descriptionErrorMessage: MutableState<String>,
  categoryIsError: MutableState<Boolean>,
  placeIsError: MutableState<Boolean>,
  placeErrorMessage: MutableState<String>,
  amountIsError: MutableState<Boolean>,
  amountErrorMessage: MutableState<String>
) {
  errors.forEach { error ->
    when (error) {
      is UiStateError.ShowPlaceError -> showPlaceError(
        stringResource(error.message.toPresentation()),
        placeIsError,
        placeErrorMessage
      )
      is UiStateError.ShowCategoryError -> showCategoryError(categoryIsError)
      is UiStateError.ShowDescriptionError -> showDescriptionError(
        stringResource(error.message.toPresentation()),
        descriptionIsError,
        descriptionErrorMessage
      )
      is UiStateError.ShowAmountError -> showAmountError(
        stringResource(error.message.toPresentation()), amountIsError, amountErrorMessage
      )
    }
  }
}

private fun showAmountError(
  message: String,
  amountIsError: MutableState<Boolean>,
  amountErrorMessage: MutableState<String>
) {
  amountErrorMessage.value = message
  amountIsError.value = true
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

@Preview
@Composable
fun PreviewManualRegisterComposable() {
  ExpensesRegisterTheme {
    ManualRegisterComposable(fragmentActivity = FragmentActivity())
  }
}
