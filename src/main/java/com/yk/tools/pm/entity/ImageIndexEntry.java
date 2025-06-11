package com.yk.tools.pm.entity;

import java.time.LocalDateTime;

/**
 * Represents a photo in the index.
 */
public record ImageIndexEntry(
    long id,
    String filename,
    String path,
    long fileSize,
    int width,
    int height,
    String bytesHash,
    LocalDateTime createdDateTime,
    String metadata) {
}
