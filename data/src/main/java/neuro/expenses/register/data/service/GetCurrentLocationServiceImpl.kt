package neuro.expenses.register.data.service

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import neuro.expenses.register.domain.dto.LatLngDto
import neuro.expenses.register.domain.service.GetCurrentLocationService

class GetCurrentLocationServiceImpl(
  private val context: Context,
  private val scheduler: Scheduler
) : GetCurrentLocationService {
  override fun getCurrentLocation(): Single<LatLngDto> {
    return Single.create { subscriber ->
      val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

      if (ActivityCompat.checkSelfPermission(
          context,
          Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
          context,
          Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
      ) {
        throw IllegalStateException("Location permission not granted!")
      }
      fusedLocationClient.getCurrentLocation(
        Priority.PRIORITY_BALANCED_POWER_ACCURACY,
        object : CancellationToken() {
          override fun onCanceledRequested(p0: OnTokenCanceledListener) =
            CancellationTokenSource().token

          override fun isCancellationRequested() = false
        }).addOnSuccessListener {
        if (it != null) {
          subscriber.onSuccess(it)
        } else {
          subscriber.onError(Exception("No location"))
        }
      }
    }.map { LatLngDto(it.latitude, it.longitude) }.observeOn(scheduler)
  }
}
