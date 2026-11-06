package com.animeword.bakend.shared;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public record DtoError(
    int status,
    String message,
    LocalDateTime timestamp,
    Optional<Map<String,String>> errors
) {
}