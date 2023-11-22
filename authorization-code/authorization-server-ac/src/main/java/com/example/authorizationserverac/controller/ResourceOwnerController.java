package com.example.authorizationserverac.controller;


import com.example.authorizationserverac.dto.ResourceOwnerDTO;
import com.example.authorizationserverac.dto.ResourceOwnerTypeDTO;
import com.example.authorizationserverac.service.ResourceOwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource-owners")
@RequiredArgsConstructor
public class ResourceOwnerController {

    private final ResourceOwnerService resourceOwnerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid ResourceOwnerDTO userDTO) {
        resourceOwnerService.createUser(userDTO);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeUserType(@RequestBody @Valid ResourceOwnerTypeDTO userTypeDTO) {
        resourceOwnerService.changeUserType(userTypeDTO);
    }

}
