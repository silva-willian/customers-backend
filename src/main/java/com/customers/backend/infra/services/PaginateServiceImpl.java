package com.customers.backend.infra.services;

import com.customers.backend.infra.contracts.services.PaginateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaginateServiceImpl implements PaginateService {
    public <T> List<T> getPage(List<T> sourceList, int start, int limit) {
        if (limit <= 0)
            throw new IllegalArgumentException("invalid page size: " + limit);

        if (sourceList == null || sourceList != null && sourceList.size() <= start)
            return null;

        return sourceList.subList(start, Math.min(start + limit, sourceList.size()));
    }
}
