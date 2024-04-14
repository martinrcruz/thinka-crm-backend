package cl.thinka.clientmicroservice.v1.dto.response;

import lombok.Data;

@Data
public class EditTaskResponseDTO {
    private Long id;
    private Long projectId;
    private String title;
    private String resume;
    private String description;
    private String objectives;
    private String comments;
    private String taskStatus;
    private String inCharge;
    private String priority;
    private String startDate;
    private String endDate;
}
