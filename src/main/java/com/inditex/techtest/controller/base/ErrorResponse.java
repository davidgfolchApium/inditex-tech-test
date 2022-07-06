package com.inditex.techtest.controller.base;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime now, String message) {
}
