
package com.example.nhom2_case.service.Impl;

import com.example.nhom2_case.model.Image;
import com.example.nhom2_case.repository.ImageRepository;
import com.example.nhom2_case.service.IImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImage {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> images(Long id) {
        return imageRepository.images(id);
    }
}
