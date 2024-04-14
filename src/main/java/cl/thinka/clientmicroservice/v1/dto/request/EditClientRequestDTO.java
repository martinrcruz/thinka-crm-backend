package cl.thinka.clientmicroservice.v1.dto.request;

import cl.thinka.clientmicroservice.v1.jpa.entity.Project;
import lombok.Data;

import java.util.List;

@Data
public class EditClientRequestDTO {
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
}
