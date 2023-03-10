package cz.jaro.datum_cas

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object CasSerializer : KSerializer<Cas> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Cas", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Cas) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): Cas {
        return decoder.decodeString().toCas()
    }
}