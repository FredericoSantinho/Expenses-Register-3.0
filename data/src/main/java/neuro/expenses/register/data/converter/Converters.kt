package neuro.expenses.register.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import neuro.expenses.register.data.model.place.LatLng
import java.util.*

@ProvidedTypeConverter
class Converters {
  @TypeConverter
  fun timestampToCalendar(timestamp: Long): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = Date(timestamp)
    return calendar
  }

  @TypeConverter
  fun calendarToTimestamp(calendar: Calendar): Long {
    return calendar.timeInMillis
  }

  @TypeConverter
  fun stringToLatLng(s: String): LatLng {
    val split = s.split(';')
    val latitude = split[0].toDouble()
    val longitude = split[1].toDouble()
    return LatLng(latitude, longitude)
  }

  @TypeConverter
  fun latLngToString(latLng: LatLng): String {
    return latLng.latitude.toString() + ';' + latLng.longitude.toString()
  }
}
