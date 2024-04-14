package cl.thinka.clientmicroservice.v1.dto.request;

import lombok.Data;

@Data
public class WebFormQuoteRequestDTO {
    private String firstName;
    private String lastName;
    private String rut;
    private String email;
    private String city;
    private String projectDescription;
    private String expectedDate;
}
