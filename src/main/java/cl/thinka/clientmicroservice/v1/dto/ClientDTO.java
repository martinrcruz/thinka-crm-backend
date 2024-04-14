package cl.thinka.clientmicroservice.v1.dto;

import cl.thinka.clientmicroservice.v1.jpa.entity.Project;
import lombok.Data;

import java.util.List;

@Data
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String rut;
    private String email;
    private String contactNumber;
    private String workline;
    private String address;
    private String city;
    private String domain;
    private String clientStatus;
    private Long inCharge;
    private String contactPlatform;
    private String description;
    private List<Project> projects;
    private String createdAt;
    private Long createdBy;
    private String lastModifiedAt;
    private Long lastModifiedBy;
}
