package com.mystreet.exception;
import java.time.Instant;
public record ErrorResponse(Instant timestamp, String path, String error, String message) {}
