package cl.thinka.clientmicroservice.v1.dto.response;

import lombok.Data;

@Data
public class AddEmailResponseDTO {
    private String title;
    private String subject;
    private String fromEmail;
    private String toEmail;
    private String message;
    private Integer status;
}
