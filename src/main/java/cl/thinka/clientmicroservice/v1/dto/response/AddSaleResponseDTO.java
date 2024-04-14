package cl.thinka.clientmicroservice.v1.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddSaleResponseDTO {
    private Long clientId;
    private Long quoteId;
    private String title;
    private String resume;
    private String description;
    private String saleStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String domain;
    private String briefUrl;
    private int cost;
    private String duration;
    private String inCharge;
    private Long projectId;
    private String paymentType;
    private Integer bruteCost;
    private Integer iva;
    private Integer total;
    private String paymentStatus;
}
