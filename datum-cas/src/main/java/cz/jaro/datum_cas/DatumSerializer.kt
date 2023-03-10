package cz.jaro.datum_cas

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DatumSerializer : KSerializer<Datum> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Datum", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Datum) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): Datum {
        return decoder.decodeString().toDatum()
    }
}