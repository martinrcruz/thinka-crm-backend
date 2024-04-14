package cl.thinka.clientmicroservice.v1.dto.response;

import cl.thinka.clientmicroservice.v1.jpa.entity.Task;
import lombok.Data;

import java.util.List;

@Data
public class AddProjectResponseDTO {
    private String title;
    private String resume;
    private String description;
    private String objectives;
    private String domain;
    private String priority;
    private String tags;
    private String files;
    private String image;
    private String startDate;
    private String endDate;
    private String inCharge;
    private List<Task> tasks;
}
