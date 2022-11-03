package neuro.expenses.register.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
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
  fun calendarToTimestamp(calendar: Calendar): Long? {
    return calendar.timeInMillis
  }
}
