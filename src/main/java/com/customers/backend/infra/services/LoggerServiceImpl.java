package com.customers.backend.infra.services;

import com.customers.backend.domain.contracts.services.LoggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoggerServiceImpl implements LoggerService {


    @Override
    public void Info(String message) {
        log.info(message);
    }

    @Override
    public void Info(String message, Object... arguments) {

        if (arguments != null)
            log.info(message, arguments);
        else
            Info(message);
    }

    @Override
    public void Error(String message) {
        log.error(message);
    }

    @Override
    public void Error(String message, Object... arguments) {
        if (arguments != null)
            log.error(message, arguments);
        else
            Info(message);
    }

}
