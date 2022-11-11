package neuro.expenses.register.ui.settings

import neuro.expenses.register.common.android.activity.BaseActivity
import neuro.expenses.register.databinding.ActivitySettingsBinding

class SettingsActivity : BaseActivity<ActivitySettingsBinding>() {

  override fun getViewBinding(): ActivitySettingsBinding {
    return ActivitySettingsBinding.inflate(layoutInflater)
  }
}