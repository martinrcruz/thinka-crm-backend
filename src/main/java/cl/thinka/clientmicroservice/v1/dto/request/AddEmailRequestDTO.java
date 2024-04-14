package cl.thinka.clientmicroservice.v1.dto.request;

import lombok.Data;

@Data
public class AddEmailRequestDTO {
    private String title;
    private String subject;
    private String fromEmail;
    private String toEmail;
    private String message;
    private Integer status;
}
