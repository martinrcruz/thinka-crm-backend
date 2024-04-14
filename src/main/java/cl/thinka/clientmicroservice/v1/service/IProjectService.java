package cl.thinka.clientmicroservice.v1.service;

import cl.thinka.clientmicroservice.v1.dto.request.AddProjectRequestDTO;
import cl.thinka.clientmicroservice.v1.dto.request.EditProjectRequestDTO;
import cl.thinka.clientmicroservice.v1.exception.ThinkaException;
import cl.thinka.clientmicroservice.v1.util.ThinkaResponse;

import java.util.List;

public interface IProjectService {

    public ThinkaResponse listProjects() throws ThinkaException;
    public ThinkaResponse getProjectById(Long id) throws ThinkaException;
    public ThinkaResponse deleteProjectById(Long id) throws ThinkaException;
    public ThinkaResponse editProject(EditProjectRequestDTO project) throws ThinkaException;
    public ThinkaResponse saveProject(AddProjectRequestDTO project) throws ThinkaException;
}
