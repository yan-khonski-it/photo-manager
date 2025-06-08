package com.yk.tools.pm.entity;

import java.time.LocalDateTime;

public record Photo (long id, String filename, String path, String hashCodeValue, LocalDateTime taken, String metadata) {


}
