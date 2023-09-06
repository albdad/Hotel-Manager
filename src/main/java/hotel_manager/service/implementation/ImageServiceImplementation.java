package hotel_manager.service.implementation;

import hotel_manager.persistence.Image;
import hotel_manager.persistence.Room;
import hotel_manager.repository.ImageRepository;
import hotel_manager.repository.RoomRepository;
import hotel_manager.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageServiceImplementation implements ImageService {

    private final String folderPath = "src/main/resources/images/";

    @Autowired
    private final ImageRepository imageRepository;

    @Autowired
    private final RoomRepository roomRepository;

    public ImageServiceImplementation(ImageRepository imageRepository, RoomRepository roomRepository) {
        this.imageRepository = imageRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public String uploadImage(String roomNumber, MultipartFile image) throws IOException {
        String filePath = folderPath + roomNumber + "/" + image.getOriginalFilename();
        Image imageToSave = Image.builder()
                .name(image.getOriginalFilename())
                .type(image.getContentType())
                .filepath(filePath)
                .roomNumber(roomNumber)
                .build();
        imageRepository.save(imageToSave);
        saveImagePathToRoom(filePath, roomNumber);
        image.transferTo(new File(filePath));
        return "Image " + image.getOriginalFilename() + " sucessfully uploaded.";
    }

    @Override
    public byte[] getImage(String imageName) {
        Optional<Image> oImage = imageRepository.findByImageName(imageName);
        if (oImage.isPresent()) {
            return findImage(oImage.get().getFilepath());
        }
        return noImageFound();
    }

    @Override
    public void deleteImage(String imageName) {
        try {
            String filePath = folderPath + imageName;
            Files.deleteIfExists(Path.of(filePath));
            imageRepository.deleteByName(imageName);
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public Collection<byte[]> getAll() {
        return imageRepository.findAll()
                .stream()
                .map(image -> findImage(image.getFilepath()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<byte[]> getAllFromRoom(String roomNumber) {
        return imageRepository.findByRoomNumber(roomNumber).stream()
                .map(image -> findImage(image.getFilepath()))
                .collect(Collectors.toList());
    }

    private byte[] noImageFound() {
        String noImageFoundPath = "src/main/resources/images/noImage.png";
        try {
            return Files.readAllBytes(Path.of(noImageFoundPath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private byte[] findImage(String filePath) {
        try {
            return Files.readAllBytes(Path.of(filePath));
        } catch (IOException e) {
            return noImageFound();
        }
    }
    private void saveImagePathToRoom(String imagePath, String roomNumber) {
        Room room = roomRepository.getByName(roomNumber);
        room.getImagePaths().add(imagePath);
        roomRepository.save(room);
    }
}
