package cz.jaro.datum_cas

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import java.time.LocalTime
import java.util.Calendar

@kotlinx.serialization.Serializable(CasSerializer::class)
data class Cas(val h: Int = 0, val min: Int = 0, val s: Int = 0) : Comparable<Cas> {

    companion object {

        val ted
            get() = Calendar.getInstance().let { Cas(it[Calendar.HOUR_OF_DAY], it[Calendar.MINUTE], it[Calendar.SECOND]) }

        val tedFlow = flow {
            while (currentCoroutineContext().isActive) {
                delay(500)
                emit(Calendar.getInstance().let { Cas(it[Calendar.HOUR_OF_DAY], it[Calendar.MINUTE], it[Calendar.SECOND]) })
            }
        }
            .flowOn(Dispatchers.IO)
            .stateIn(MainScope(), SharingStarted.WhileSubscribed(5_000), ted)

        val nikdy = Cas(99, 99)

    }

    override operator fun compareTo(other: Cas) = toInt().compareTo(other.toInt())
    operator fun minus(other: Cas) = toInt().minus(other.toInt()).sek
    operator fun minus(other: Trvani) = toTrvani().minus(other).toCas()
    operator fun plus(other: Trvani) = toTrvani().plus(other).toCas()
    operator fun div(other: Trvani) = toTrvani().div(other)
    operator fun rem(other: Trvani) = toTrvani().rem(other).toCas()

    fun toTrvani() = toInt().sek
    @RequiresApi(Build.VERSION_CODES.O)
    fun toLocalTime() = LocalTime.of(h, min, s)
    private fun toInt() = h * 60 * 60 + min * 60 + s
    override fun toString() = toString(printSeconds = false)
    fun toString(printSeconds: Boolean = false) = buildString {
        append(h)
        append(":")
        if ("$min".length <= 1) append("0")
        append(min)
        if (printSeconds) {
            append(":")
            if ("$s".length <= 1) append("0")
            append(s)
        }
    }
}
