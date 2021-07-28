package com.example.yashkin.service.impl;

import com.example.yashkin.entity.ProjectEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.mappers.ProjectMapper;
import com.example.yashkin.repository.ProjectRepository;
import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ProjectResponseDto;
import com.example.yashkin.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    private ProjectMapper INSTANCE;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper INSTANCE) {
        this.projectRepository = projectRepository;
        this.INSTANCE = INSTANCE;
    }

    @Transactional
    @Override
    public ProjectResponseDto getById(Long id) throws NullPointerException {
        ProjectEntity projectEntity = projectRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Project with ID = %d not found", id))
        );

        ProjectResponseDto projectResponseDto = INSTANCE.projectResponseDtoFromProjectEntity(projectEntity);

        return projectResponseDto;
    }

    @Transactional
    @Override
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {
        ProjectEntity entity = INSTANCE.projectEntityFromProjectRequestDto(projectRequestDto);
        entity.setCustomer(projectRequestDto.getCustomer());
        entity.setProjectName(projectRequestDto.getProjectName());
        entity.setStatus(projectRequestDto.getStatus());
        ProjectResponseDto projectResponseDto = INSTANCE.projectResponseDtoFromProjectEntity(entity);

        return  projectResponseDto;
        //return entity;
    }

    @Transactional
    @Override
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto projectRequestDto) throws NullPointerException {
        ProjectEntity entity = projectRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Project with ID = %d not found", id))
        );
        ProjectEntity entity1 = INSTANCE.projectEntityFromProjectRequestDto(projectRequestDto);
        projectRepository.save(entity1);
        ProjectResponseDto projectResponseDto = INSTANCE.projectResponseDtoFromProjectEntity(entity1);
        return projectResponseDto;
    }

    @Transactional
    @Override
    public ProjectResponseDto deleteProject(Long id) throws NullPointerException {
        ProjectEntity entity = projectRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Project with ID = %d not found", id))
        );
        projectRepository.delete(entity);
        ProjectResponseDto projectResponseDto = INSTANCE.projectResponseDtoFromProjectEntity(entity);
        return projectResponseDto;
    }
}
