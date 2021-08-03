package com.example.yashkin.service;

import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {

    List<ProjectResponseDto> getAllProjects();

    ProjectResponseDto getById(Long id);

    ProjectResponseDto addProject(ProjectRequestDto projectRequestDto);

    ProjectResponseDto updateProject(Long id, ProjectRequestDto projectRequestDto);

    ProjectResponseDto setFinishedStatusProject(Long id);

    ProjectResponseDto deleteProject(Long id);

}
