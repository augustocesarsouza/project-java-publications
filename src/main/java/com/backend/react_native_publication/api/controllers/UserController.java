package com.backend.react_native_publication.api.controllers;

import com.backend.react_native_publication.application.dto.UserDTO;
import com.backend.react_native_publication.application.dto.UserLoginDTO;
import com.backend.react_native_publication.application.dto.validations.userValidationDTOs.UserCreateValidatorDTO;
import com.backend.react_native_publication.application.services.ResultService;
import com.backend.react_native_publication.application.services.interfaces.IUserAuthenticationService;
import com.backend.react_native_publication.application.services.interfaces.IUserManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@CrossOrigin
@RequestMapping("/v1")
public class UserController {
    private final IUserManagementService userManagementService;
    private final IUserAuthenticationService userAuthenticationService;

    @Autowired
    public UserController(IUserManagementService userManagementService,
                          IUserAuthenticationService userAuthenticationService) {
        this.userManagementService = userManagementService;
        this.userAuthenticationService = userAuthenticationService;
    }

    @GetMapping("/user/find-by-id/{userId}")
    public ResponseEntity<ResultService<UserDTO>> findById(@PathVariable String userId){
        var result = userManagementService.findById(userId);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/public/user/login/{email}/{password}")
    public ResponseEntity<ResultService<UserLoginDTO>> login(@PathVariable String email, @PathVariable String password){
        var result = userAuthenticationService.Login(email, password);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }


    @PostMapping("/public/user/create")
    public ResponseEntity<ResultService<UserDTO>> Create(@Valid @RequestBody UserCreateValidatorDTO userCreateValidatorDTO, BindingResult resultValid){
        var result = userManagementService.create(userCreateValidatorDTO, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
