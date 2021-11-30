package com.example.yashkin.service.impl;

import com.example.yashkin.entity.ReleaseEntity;
import com.example.yashkin.mappers.ReleaseMapper;
import com.example.yashkin.repository.ReleaseRepository;
import com.example.yashkin.rest.dto.ReleaseRequestDto;
import com.example.yashkin.rest.dto.ReleaseResponseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ReleaseServiceImplTest {

    private static final Long ID = 1L;
    private static final Integer VERSION = 1;
    private static final LocalDateTime DATE_START = LocalDateTime.of(1999, 4, 1, 10, 10, 10);
    private static final LocalDateTime DATE_END = LocalDateTime.of(2020, 9, 1, 10, 10, 10);

    @Mock
    private ReleaseRepository releaseRepository;

    ReleaseMapper releaseMapper;

    @InjectMocks
    ReleaseServiceImpl releaseService;

    @Before
    public void setUp() throws Exception {
        // init mocks
        releaseMapper = new ReleaseMapper() {
            @Override
            public ReleaseEntity releaseEntityFromReleaseRequestDto(ReleaseRequestDto releaseRequestDto) {
                ReleaseEntity entity = new ReleaseEntity();
                entity.setId(releaseRequestDto.getId());
                entity.setVersion(releaseRequestDto.getVersion());
                entity.setDateStart(releaseRequestDto.getDateStart());
                entity.setDateEnd(releaseRequestDto.getDateEnd());
                return entity;
            }

            @Override
            public ReleaseResponseDto releaseResponseDtoFromReleaseEntity(ReleaseEntity releaseEntity) {
                ReleaseResponseDto responseDto = new ReleaseResponseDto();
                responseDto.setId(releaseEntity.getId());
                responseDto.setVersion(releaseEntity.getVersion());
                responseDto.setDateStart(releaseEntity.getDateStart());
                responseDto.setDateEnd(releaseEntity.getDateEnd());
                return responseDto;
            }

            @Override
            public void updateReleaseEntityFromReleaseRequestDto(ReleaseRequestDto releaseRequestDto, ReleaseEntity releaseEntity) {

            }

            @Override
            public void updateReleaseResponseDtoFromReleaseEntity(ReleaseEntity releaseEntity, ReleaseResponseDto releaseResponseDto) {

            }
        };
        releaseService = new ReleaseServiceImpl(releaseRepository, releaseMapper);
    }

    @Test
    public void getAllRelease() {
        ReleaseEntity testRelease1 = new ReleaseEntity(ID, VERSION, DATE_START, DATE_END);
        ReleaseEntity testRelease2 = new ReleaseEntity(ID + 1, VERSION + 1, DATE_START, DATE_END);
        ReleaseRequestDto requestTestRelease1 = new ReleaseRequestDto(ID, VERSION, DATE_START, DATE_END);
        ReleaseRequestDto requestTestRelease2 = new ReleaseRequestDto(ID + 1, VERSION + 1, DATE_START, DATE_END);
        ReleaseResponseDto responseTestRelease1 = new ReleaseResponseDto(ID, VERSION, DATE_START, DATE_END);
        ReleaseResponseDto responseTestRelease2 = new ReleaseResponseDto(ID + 1, VERSION + 1, DATE_START, DATE_END);

        releaseService.addRelease(requestTestRelease1);
        releaseService.addRelease(requestTestRelease2);

        List<ReleaseEntity> entities = List.of(testRelease1, testRelease2);

        Mockito.when(releaseRepository.findAll()).thenReturn(entities);

        List<ReleaseResponseDto> responseDtoListExpected = List.of(responseTestRelease1, responseTestRelease2);
        List<ReleaseResponseDto> responseDtoListActual = releaseService.getAllRelease();

        Assert.assertEquals(responseDtoListExpected.get(1).getVersion(), responseDtoListActual.get(1).getVersion());

    }

    @Test
    public void getById() {
        ReleaseEntity expected = new ReleaseEntity(ID, VERSION, DATE_START, DATE_END);
        ReleaseRequestDto requestDto = new ReleaseRequestDto(ID, VERSION, DATE_START, DATE_END);

        Mockito.when(releaseRepository.findById(Mockito.any())).thenReturn(Optional.of(expected));

        releaseService.addRelease(requestDto);

        ReleaseResponseDto actual = releaseService.getById(ID);

        Assert.assertEquals(expected.getVersion(), actual.getVersion());

    }

    @Test
    public void addRelease() {
        ReleaseRequestDto expected = new ReleaseRequestDto(ID, VERSION, DATE_START, DATE_END);

        ReleaseResponseDto actual = releaseService.addRelease(expected);

        Assert.assertEquals(expected.getVersion(), actual.getVersion());
    }

    @Test
    public void updateRelease() {
        ReleaseEntity testRelease = new ReleaseEntity(ID, VERSION, DATE_START, DATE_END);
        ReleaseRequestDto requestTestRelease = new ReleaseRequestDto(ID, VERSION, DATE_START, DATE_END);
        ReleaseResponseDto responseDtoTestRelease = releaseService.addRelease(requestTestRelease);

        Long testID = responseDtoTestRelease.getId();
        Integer newVersion = VERSION + 5;
        ReleaseRequestDto expected = new ReleaseRequestDto(testID, newVersion, DATE_START, DATE_END);

        Mockito.when(releaseRepository.findById(Mockito.any())).thenReturn(Optional.of(testRelease));

        ReleaseResponseDto actual = releaseService.updateRelease(testID, expected);

        Assert.assertEquals(newVersion, actual.getVersion());
    }

    @Test
    public void deleteRelease() {
        ReleaseEntity testRelease = new ReleaseEntity(ID, VERSION, DATE_START, DATE_END);
        ReleaseRequestDto expected = new ReleaseRequestDto(ID, VERSION, DATE_START, DATE_END);
        Mockito.when(releaseRepository.findById(Mockito.any())).thenReturn(Optional.of(testRelease));
        ReleaseResponseDto actual = releaseService.addRelease(expected);
        Assert.assertEquals(expected.getVersion(), actual.getVersion());
        Long testId = actual.getId();
        Assert.assertEquals(actual.getVersion(), releaseService.deleteRelease(testId).getVersion());
    }
}