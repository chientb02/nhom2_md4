package com.example.nhom2_case.controller;
import com.example.nhom2_case.model.Account;
import com.example.nhom2_case.model.User;
import com.example.nhom2_case.repository.AccountRepository;
import com.example.nhom2_case.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @Value("${upload.path}")
    private String upload;

    @Autowired
    private AccountRepository accountRepository ;
    @GetMapping
    public ResponseEntity<Iterable<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/role/{id}")
    public ResponseEntity<Iterable<User>> findByUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findByAccount(id), HttpStatus.OK);
    }
    @GetMapping("/roleAdm")
    public ResponseEntity<Iterable<User>> findByUser() {
        return new ResponseEntity<>(userService.findByAdm(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findOne(@PathVariable Long id) {
        Optional<User> user = userService.findOne(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestPart("user") User user,
                                  @RequestPart(value = "file", required = false) MultipartFile file) {
        getImagePath(user, file);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/allow")
    public ResponseEntity<?> save(@RequestBody Account account) {
        accountRepository.save(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    private void getImagePath(User user, MultipartFile file) {
        if (file.getSize() == 0) {
            if (Objects.equals(user.getIdUser(), null)) {
                user.setAvatar("thanh.jpg");
            }
        } else {
            String name = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(upload + name));
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.setAvatar(name);
        }
    }
}
