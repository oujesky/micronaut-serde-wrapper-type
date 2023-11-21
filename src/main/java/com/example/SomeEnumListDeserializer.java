package com.example;

import io.micronaut.context.annotation.Primary;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.type.Argument;
import io.micronaut.serde.Decoder;
import io.micronaut.serde.Deserializer;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Singleton
@Primary
public class SomeEnumListDeserializer implements Deserializer<List<SomeEnum>> {
    @Override
    public @Nullable List<SomeEnum> deserialize(@NonNull Decoder decoder, @NonNull DecoderContext context, @NonNull Argument<? super List<SomeEnum>> type) throws IOException {
        var stringValue = decoder.decodeString();
        return Arrays.stream(stringValue.split("\\|"))
            .map(SomeEnum::valueOf)
            .toList();
    }
}
