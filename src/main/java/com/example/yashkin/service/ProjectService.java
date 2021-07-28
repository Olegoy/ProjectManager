package com.example.yashkin.service;

import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ProjectResponseDto;

public interface ProjectService {

    ProjectResponseDto getById(Long id);

    ProjectResponseDto addProject(ProjectRequestDto projectRequestDto);

    ProjectResponseDto updateProject(Long id, ProjectRequestDto projectRequestDto);

    ProjectResponseDto deleteProject(Long id);

}
