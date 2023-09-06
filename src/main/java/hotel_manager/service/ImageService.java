package hotel_manager.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

public interface ImageService {
    String uploadImage(String roomNumber, MultipartFile image) throws IOException;

    byte[] getImage(String imageName);
    void deleteImage(String imageName);
    Collection<byte[]> getAll();
    Collection<byte[]> getAllFromRoom(String roomNumber);
}
