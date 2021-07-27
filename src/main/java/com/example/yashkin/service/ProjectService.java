package com.example.yashkin.service;

import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ProjectResponseDto;

import java.util.UUID;

public interface ProjectService {

    ProjectResponseDto getById(UUID id);

    ProjectResponseDto addProject(ProjectRequestDto projectRequestDto);

    ProjectResponseDto updateProject(ProjectRequestDto projectRequestDto);

    ProjectResponseDto deleteProject(UUID id);

}
