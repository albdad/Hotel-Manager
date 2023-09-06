package hotel_manager.service.implementation;

import hotel_manager.persistence.Room;
import hotel_manager.repository.ImageRepository;
import hotel_manager.repository.RoomRepository;
import hotel_manager.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class RoomServiceImplementation implements RoomService {
    private final RoomRepository roomRepository;
    private final ImageRepository imageRepository;

    @Override
    public Room getRoomByNumber(String roomNumber) {
        return roomRepository.getByName(roomNumber);
    }

    @Override
    public Collection<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room saveRoom(Room room) {
        room.setAvailable(false);
        return roomRepository.save(room);
    }

    @Override
    public void deleteByRoomName(String roomNumber) {
        imageRepository.findByRoomNumber(roomNumber).clear();
        roomRepository.getByName(roomNumber).getImagePaths().forEach(imagePath -> {
            try {
                Files.deleteIfExists(Path.of(imagePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        roomRepository.deleteByRoomName(roomNumber);
    }

    @Override
    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Collection<Room> getRoomsByAvailability(String roomNumber) {
        return roomRepository.getAllRooms().stream()
                .filter(room -> room.getName().equals(roomNumber))
                .filter(Room::isAvailable)
                .collect(Collectors.toList());
    }
}
