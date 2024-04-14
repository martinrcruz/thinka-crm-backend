package cl.thinka.clientmicroservice.v1.jpa.repository.impl;

import cl.thinka.clientmicroservice.v1.jpa.entity.Project;
import cl.thinka.clientmicroservice.v1.jpa.repository.ProjectRepository;
import cl.thinka.clientmicroservice.v1.jpa.repository.read.ProjectRepositoryRead;
import cl.thinka.clientmicroservice.v1.jpa.repository.write.ProjectRepositoryWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    @Autowired
    ProjectRepositoryRead projectRepositoryRead;
    @Autowired
    ProjectRepositoryWrite projectRepositoryWrite;

    @Override
    public Project save(Project project) {
        return projectRepositoryWrite.save(project);
    }

    @Override
    public boolean deleteById(Long id, Long userId) {
        projectRepositoryWrite.deleteById(id, userId);
        return true;
    }

    @Override
    public Project getById(Long id) {
        return projectRepositoryRead.getProjectById(id);
    }

    @Override
    public List<Project> getAll() {
        return projectRepositoryRead.findAll();
    }
}
