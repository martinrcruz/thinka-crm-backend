package cl.thinka.clientmicroservice.v1.dto.request;

import cl.thinka.clientmicroservice.v1.jpa.entity.Client;
import lombok.Data;

@Data
public class EditQuoteRequestDTO {
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
}
