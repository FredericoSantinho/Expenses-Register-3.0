package neuro.expenses.register.presentation.ui.settings

import neuro.expenses.register.presentation.common.activity.BaseActivity
import neuro.expenses.register.presentation.databinding.ActivitySettingsBinding

class SettingsActivity : BaseActivity<ActivitySettingsBinding>() {

  override fun getViewBinding(): ActivitySettingsBinding {
    return ActivitySettingsBinding.inflate(layoutInflater)
  }
}