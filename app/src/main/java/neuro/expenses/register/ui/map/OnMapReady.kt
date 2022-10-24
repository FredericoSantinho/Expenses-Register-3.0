package neuro.expenses.register.ui.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class OnMapReady : OnMapReadyCallback {
  private lateinit var mMap: GoogleMap

  override fun onMapReady(googleMap: GoogleMap) {
    mMap = googleMap

    // Add a marker in Sydney and move the camera
    val bitoque = LatLng(37.091495, -8.2475677)
    mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
    mMap.isMyLocationEnabled = true

    mMap.addMarker(MarkerOptions().position(bitoque).title("Marker in Sydney").draggable(true))
    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bitoque, 19f))
  }

}