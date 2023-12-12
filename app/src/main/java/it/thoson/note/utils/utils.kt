import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Date

class Utils {

    companion object {

        private val colorPalette = arrayOf(
            Color(0xFFDF826C),
            Color(0xFFF8FFD2),
            Color(0xFFD0F288),
            Color(0xFF8ADAB2),
            Color(0xFFB6BBC4),
            Color(0xFFF8DFD4),
            Color(0xFFC5FFF8),
            Color(0xFFB1C381),
            Color(0xFFE3651D),
            Color(0xFFBED754),
            Color(0xFFF7B787),
            Color(0xFF3081D0),
            Color(0xFF67729D),
            Color(0xFFDC8686),
            Color(0xFFA25772),
        )

        fun intToColor(value: Int): Color {
            val index = value % colorPalette.size
            return colorPalette[index]
        }

        fun dateToString(date: Date): String {
            val dateFormat = SimpleDateFormat.getDateInstance()
            return dateFormat.format(date)
        }

        fun stringToDate(dateString: String): Date {
            val dateFormat = SimpleDateFormat.getDateInstance()
            return dateFormat.parse(dateString)
                ?: throw IllegalArgumentException("Invalid date format")
        }
    }
}
