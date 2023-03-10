package cz.jaro.datum_cas

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalTime
import java.util.Calendar

fun Int.toDatum() = Datum(this % 31, (this % 372) / 31, this / 372)

fun String.toDatum(): Datum = split(". ").map { it.toInt() }.toDatum()

fun List<Int>.toDatum(): Datum {
    assert(size <= 3)
    assert(size >= 2)
    if (size == 2) return Datum(get(0), get(1), Datum.dnes.rok)
    return Datum(get(0), get(1), get(2))
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toDatum() = Datum(dayOfMonth, monthValue, year)

fun Calendar.toDatum(): Datum = Datum(this[Calendar.DAY_OF_MONTH], this[Calendar.MONTH] + 1, this[Calendar.YEAR])

infix fun Int.cas(o: Int) = Cas(this, o)

fun List<Int>.toCas(): Cas {
    assert(size >= 2)
    assert(size <= 3)
    if (size == 2) return get(0) cas get(1)
    return Cas(get(0), get(1), get(2))
}

private fun Int.toCas() = if (this == 362340) Cas.nikdy else Cas(
    h = div(60 * 60),
    min = rem(60 * 60).div(60),
    s = rem(60),
)

fun String?.toCas() = this
    ?.split(":")
    ?.map { it.toInt() }
    ?.toCas()
    ?: Cas.ted

fun Trvani.toCas() = sek.toCas()

@RequiresApi(Build.VERSION_CODES.O)
fun LocalTime.toCas() = Cas(hour, minute, second)

val Double.sek get() = toInt().sek
val Int.sek get() = Trvani(this)

val Double.min get() = times(60).sek
val Int.min get() = times(60).sek

val Double.hod get() = times(60).min
val Int.hod get() = times(60).min


operator fun Int.times(o: Trvani) = times(o.sek).sek
operator fun Double.times(o: Trvani) = times(o.sek).sek