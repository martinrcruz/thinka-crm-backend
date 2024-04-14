package cl.thinka.clientmicroservice.v1.dto.request;

import cl.thinka.clientmicroservice.v1.jpa.entity.File;
import cl.thinka.clientmicroservice.v1.jpa.entity.Project;
import cl.thinka.clientmicroservice.v1.jpa.entity.Tag;
import lombok.Data;

import java.util.List;

@Data
public class EditTaskRequestDTO {
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
    private List<Tag> tags;
    private List<File> files;
}
