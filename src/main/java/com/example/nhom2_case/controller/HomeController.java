package com.example.nhom2_case.controller;
import com.example.nhom2_case.model.Account;
import com.example.nhom2_case.model.Home;
import com.example.nhom2_case.model.Image;
import com.example.nhom2_case.repository.AccountRepository;
import com.example.nhom2_case.repository.ImageRepository;
import com.example.nhom2_case.security.repository.IAccountRepository;
import com.example.nhom2_case.security.service.IAccountService;
import com.example.nhom2_case.service.IHomeService;
import com.example.nhom2_case.service.IImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/homes")
public class HomeController {
    @Autowired
    IAccountService accountService;
    @Autowired
    private IImage image;
    @Autowired
    private IHomeService homeService;

    @Value("${upload.path}")
    private String upload;

    @Autowired
    private ImageRepository imageRepository;


    @Autowired
    private AccountRepository accountRepository ;
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
                                  @RequestPart("account") String name,
                                  HttpServletRequest request) {
        home.setAccount(accountService.findByUsername(name));
        Home homeDB = homeService.saveWithImg(home);
        List<MultipartFile> files = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MultipartFile file = ((MultipartHttpServletRequest) request).getFile("image" + i);
            if (file !=  null) {
                files.add(file);
            } else {
                break;
            }
        }
        getImagePath(homeDB, files);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/img/{id}")
    public List<Image> images(@PathVariable Long id) {

        return imageRepository.images(id);
    }
    private void getImagePath(Home home, List<MultipartFile> files) {
        List<Image> images = new ArrayList<>();
        for (MultipartFile f:files) {
            String img = f.getOriginalFilename();
            try {
                FileCopyUtils.copy(f.getBytes(), new File(upload + img));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image image = new Image(img);
            image.setHome(home);
            images.add(image);

    }
        imageRepository.saveAll(images);
    }

    @GetMapping("/findAcc/{idAcc}")
    public Account findAcc(@PathVariable Long idAcc) {
        Optional<Account> acc = accountRepository.findById(idAcc);
        if (acc.isPresent()) {
            return acc.get();
        } else {
            return null;
        }
    }

    @GetMapping("/search/{search}")
    public ResponseEntity<?> searchByName(@PathVariable String search){
        return new ResponseEntity<>(homeService.searchByName(search),HttpStatus.OK);
    }
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Home> optionalHome = homeService.findOne(id);
        if (optionalHome.isPresent()) {
            Home home = optionalHome.get();
            home.setDeleted(1);
            homeService.save(home);
        }
    }

    @PostMapping("/displayByHost/{username}")
    public ResponseEntity<List<Home>> getHome(@PathVariable("username") String username) {
        Long idAccount = accountRepository.idAccount(username);
        List<Home> homes = homeService.getHome(idAccount);
        return new ResponseEntity<>(homes, HttpStatus.OK);
    }
}
