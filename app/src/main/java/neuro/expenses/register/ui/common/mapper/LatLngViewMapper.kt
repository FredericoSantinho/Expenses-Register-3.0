package neuro.expenses.register.ui.common.mapper

import com.google.android.gms.maps.model.LatLng
import neuro.expenses.register.viewmodel.home.model.LatLngModel

interface LatLngViewMapper {
  fun map(latLngModel: LatLngModel): LatLng
}