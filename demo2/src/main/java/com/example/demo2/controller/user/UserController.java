package com.example.demo2.controller.user;

import com.example.demo2.model.request.authentication.UserRequest;
import com.example.demo2.model.response.UserResponse;
import com.example.demo2.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Update User base on info",
            description = "Get a User object by specifying its id. The response is User object with firstname, lastname,"
                    + "email")
    @PutMapping("/{idUser}")
    public ResponseEntity<UserResponse> updateUser(
            @Valid
            @PathVariable(name = "idUser") Long idUser,
            @RequestBody UserRequest userRequest
    ){
        UserResponse userResponse = userService.updateUser(idUser, userRequest);
        return ResponseEntity.ok(userResponse);
    }


    @Operation(summary = "Delete User base on idUser",
            description = "Delete a User object by specifying its id")
    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long idUser
    ){
        userService.deleteUser(idUser);
        return ResponseEntity.ok(null);
    }


    @Operation(summary = "Get UserDetails base on idUser",
            description = "Get a User object by specifying its id. The response is User object with firstname,"
                    + "lastname, email")
    @GetMapping("/{idUser}")
    public  ResponseEntity<UserResponse> getUserDetails(
            @PathVariable(name = "idUser") Long idUser
    ){
        UserResponse userResponse = userService.getUserDetails(idUser);
        return ResponseEntity.ok(userResponse);
    }


    @Operation(summary = "Get UserList",
            description = "Get a UserList . The response is Supplier object with  name, "
                    + "email")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

}
