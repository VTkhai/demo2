package com.example.demo2.controller.user;

import com.example.demo2.entity.authentication.Role;
import com.example.demo2.model.request.authentication.RegisterRequest;
import com.example.demo2.model.response.UserResponse;
import com.example.demo2.service.user.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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
            @RequestBody RegisterRequest userRequest
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
            description = "Get a UserList . The response is User object with name, "
                    + "email")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Search users base on firstname or lastname", description = "Search Users")
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchUsers(
            @RequestParam(required = false) String firstname,@RequestParam(required = false) String lastname) {

        List<UserResponse> userResponses = userService.searchUsers(firstname, lastname);
        return ResponseEntity.ok(userResponses);
    }

    @Operation(summary = "Search users base on role", description = "Search Users by role")
    @GetMapping("/search/role")
    public ResponseEntity<List<UserResponse>> searchUserRole(@RequestParam Role role) {
        List<UserResponse> users = userService.searchByRole(role);
        return ResponseEntity.ok(users);
    }
}
