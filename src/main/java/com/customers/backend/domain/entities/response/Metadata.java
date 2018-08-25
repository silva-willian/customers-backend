package com.customers.backend.domain.entities.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Metadata {
    private MetadataType type;
    private int start;
    private int limit;
    private int total;
}
