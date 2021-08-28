package com.example.yashkin.service.impl;

import com.example.yashkin.entity.ProjectEntity;
import com.example.yashkin.entity.ReleaseEntity;
import com.example.yashkin.entity.TaskEntity;
import com.example.yashkin.entity.UserEntity;
import com.example.yashkin.exception.NotFoundException;
import com.example.yashkin.mappers.TaskMapper;
import com.example.yashkin.model.TaskStatus;
import com.example.yashkin.repository.ProjectRepository;
import com.example.yashkin.repository.ReleaseRepository;
import com.example.yashkin.repository.TaskRepository;
import com.example.yashkin.repository.UserRepository;

import com.example.yashkin.rest.dto.ProjectRequestDto;
import com.example.yashkin.rest.dto.ReleaseRequestDto;
import com.example.yashkin.rest.dto.TaskRequestDto;
import com.example.yashkin.rest.dto.TaskResponseDto;
import com.example.yashkin.rest.dto.UserRequestDto;
import com.example.yashkin.service.TaskService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private static Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ReleaseRepository releaseRepository;
    private final ProjectRepository projectRepository;

    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, ReleaseRepository releaseRepository, ProjectRepository projectRepository, @Qualifier("taskMapperImpl") TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.releaseRepository = releaseRepository;
        this.projectRepository = projectRepository;
        this.taskMapper = taskMapper;
    }

    @Transactional
    @Override
    public List<TaskResponseDto> findTasksByFilter(String name, String type, TaskStatus status, String projectName, String releaseVersion, String authorName, String executorName) {
        log.info("Поиск задачи по фильтру: Название задачи:{}, Тип задачи: {}, Статус задачи: {}, " + "Название проекта: {}, Версия релиза: {}, Имя автора задачи: {}, Имя исполнителя Задачи: {} ", name, type, status, projectName, releaseVersion, authorName, executorName);

        List<TaskEntity> filteredTasks = taskRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(root.get("name"), "%" + name + "%"));
            }

            if (type != null && !type.isEmpty()) {
                predicates.add(cb.like(root.get("type"), "%" + type + "%"));
            }

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            if (projectName != null && !projectName.isEmpty()) {
                predicates.add(cb.like(root.join("project").get("name"), "%" + projectName + "%"));
            }

            if (releaseVersion != null && !releaseVersion.isEmpty()) {
                predicates.add(cb.equal(root.join("release").get("version"), releaseVersion));
            }

            if (authorName != null && !authorName.isEmpty()) {
                predicates.add(cb.like(root.join("author").get("name"), "%" + authorName + "%"));
            }

            if (executorName != null && !executorName.isEmpty()) {
                predicates.add(cb.like(root.join("executor").get("name"), "%" + executorName + "%"));
            }

            return query
                    .where(predicates.toArray(new Predicate[predicates.size()]))
                    .orderBy(cb.asc(root.get("name")))
                    .getRestriction();
        });

        return filteredTasks.stream()
              .map(taskMapper::taskResponseDtoFromTaskEntity)
              .collect(Collectors.toList());

    }

    @Transactional
    @Override
    public TaskResponseDto getById(Long id) throws NullPointerException {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = %d not found", id))
        );

        TaskResponseDto responseDto = taskMapper.taskResponseDtoFromTaskEntity(taskEntity);
        log.info("task got by id");
        return responseDto;

    }

    @Transactional
    @Override
    public TaskResponseDto addTask(TaskRequestDto requestDto) {

        log.info("Start task add");

        TaskEntity entity = taskMapper.taskEntityFromTaskRequestDto(requestDto);

        taskRepository.save(entity);
        TaskResponseDto responseDto = taskMapper.taskResponseDtoFromTaskEntity(entity);
        log.info("task added");
        return responseDto;

    }

    @Transactional
    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto) throws NullPointerException {
        log.info("Start task updated");
        TaskEntity entity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = %d not found", id))
        );
        entity.setName(taskRequestDto.getName());
        entity.setAuthor(taskMapper.taskEntityFromTaskRequestDto(taskRequestDto).getAuthor());
        entity.setExecutor(taskMapper.taskEntityFromTaskRequestDto(taskRequestDto).getExecutor());
        entity.setStatus(taskRequestDto.getStatus());
        entity.setPriority(taskRequestDto.getPriority());
        entity.setType(taskRequestDto.getType());
        entity.setRelease(taskMapper.taskEntityFromTaskRequestDto(taskRequestDto).getRelease());
        entity.setProjectId(taskMapper.taskEntityFromTaskRequestDto(taskRequestDto).getProjectId());
        TaskResponseDto responseDto = taskMapper.taskResponseDtoFromTaskEntity(entity);
        log.info("task updated");
        return responseDto;
    }

    @Transactional
    @Override
    public TaskResponseDto deleteTask(Long id) throws NullPointerException {
        TaskEntity entity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = %d not found", id))
        );
        taskRepository.delete(entity);
        TaskResponseDto responseDto = taskMapper.taskResponseDtoFromTaskEntity(entity);
        log.info("task deleted");
        return responseDto;
    }

    @Transactional
    @Override
    public List<TaskResponseDto> unfinishedTasksByRelease(Long releaseId) {
        List<TaskEntity> tasksByRelease = taskRepository.findAllByRelease(releaseId);
        List<TaskResponseDto> unfinishedTasks = tasksByRelease.stream()
                .filter((s) -> !s.getStatus().toString().equals("DONE"))
                .map(taskMapper::taskResponseDtoFromTaskEntity)
                .collect(Collectors.toList());

        log.info("got unfinished tasks by release");
        return unfinishedTasks;
    }

    @Transactional
    @Override
    public List<TaskResponseDto> getAllTask() {
        List<TaskEntity> allTasksEntity = taskRepository.findAll();
        List<TaskResponseDto> allTasks = allTasksEntity.stream()
                .map(taskMapper::taskResponseDtoFromTaskEntity)
                .collect(Collectors.toList());

        log.info("got all tasks");
        return allTasks;
    }

    @Transactional
    @Override
    public List<TaskResponseDto> unfinishedTasksByProjectId(Long projectId) {
        List<TaskEntity> tasksByProject = taskRepository.findAllByProjectId(projectId);

        List<TaskResponseDto> unfinishedTasks = tasksByProject.stream()
                .filter((s) -> !s.getStatus().toString().equals("DONE"))
                .map(taskMapper::taskResponseDtoFromTaskEntity)
                .collect(Collectors.toList());

        log.info("got unfinished tasks by project");
        return unfinishedTasks;
    }

    @Transactional
    @Override
    public TaskResponseDto setStatusTask(Long id, TaskStatus status) {
        TaskEntity entity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = %d not found", id))
        );

        entity.setStatus(status);

        TaskResponseDto responseDto = taskMapper.taskResponseDtoFromTaskEntity(entity);
        log.info("changed task's status");
        return responseDto;
    }

    @Transactional
    @Override
    public TaskResponseDto setReleaseTask(Long id, Long releaseId) {
        TaskEntity entity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = %d not found", id))
        );
        entity.setRelease(releaseRepository.getById(releaseId));
        TaskResponseDto responseDto = taskMapper.taskResponseDtoFromTaskEntity(entity);
        log.info("changed task's release");
        return responseDto;
    }

    @Transactional
    @Override
    public List<TaskResponseDto> createFromFile(MultipartFile multipartFile) {

        List<TaskResponseDto> tasks = new ArrayList<>();
        log.info("multipartFile" + multipartFile.getName());
        try (
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), "UTF-8"));
            CSVParser parser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim()))
            {
                Iterable<CSVRecord> records = parser.getRecords();
                for (CSVRecord record : records) {

                    TaskRequestDto requestDto = new TaskRequestDto();
                    requestDto.setId(Long.parseLong(record.get("id")));
                    requestDto.setName(record.get("Name"));
                    requestDto.setAuthor(new UserRequestDto(Long.parseLong(record.get("Author"))));
                    if (!record.get("Executor").isEmpty()) {
                        requestDto.setExecutor(new UserRequestDto(Long.parseLong(record.get("Executor"))));
                    }
                    requestDto.setType(record.get("Type"));
                    requestDto.setStatus(TaskStatus.valueOf(record.get("Status")));
                    requestDto.setPriority(Integer.parseInt(record.get("Priority")));
                    requestDto.setProjectId(new ProjectRequestDto(Long.parseLong(record.get("ProjectId"))));
                    requestDto.setRelease(new ReleaseRequestDto(Long.parseLong(record.get("Release"))));
                    tasks.add(addTask(requestDto));

                    log.info("tasks sent to add");
                }

        } catch (IOException e) {
            log.error("The task did not create!");
        }
        return tasks;
    }

    @Transactional
    @Override
    public TaskResponseDto setExecutorTask(Long id, Long userId) {
        TaskEntity entity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = %d not found", id))
        );
        entity.setExecutor(userRepository.getById(userId));
        TaskResponseDto responseDto = taskMapper.taskResponseDtoFromTaskEntity(entity);
        log.info("changed task's executor");
        return responseDto;
    }
}
