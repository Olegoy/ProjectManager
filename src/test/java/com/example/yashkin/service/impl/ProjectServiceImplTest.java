package com.example.yashkin.service.impl;

import com.example.yashkin.entity.ProjectEntity;
import com.example.yashkin.entity.UserEntity;
import com.example.yashkin.feign.BankAccountClient;
import com.example.yashkin.mappers.ProjectMapper;
import com.example.yashkin.mappers.ProjectMapperImpl;
import com.example.yashkin.model.ProjectStatus;
import com.example.yashkin.model.Role;
import com.example.yashkin.repository.ProjectRepository;
import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ProjectResponseDto;
import com.example.yashkin.rest.dto.UserRequestDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceImplTest {

    @Mock
    ProjectRepository projectRepository;
    @MockBean
    BankAccountClient client;
    @Mock
    ProjectMapper projectMapper;
    @InjectMocks
    ProjectServiceImpl service;
//    ProjectServiceImpl service = new ProjectServiceImpl(projectRepository, projectMapper, client);

    private static final Long ID = 1L;
    private static final String PROJECT_NAME = "project";

    @Before
    public void setUp() throws Exception {
/*        List<ProjectEntity> projectEntities = new ArrayList<>();
        ProjectEntity project1 = new ProjectEntity();
        project1.setId(1l);
        ProjectEntity project2 = new ProjectEntity();
        project2.setId(2l);
        projectEntities.add(project1);
        projectEntities.add(project2);*/
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllProjects() {
    }

    @Test
    public void getById() {
        ProjectEntity expected = new ProjectEntity(ID, PROJECT_NAME);
        ProjectRequestDto requestDto = new ProjectRequestDto(ID, PROJECT_NAME, 1l, ProjectStatus.IN_PROGRESS);
        Mockito.when(client.checkOperationByOwners(Mockito.any(), Mockito.any()).getBody()).thenReturn(true);
        service.addProject(requestDto);
        ProjectResponseDto actual = service.getById(ID);
        Mockito.when(projectRepository.findById(Mockito.any())).thenReturn(Optional.of(expected));

//        ProjectResponseDto actual = new ProjectResponseDto();
/*        actual.setProjectName(service.getById(ID).getProjectName());
        actual.setId(service.getById(ID).getId());*/

        Assert.assertEquals(expected.getProjectName(), actual.getProjectName());
    }

    @Test
    public void addProject() {
    }

    @Test
    public void updateProject() {
    }

    @Test
    public void setFinishedStatusProject() {
    }

    @Test
    public void deleteProject() {
    }
}