package com.example;

import io.micronaut.serde.ObjectMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@MicronautTest
class MicronautSerdeWrapperTypeTest {

    @Inject
    private ObjectMapper objectMapper;

    @Test
    void testDeserializeJustEnumList() throws IOException {
        var json = """
            {
                "enumList":"A|B|C"
            }""";

        var result = objectMapper.readValue(json, SomeResult.class);

        assert result.stringList() == null;
        assert Objects.equals(result.enumList(), List.of(SomeEnum.A, SomeEnum.B, SomeEnum.C));
    }

    @Test
    void testDeserializeJustStringList() throws IOException {
        var json = """
            {
                "stringList":["aaa","bbb","ccc"]
            }""";

        var result = objectMapper.readValue(json, SomeResult.class);

        assert result.enumList() == null;
        assert Objects.equals(result.stringList(), List.of("aaa", "bbb", "ccc"));
    }

    @Test
    void testDeserializeBoth() throws IOException {
        var json = """
            {
                "enumList":"A|B|C",
                "stringList":["aaa","bbb","ccc"]
            }""";

        var result = objectMapper.readValue(json, SomeResult.class);

        assert Objects.equals(result.enumList(), List.of(SomeEnum.A, SomeEnum.B, SomeEnum.C));
        assert Objects.equals(result.stringList(), List.of("aaa", "bbb", "ccc"));
    }

}
