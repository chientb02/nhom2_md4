import com.example.nhom2_case.model.Home;
import com.example.nhom2_case.model.Image;
import com.example.nhom2_case.repository.ObjectWithImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@Service
public class ObjectWithImagesService {
    @Autowired
    ObjectWithImagesRepository objectWithImagesRepository;



    public void uploadImages(Long objectId, MultipartFile[] files) {
        Home objectWithImages = objectWithImagesRepository.findById(objectId)
                .orElseThrow(() -> new EntityNotFoundException("Object not found with id: " + objectId));

        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();

            // Xử lý và lưu trữ tệp tin ảnh theo yêu cầu của bạn
            // Ví dụ: Lưu tệp tin vào thư mục hoặc lưu trữ tệp tin trong cơ sở dữ liệu

            Image image = new Image();
            image.setFilename(filename);

            objectWithImages.addImage(image);
        }

        objectWithImagesRepository.save(objectWithImages);
    }
}