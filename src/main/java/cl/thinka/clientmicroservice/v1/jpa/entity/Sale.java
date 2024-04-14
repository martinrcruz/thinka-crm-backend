package cl.thinka.clientmicroservice.v1.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "quote_id")
    private Long quoteId;

    private String title;

    private String resume;

    private String description;

    @Column(name = "sale_status")
    private String saleStatus;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    private String domain;

    @Column(name = "brief_url")
    private String briefUrl;

    private Integer cost;

    private String duration;

    @Column(name = "in_charge")
    private String inCharge;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Project> project;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "brute_cost")
    private Integer bruteCost;

    private Integer iva;

    @Column(name = "payment_status")
    private String paymentStatus;

    @CreationTimestamp
    @Column(name = "created_at", updatable=false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", updatable=false)
    private Long createdBy;

    @UpdateTimestamp
    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @Column(name = "last_modified_by")
    private Long lastModifiedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "deleted_by")
    private Long deletedBy;

    @Column(columnDefinition = "int default 1", nullable = false)
    private Integer status = 1;
}

