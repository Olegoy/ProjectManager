package com.example.yashkin.service.impl;

import com.example.yashkin.entity.ProjectEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.mappers.ProjectMapper;
import com.example.yashkin.repository.ProjectRepository;
import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ProjectResponseDto;
import com.example.yashkin.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    @Override
    public ProjectResponseDto getById(UUID id) throws NullPointerException {
        ProjectEntity projectEntity = projectRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Project with ID = ' ' not found")
        );

        ProjectResponseDto projectResponseDto = ProjectMapper.INSTANCE.projectResponseDtoFromProjectEntity(projectEntity);

        return projectResponseDto;
    }

    @Transactional
    @Override
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {
        ProjectEntity entity = ProjectMapper.INSTANCE.projectEntityFromProjectRequestDto(projectRequestDto);
        projectRepository.save(entity);
        ProjectResponseDto projectResponseDto = ProjectMapper.INSTANCE.projectResponseDtoFromProjectEntity(entity);

        return  projectResponseDto;
        //return entity;
    }

    @Transactional
    @Override
    public ProjectResponseDto updateProject(ProjectRequestDto projectRequestDto) throws NullPointerException {
        ProjectEntity entity = projectRepository.findByProjectName(projectRequestDto.getName()).orElseThrow(
                () -> new NotFoundException("Project with Name = ' ' not found")
        );
        ProjectEntity entity1 = ProjectMapper.INSTANCE.projectEntityFromProjectRequestDto(projectRequestDto);
        projectRepository.save(entity1);
        ProjectResponseDto projectResponseDto = ProjectMapper.INSTANCE.projectResponseDtoFromProjectEntity(entity1);
        return projectResponseDto;
    }

    @Transactional
    @Override
    public ProjectResponseDto deleteProject(UUID id) throws NullPointerException {
        ProjectEntity entity = projectRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Project with ID = ' ' not found")
        );
        projectRepository.delete(entity);
        ProjectResponseDto projectResponseDto = ProjectMapper.INSTANCE.projectResponseDtoFromProjectEntity(entity);
        return projectResponseDto;
    }
}
