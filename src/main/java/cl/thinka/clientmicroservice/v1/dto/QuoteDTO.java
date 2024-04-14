package cl.thinka.clientmicroservice.v1.dto;

import cl.thinka.clientmicroservice.v1.jpa.entity.Client;
import lombok.Data;

@Data
public class QuoteDTO {
    private Long id;
    private Client customer;
    private String title;
    private String resume;
    private String description;
    private String startDate;
    private String endDate;
    private String quoteStatus;
    private String domain;
    private int estimatedCost;
    private String estimatedTime;
    private Long inCharge;
    private String createdAt;
    private Long createdBy;
    private String lastModifiedAt;
    private Long lastModifiedBy;
}
