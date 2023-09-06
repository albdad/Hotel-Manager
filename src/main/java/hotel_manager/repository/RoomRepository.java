package hotel_manager.repository;

import hotel_manager.persistence.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room getByName(String roomNumber);

    Collection<Room> getAllRooms();

    void deleteByRoomName(String roomNumber);
}
