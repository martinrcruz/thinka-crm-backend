package cl.thinka.clientmicroservice.v1.dto;

import lombok.Data;

@Data
public class EmailDTO {
    private Long id;
    private String title;
    private String subject;
    private String fromEmail;
    private String toEmail;
    private String message;
    private Integer status;
}
