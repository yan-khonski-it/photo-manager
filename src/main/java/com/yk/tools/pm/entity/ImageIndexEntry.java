package com.yk.tools.pm.entity;

import java.time.LocalDateTime;

/**
 * Represents a photo in the index.
 */
public record ImageIndexEntry(
    Long id, // Should be NULL when inserting into index. AUTO-INCREMENTED.
    String filename,
    String path,
    Long fileSize,
    Integer width,
    Integer height,
    String bytesHash,
    LocalDateTime createdDateTime,
    String make,
    String model,
    String metadata) {
}
