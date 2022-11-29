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
import neuro.expenses.register.domain.service.location.GetCurrentLocationService
import neuro.expenses.register.domain.service.location.NoLocationException
import neuro.expenses.register.domain.service.location.NoLocationPermissionException

class GetCurrentLocationServiceImpl(
  private val context: Context, private val scheduler: Scheduler
) : GetCurrentLocationService {
  override fun getCurrentLocation(): Single<LatLngDto> {
    return Single.create { subscriber ->
      val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

      if (ActivityCompat.checkSelfPermission(
          context, Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
          context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
      ) {
        subscriber.onError(NoLocationPermissionException())
      }
      fusedLocationClient.getCurrentLocation(
        Priority.PRIORITY_HIGH_ACCURACY,
        object : CancellationToken() {
          override fun onCanceledRequested(p0: OnTokenCanceledListener) =
            CancellationTokenSource().token

          override fun isCancellationRequested() = false
        }).addOnSuccessListener { location ->
        location?.let { subscriber.onSuccess(it) } ?: subscriber.onError(NoLocationException())
      }
    }.map { LatLngDto(it.latitude, it.longitude) }.observeOn(scheduler)
  }
}
