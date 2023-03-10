package cz.jaro.datum_cas

@JvmInline
value class Trvani(val sek: Int) : Comparable<Trvani> {

    override fun compareTo(other: Trvani) = sek.compareTo(other.sek)

    companion object {
        val nekonecne = Trvani(Int.MAX_VALUE)
        val zadne = Trvani(0)
    }

    operator fun times(o: Int) = sek.times(o).sek
    operator fun times(o: Double) = sek.times(o).sek
    operator fun div(o: Trvani) = sek.toDouble().div(o.sek)
    operator fun rem(o: Trvani) = sek.rem(o.sek).sek
    operator fun plus(o: Trvani) = sek.plus(o.sek).sek
    operator fun minus(o: Trvani) = sek.minus(o.sek).sek

    val min get() = sek / 60.0
    val hod get() = min / 60.0

    fun useknoutSekundy() = min.toInt().min

    fun hezkyString(): String {
        val hodin = hod.toInt()
        val minut = minus(hodin.hod).min.toInt()

        return when {
            hodin == 0 && minut == 0 -> "<1 min"
            hodin == 0 -> "$minut min"
            minut == 0 -> "$hodin hod"
            else -> "$hodin hod $minut min"
        }
    }
}
