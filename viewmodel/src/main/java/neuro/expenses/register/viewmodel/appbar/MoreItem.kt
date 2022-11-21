package neuro.expenses.register.viewmodel.appbar

class MoreItem(private val moreItemText: MoreItemText, private val onClick: () -> (Unit)) {
  fun text(): MoreItemText {
    return moreItemText
  }

  fun onClick() {
    onClick.invoke()
  }
}

abstract class MoreItemText