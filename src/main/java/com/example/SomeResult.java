package com.example;

import io.micronaut.serde.annotation.Serdeable.Deserializable;

import java.util.List;

@Deserializable
public record SomeResult(
    List<SomeEnum> enumList,
    List<String> stringList
) {}
