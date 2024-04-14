package cl.thinka.clientmicroservice.v1.dto.request;

import cl.thinka.clientmicroservice.v1.jpa.entity.Client;
import cl.thinka.clientmicroservice.v1.jpa.entity.Project;
import cl.thinka.clientmicroservice.v1.jpa.entity.Quote;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AddSaleRequestDTO {
    private Client customer;
    private Quote quote;
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
}
