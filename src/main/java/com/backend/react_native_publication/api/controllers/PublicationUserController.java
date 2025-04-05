package com.backend.react_native_publication.api.controllers;

import com.backend.react_native_publication.application.dto.PublicationUserDTO;
import com.backend.react_native_publication.application.dto.validations.publicationUserDTOs.PublicationUserCreateValidatorDTO;
import com.backend.react_native_publication.application.services.ResultService;
import com.backend.react_native_publication.application.services.interfaces.IPublicationUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Component
@RestController
@CrossOrigin
@RequestMapping("/v1")
public class PublicationUserController {
    private final IPublicationUserService publicationUserService;

    @Autowired
    public PublicationUserController(IPublicationUserService publicationUserService) {
        this.publicationUserService = publicationUserService;
    }

    @GetMapping("/publication-user/get-all-publication-user")
    public ResponseEntity<ResultService<List<PublicationUserDTO>>> GetAllPublicationUser(){
        var result = publicationUserService.GetAllPublicationUser();

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/publication-user/get-publication-by-user-id/{userId}")
    public ResponseEntity<ResultService<PublicationUserDTO>> GetPublicationByUserId(@PathVariable String userId){
        var result = publicationUserService.GetPublicationByUserId(UUID.fromString(userId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/publication-user/create")
    public ResponseEntity<ResultService<PublicationUserDTO>> Create(@Valid @RequestBody PublicationUserCreateValidatorDTO publicationUserCreateValidatorDTO,
                                                         BindingResult resultValid){
        var result = publicationUserService.Create(publicationUserCreateValidatorDTO, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
