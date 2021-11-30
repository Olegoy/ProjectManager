package com.example.yashkin.service;

import com.example.yashkin.rest.dto.ReleaseRequestDto;
import com.example.yashkin.rest.dto.ReleaseResponseDto;

import java.util.List;

/**
 * Interface with methods for release management
 * @author Oleg Yaskin
 */
public interface ReleaseService {

    /**
     * Method for get all exist release from a database
     * @return List<ReleaseResponseDto>
     */
    public List<ReleaseResponseDto> getAllRelease();

    /**
     * Method for getting a release from the database by ID
     * @param id Long
     * @return ReleaseResponseDto
     */
    ReleaseResponseDto getById(Long id);

    /**
     * Method for adding a release to the database
     * @param releaseRequestDto ReleaseRequestDto
     * @return ReleaseResponseDto
     */
    ReleaseResponseDto addRelease(ReleaseRequestDto releaseRequestDto);

    /**
     * Method for updating the release
     * @param id Long
     * @param releaseRequestDto ReleaseRequestDto
     * @return ReleaseResponseDto
     */
    ReleaseResponseDto updateRelease(Long id, ReleaseRequestDto releaseRequestDto);

    /**
     * Method for deleting the release
     * @param id Long
     * @return ReleaseResponseDto
     */
    ReleaseResponseDto deleteRelease(Long id);
}
