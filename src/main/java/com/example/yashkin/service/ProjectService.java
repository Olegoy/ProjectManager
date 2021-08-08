package com.example.yashkin.service;

import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ProjectResponseDto;

import java.util.List;

/**
 * Interface with methods for project management
 * @author Oleg Yaskin
 */
public interface ProjectService {

    /**
     * Method for get all exist projects from a database
     * @return List<ProjectResponseDto>
     */
    List<ProjectResponseDto> getAllProjects();

    /**
     * Method for getting a project from the database by ID
     * @param id Long
     * @return ProjectResponseDto
     */
    ProjectResponseDto getById(Long id);

    /**
     * Method for adding a project to the database
     * @param projectRequestDto ProjectRequestDto
     * @return ProjectResponseDto
     */
    ProjectResponseDto addProject(ProjectRequestDto projectRequestDto);

    /**
     * Method for updating the project
     * @param id Long
     * @param projectRequestDto ProjectRequestDto
     * @return ProjectResponseDto
     */
    ProjectResponseDto updateProject(Long id, ProjectRequestDto projectRequestDto);

    /**
     * Method for completing the project
     * @param id Long
     * @return ProjectResponseDto
     */
    ProjectResponseDto setFinishedStatusProject(Long id);

    /**
     * Method for deleting the project
     * @param id Long
     * @return ProjectResponseDto
     */
    ProjectResponseDto deleteProject(Long id);

}
