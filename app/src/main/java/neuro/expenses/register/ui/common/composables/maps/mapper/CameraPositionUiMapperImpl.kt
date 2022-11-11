package neuro.expenses.register.ui.common.composables.maps.mapper

import com.google.android.gms.maps.model.CameraPosition
import neuro.expenses.register.ui.common.mapper.LatLngMapper
import neuro.expenses.register.viewmodel.home.model.CameraPositionModel

class CameraPositionUiMapperImpl(private val latLngMapper: LatLngMapper) : CameraPositionUiMapper {
  override fun map(cameraPositionModel: CameraPositionModel): CameraPosition {
    return CameraPosition.fromLatLngZoom(
      latLngMapper.map(cameraPositionModel.latLngModel),
      cameraPositionModel.zoom
    )
  }
}