package com.cirruslabs.technical.controller;

import com.cirruslabs.technical.model.ResponseDto;
import com.cirruslabs.technical.model.UserRequestDto;
import com.cirruslabs.technical.service.GeolocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class UserController {
    private final GeolocationService geolocationService;

    public UserController(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }


    @Operation(summary = "Register an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User welcome message",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid payload parameters", content = @Content)})
    @PostMapping(value = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseDto> create(@Valid @RequestBody UserRequestDto user) {
        ResponseDto createResponse = ResponseDto.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .message(geolocationService.validateCanadaLocation(user.getIpAddress(), user.getUserName()))
                .build();
        return new ResponseEntity<>(createResponse, HttpStatus.OK);
    }
}


