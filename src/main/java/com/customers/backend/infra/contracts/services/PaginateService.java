package com.customers.backend.infra.contracts.services;

import java.util.List;

public interface PaginateService {
    <T> List<T> getPage(List<T> sourceList, int start, int limit);
}
