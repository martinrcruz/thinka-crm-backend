package cl.thinka.clientmicroservice.v1.dto;

import cl.thinka.clientmicroservice.v1.jpa.entity.File;
import cl.thinka.clientmicroservice.v1.jpa.entity.Project;
import cl.thinka.clientmicroservice.v1.jpa.entity.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class TaskDTO {
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
    private String createdAt;
    private Long createdBy;
    private String lastModifiedAt;
    private Long lastModifiedBy;
}
