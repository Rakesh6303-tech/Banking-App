package com.example.banking.Exception;

import java.time.LocalTime;

public record ErrorDetails(LocalTime timestamp,
                           String message,
                           String details,
                           String errorCode) {
}
