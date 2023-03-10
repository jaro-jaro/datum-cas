package cz.jaro.datum_cas


import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.Calendar

@kotlinx.serialization.Serializable(DatumSerializer::class)
data class Datum(val den: Int, val mesic: Int, val rok: Int) : Comparable<Datum> {

    companion object {
        val dnes get() = Calendar.getInstance().toDatum()
    }

    override fun compareTo(other: Datum) = toInt().compareTo(other.toInt())
    operator fun minus(other: Datum) = toInt().minus(other.toInt())
    operator fun minus(other: Int) = toInt().minus(other).toDatum()
    operator fun plus(other: Int) = toInt().plus(other).toDatum()

    @RequiresApi(Build.VERSION_CODES.O)
    fun toLocalDate() = LocalDate.of(rok, mesic, den)!!
    fun toInt() = rok * 372 + mesic * 31 + den
    fun toCalendar(): Calendar = Calendar.getInstance().apply { set(rok, mesic - 1, den) }
    override fun toString() = "$den. $mesic. $rok"
    operator fun rangeTo(second: Datum) = DatumRange(this, second)
}
