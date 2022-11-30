package neuro.expenses.register.presentation.ui.common.mapper

import com.google.android.gms.maps.model.LatLng
import neuro.expenses.register.viewmodel.home.model.LatLngModel

fun LatLngModel.toPresentation(): LatLng = LatLng(latitude, longitude)

fun LatLng.toViewModel(): LatLngModel {
  return LatLngModel(latitude, longitude)
}
