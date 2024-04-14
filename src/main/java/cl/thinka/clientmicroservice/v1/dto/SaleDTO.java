package cl.thinka.clientmicroservice.v1.dto;

import cl.thinka.clientmicroservice.v1.jpa.entity.Project;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDTO {
    private Long id;
    private Long clientId;
    private Long quoteId;
    private String title;
    private String resume;
    private String description;
    private String saleStatus;
    private String startDate;
    private String endDate;
    private String domain;
    private String briefUrl;
    private Integer cost;
    private String duration;
    private String inCharge;
    private List<Project> project;
    private String paymentType;
    private Integer bruteCost;
    private Integer iva;
    private String paymentStatus;
    private String createdAt;
    private Long createdBy;
    private String lastModifiedAt;
    private Long lastModifiedBy;
}
