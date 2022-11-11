package neuro.expenses.register.ui.common.composables.maps.mapper

import com.google.android.gms.maps.model.CameraPosition
import neuro.expenses.register.ui.common.mapper.LatLngViewMapper
import neuro.expenses.register.viewmodel.home.model.CameraPositionModel

class CameraPositionViewMapperImpl(private val latLngViewMapper: LatLngViewMapper) :
  CameraPositionViewMapper {
  override fun map(cameraPositionModel: CameraPositionModel): CameraPosition {
    return CameraPosition.fromLatLngZoom(
      latLngViewMapper.map(cameraPositionModel.latLngModel),
      cameraPositionModel.zoom
    )
  }
}