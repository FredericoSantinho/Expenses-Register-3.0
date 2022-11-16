package neuro.expenses.register.ui.common.composables.maps.mapper

import com.google.android.gms.maps.model.CameraPosition
import neuro.expenses.register.ui.common.mapper.toPresentation
import neuro.expenses.register.viewmodel.home.model.CameraPositionModel

fun CameraPositionModel.toPresentation(): CameraPosition =
  CameraPosition.fromLatLngZoom(latLngModel.toPresentation(), zoom)
