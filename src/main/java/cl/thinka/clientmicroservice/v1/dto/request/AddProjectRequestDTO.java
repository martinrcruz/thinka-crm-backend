package cl.thinka.clientmicroservice.v1.dto.request;

import cl.thinka.clientmicroservice.v1.jpa.entity.File;
import cl.thinka.clientmicroservice.v1.jpa.entity.Tag;
import cl.thinka.clientmicroservice.v1.jpa.entity.Task;
import lombok.Data;

import java.util.List;

@Data
public class AddProjectRequestDTO {
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
    private String projectStatus;
    private String inCharge;
    private List<Task> tasks;
}
