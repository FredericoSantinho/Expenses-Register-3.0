package neuro.expenses.register.presentation.ui.manual.register.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.FragmentActivity
import neuro.expenses.register.presentation.R
import neuro.expenses.register.presentation.common.compose.rememberUnit
import neuro.expenses.register.presentation.common.picker.date.ShowDatePicker
import neuro.expenses.register.presentation.common.picker.date.ShowMaterialDatePicker
import neuro.expenses.register.presentation.common.picker.time.DefaultShowTimePicker
import neuro.expenses.register.presentation.common.picker.time.ShowTimePicker
import neuro.expenses.register.presentation.ui.bill.BillComposableContainer
import neuro.expenses.register.presentation.ui.common.composables.datetime.DateTimeComposable
import neuro.expenses.register.presentation.ui.common.composables.datetime.mapper.DateTextMapper
import neuro.expenses.register.presentation.ui.common.composables.datetime.mapper.DateTextMapperImpl
import neuro.expenses.register.presentation.ui.common.composables.datetime.mapper.TimeTextMapper
import neuro.expenses.register.presentation.ui.common.composables.datetime.mapper.TimeTextMapperImpl
import neuro.expenses.register.presentation.ui.common.composables.snackbar.showSnackbar
import neuro.expenses.register.presentation.ui.common.composables.text.CurrencyTextField
import neuro.expenses.register.presentation.ui.common.composables.text.TextFieldWithDropdown
import neuro.expenses.register.presentation.ui.common.composables.text.TextFieldWithError
import neuro.expenses.register.presentation.ui.common.keyboard.keyboardOptionsNumeric
import neuro.expenses.register.presentation.ui.common.keyboard.keyboardOptionsText
import neuro.expenses.register.presentation.ui.manual.register.mapper.toPresentation
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTheme
import neuro.expenses.register.presentation.ui.theme.ExpensesRegisterTypography
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiEvent.UiEvent
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiState.UiState
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterUiState.UiStateError
import neuro.expenses.register.viewmodel.manual.register.ManualRegisterViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ManualRegisterComposable(
  manualRegisterViewModel: ManualRegisterViewModel = getViewModel(),
  showTimePicker: ShowTimePicker = DefaultShowTimePicker(),
  showDatePicker: ShowDatePicker = ShowMaterialDatePicker(),
  timeTextMapper: TimeTextMapper = TimeTextMapperImpl(),
  dateTextMapper: DateTextMapper = DateTextMapperImpl()
) {
  rememberUnit { manualRegisterViewModel.onComposition() }

  val uiEvent by manualRegisterViewModel.uiEvent
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
        semantics = ManualRegisterComposableTags.DESCRIPTION,
        value = manualRegisterViewModel.description,
        label = stringResource(R.string.description),
        keyboardOptions = keyboardOptionsText,
        textStyle = ExpensesRegisterTypography.body2,
        onValueChange = { manualRegisterViewModel.onDescriptionChange() },
        isError = descriptionIsError,
        errorMessage = descriptionErrorMessage
      )
      TextFieldWithDropdown(semantics = ManualRegisterComposableTags.CATEGORY,
        dataIn = manualRegisterViewModel.categoriesNames.subscribeAsState(
          initial = emptyList()
        ),
        label = stringResource(R.string.category),
        keyboardOptions = keyboardOptionsText,
        onValueChange = { manualRegisterViewModel.onCategoryChange() },
        value = manualRegisterViewModel.category,
        isError = categoryIsError,
        textStyle = ExpensesRegisterTypography.body2,
        onSelectOption = { focusManager.moveFocus(FocusDirection.Next) })
      ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (place, placeAuto) = createRefs()

        TextFieldWithError(value = manualRegisterViewModel.place,
          label = stringResource(R.string.place),
          modifier = Modifier
            .constrainAs(place) {
              start.linkTo(parent.start)
              end.linkTo(placeAuto.start, margin = 8.dp)
              width = Dimension.fillToConstraints
            }
            .onFocusEvent { placeHasFocus.value = it.isFocused },
          semantics = ManualRegisterComposableTags.PLACE,
          keyboardOptions = keyboardOptionsText,
          textStyle = ExpensesRegisterTypography.body2,
          onValueChange = {
            manualRegisterViewModel.onPlaceChange()
          },
          isError = placeIsError,
          errorMessage = placeErrorMessage
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

        CurrencyTextField(value = manualRegisterViewModel.price,
          label = stringResource(R.string.price),
          modifier = Modifier
            .semantics { testTag = ManualRegisterComposableTags.PRICE }
            .constrainAs(priceC) {
              start.linkTo(parent.start)
              width = Dimension.value(96.dp)
            },
          onValueChange = {
            manualRegisterViewModel.onPriceChange()
          },
          textStyle = ExpensesRegisterTypography.body2
        )
        TextFieldWithError(
          value = manualRegisterViewModel.amount,
          label = stringResource(R.string.amount),
          modifier = Modifier.constrainAs(amountC) {
            start.linkTo(priceC.end, margin = 8.dp)
            width = Dimension.value(96.dp)
          },
          semantics = ManualRegisterComposableTags.AMOUNT,
          keyboardOptions = keyboardOptionsNumeric,
          textStyle = ExpensesRegisterTypography.body2.copy(textAlign = TextAlign.End),
          onValueChange = {
            manualRegisterViewModel.amount.value = it
            manualRegisterViewModel.onAmountChange()
          },
          isError = amountIsError,
          errorMessage = amountErrorMessage
        )
        Text(
          text = stringResource(R.string.manual_register_total) + ':',
          modifier = Modifier.constrainAs(totalLabelC) {
            end.linkTo(totalC.start, margin = 8.dp)
            bottom.linkTo(amountC.bottom, margin = 4.dp)
          },
          style = MaterialTheme.typography.h6,
        )
        Text(
          text = manualRegisterViewModel.total.value, modifier = Modifier.constrainAs(totalC) {
            end.linkTo(parent.end, margin = 8.dp)
            bottom.linkTo(amountC.bottom, margin = 4.dp)
          }, style = MaterialTheme.typography.h6
        )
      }
      Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
      ) {
        Button(onClick = {
          manualRegisterViewModel.onRegisterButton()
        },
          modifier = Modifier
            .semantics { testTag = ManualRegisterComposableTags.BUTTON_REGISTER_EXPENSE }
            .padding(top = 8.dp, bottom = 16.dp)) {
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
  onUiEvent(uiEvent, manualRegisterViewModel)
}

@Composable
private fun onUiEvent(uiEvent: UiEvent?, manualRegisterViewModel: ManualRegisterViewModel) {
  when (uiEvent) {
    is UiEvent.ShowRegisterSuccess -> {
      showSuccessSnackbar(uiEvent)
    }
    null -> {}
  }
  manualRegisterViewModel.eventConsumed()
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
      uiState.errors,
      descriptionIsError,
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
        stringResource(error.message.toPresentation()), placeIsError, placeErrorMessage
      )
      is UiStateError.ShowCategoryError -> showCategoryError(categoryIsError)
      is UiStateError.ShowDescriptionError -> showDescriptionError(
        stringResource(error.message.toPresentation()), descriptionIsError, descriptionErrorMessage
      )
      is UiStateError.ShowAmountError -> showAmountError(
        stringResource(error.message.toPresentation()), amountIsError, amountErrorMessage
      )
    }
  }
}

private fun showAmountError(
  message: String, amountIsError: MutableState<Boolean>, amountErrorMessage: MutableState<String>
) {
  amountErrorMessage.value = message
  amountIsError.value = true
}

private fun showPlaceError(
  message: String, placeIsError: MutableState<Boolean>, placeErrorMessage: MutableState<String>
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
  val fragmentActivity = FragmentActivity()

  ExpensesRegisterTheme {
    ManualRegisterComposable()
  }
}

class ManualRegisterComposableTags {
  companion object {
    const val DESCRIPTION = "description"
    const val CATEGORY = "category"
    const val PLACE = "place"
    const val PRICE = "price"
    const val AMOUNT = "amount"
    const val BUTTON_REGISTER_EXPENSE = "registerButton"
  }
}