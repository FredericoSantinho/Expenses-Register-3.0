package neuro.expenses.register.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import neuro.expenses.register.data.model.place.LatLngModel
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
  fun stringToLatLng(s: String): LatLngModel {
    val split = s.split(';')
    val latitude = split[0].toDouble()
    val longitude = split[1].toDouble()
    return LatLngModel(latitude, longitude)
  }

  @TypeConverter
  fun latLngToString(latLngModel: LatLngModel): String {
    return latLngModel.latitude.toString() + ';' + latLngModel.longitude.toString()
  }
}
