package com.customers.backend.domain.contracts.services;

public interface LoggerService {
    void Info(String message);
    void Info(String message, Object... arguments);
    void Error(String message);
    void Error(String message, Object... arguments);
}