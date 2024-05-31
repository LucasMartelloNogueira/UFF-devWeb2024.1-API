package id.uff.lucasmartello20241.devwebapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.uff.lucasmartello20241.devwebapi.model.dtos.UserDTO;
import id.uff.lucasmartello20241.devwebapi.model.entities.User;
import id.uff.lucasmartello20241.devwebapi.model.utils.PageResult;
import id.uff.lucasmartello20241.devwebapi.services.UserService;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.create(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> read(@PathVariable("id") int id) {
        UserDTO userDTO = UserDTO.fromEntity(userService.read(id));
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PutMapping()
    public ResponseEntity<UserDTO> update(@RequestBody User user) {
        UserDTO userDTO = UserDTO.fromEntity(userService.update(user));
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping()
    public ResponseEntity<PageResult<UserDTO>> findAllPaginated(
        @RequestParam(value = "page", defaultValue = "0") int page, 
        @RequestParam(value = "size", defaultValue = "3") int size) {
        
        PageRequest pageable = PageRequest.of(page, size);
        Page<User> pageUser = userService.findAllPaginated(pageable);

        List<UserDTO> users = new ArrayList<>();
        pageUser.getContent().forEach((user) -> users.add(UserDTO.fromEntity(user)));

        PageResult<UserDTO> pageResult = new PageResult<>(
            pageUser.getTotalElements(), 
            pageUser.getTotalPages(), 
            pageUser.getNumber(),
            pageUser.getNumberOfElements(),
            users
        );
        
        return ResponseEntity.ok().body(pageResult);

    }

}
