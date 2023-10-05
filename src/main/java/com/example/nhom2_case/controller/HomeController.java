package com.example.nhom2_case.controller;

import com.example.nhom2_case.model.Home;
import com.example.nhom2_case.model.Image;
import com.example.nhom2_case.model.User;
import com.example.nhom2_case.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/homes")
public class HomeController {
    @Autowired
    private IHomeService homeService;
    @Value("${upload.path}")
    private String upload;
    @GetMapping
    public ResponseEntity<Iterable<Home>> findAll() {
        return new ResponseEntity<>(homeService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Home> findOne(@PathVariable long id) {
        Optional<Home> home = homeService.findOne(id);
        if (home.isPresent()) {
            return new ResponseEntity<>(home.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestPart("homes") Home home,
                                  @RequestPart(value = "file", required = false) MultipartFile file) {
        getImagePath(home, file);
        homeService.save(home);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/img/{id}")
    public ResponseEntity<Iterable<Image>> image(@PathVariable Long id) {
        return new ResponseEntity<>(homeService.image(id), HttpStatus.OK);

    }
    private void getImagePath(Home home, MultipartFile file) {
        List<Image> list = new ArrayList<>();
        List<Image> list2 = new ArrayList<>();
        if (file.getSize() == 0) {
            if (Objects.equals(home.getIdHome(), null)) {
                list2.add(new Image("download.png"));
                home.setImages(list2);
            }
        } else {
            String name = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(upload + name));
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(new Image(name));
            home.setImages(list);
        }
    }

}