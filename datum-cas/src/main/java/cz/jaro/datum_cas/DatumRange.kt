package cz.jaro.datum_cas

import cz.jaro.datum_cas.Datum

class DatumRange(override val start: Datum, override val endInclusive: Datum) : ClosedRange<Datum> {
    override fun toString() = "$start..$endInclusive"
}