package neuro.expenses.register.ui.common.composables.maps.mapper

import com.google.android.gms.maps.model.CameraPosition
import neuro.expenses.register.viewmodel.home.model.CameraPositionModel

interface CameraPositionUiMapper {
  fun map(cameraPositionModel: CameraPositionModel): CameraPosition
}