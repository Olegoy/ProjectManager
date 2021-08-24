package com.example.yashkin.service.impl;

import com.example.yashkin.entity.ProjectEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.feign.BankAccountClient;
import com.example.yashkin.mappers.ProjectMapper;
import com.example.yashkin.model.ProjectStatus;
import com.example.yashkin.repository.ProjectRepository;
import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ProjectResponseDto;
import com.example.yashkin.service.ProjectService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private static Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepository;
    BankAccountClient bankAccountClient;
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, @Qualifier("projectMapperImpl") ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Transactional
    @Override
    public List<ProjectResponseDto> getAllProjects() {

         List<ProjectEntity> allProjectsEntity = projectRepository.findAll();
        List<ProjectResponseDto> allProjects = allProjectsEntity.stream()
                .map(projectMapper::projectResponseDtoFromProjectEntity)
                .collect(Collectors.toList());
        log.info("got all projects");
        return allProjects;

    }

    @Transactional
    @Override
    public ProjectResponseDto getById(Long id) throws NullPointerException {
        ProjectEntity projectEntity = projectRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Project with ID = %d not found", id))
        );

        ProjectResponseDto projectResponseDto = projectMapper.projectResponseDtoFromProjectEntity(projectEntity);
        log.info("project got by id");

        return projectResponseDto;
    }

    @Transactional
    @Override
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {
        if (Boolean.TRUE.equals(bankAccountClient.checkOperationByOwners(projectRequestDto.getCustomer().getFirstName() + projectRequestDto.getCustomer().getLastName(), projectRequestDto.getProjectName()).getBody())) {
            ProjectEntity entity = projectMapper.projectEntityFromProjectRequestDto(projectRequestDto);
            entity.setCustomer(projectMapper.projectEntityFromProjectRequestDto(projectRequestDto).getCustomer());
            entity.setProjectName(projectRequestDto.getProjectName());
            entity.setStatus(projectRequestDto.getStatus());
            ProjectResponseDto projectResponseDto = projectMapper.projectResponseDtoFromProjectEntity(entity);
            log.info("project added");
            return projectResponseDto;
        }
        else {
            log.error("Project did not create!");
            throw new NotFoundException("Project is not paid");
        }
        //return entity;
    }

    @Transactional
    @Override
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto projectRequestDto) throws NullPointerException {
        ProjectEntity entity = projectRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Project with ID = %d not found", id))
        );
        entity.setProjectName(projectRequestDto.getProjectName());
        entity.setCustomer(projectMapper.projectEntityFromProjectRequestDto(projectRequestDto).getCustomer());
        entity.setStatus(projectRequestDto.getStatus());

        ProjectResponseDto projectResponseDto = projectMapper.projectResponseDtoFromProjectEntity(entity);
        log.info("project updated");
        return projectResponseDto;
    }

    @Transactional
    @Override
    public ProjectResponseDto setFinishedStatusProject(Long id) throws NullPointerException {
        ProjectEntity entity = projectRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Project with ID = %d not found", id))
        );
        entity.setStatus(ProjectStatus.FINISHED);

        ProjectResponseDto projectResponseDto = projectMapper.projectResponseDtoFromProjectEntity(entity);
        log.info("changed project's status");
        return projectResponseDto;
    }

    @Transactional
    @Override
    public ProjectResponseDto deleteProject(Long id) throws NullPointerException {
        ProjectEntity entity = projectRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Project with ID = %d not found", id))
        );
        projectRepository.delete(entity);
        ProjectResponseDto projectResponseDto = projectMapper.projectResponseDtoFromProjectEntity(entity);
        log.info("project deleted");
        return projectResponseDto;
    }
}
