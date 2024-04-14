package cl.thinka.clientmicroservice.v1.dto.response;

import lombok.Data;

@Data
public class EditQuoteResponseDTO {
    private Long id;
    private Long clientId;
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
