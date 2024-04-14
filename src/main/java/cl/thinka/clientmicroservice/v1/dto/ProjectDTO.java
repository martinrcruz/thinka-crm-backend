package cl.thinka.clientmicroservice.v1.dto;

import cl.thinka.clientmicroservice.v1.jpa.entity.File;
import cl.thinka.clientmicroservice.v1.jpa.entity.Tag;
import cl.thinka.clientmicroservice.v1.jpa.entity.Task;
import lombok.Data;
import java.util.List;


@Data
public class ProjectDTO {
    private Long id;
    private String title;
    private String resume;
    private String description;
    private String objectives;
    private String domain;
    private String priority;
    private List<Tag> tags ;
    private List<File> files;
    private File image;
    private String startDate;
    private String endDate;
    private String inCharge;
    private String projectStatus;
    private List<Task> tasks;
    private String createdAt;
    private Long createdBy;
    private String lastModifiedAt;
    private Long lastModifiedBy;
    private Integer status;
}
