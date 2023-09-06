package hotel_manager.service;

import hotel_manager.persistence.Room;

import java.util.Collection;

public interface RoomService {

    Room getRoomByNumber(String roomNumber);

    Collection<Room> getAllRooms();

    Room saveRoom(Room room);

    void deleteByRoomName(String roomNumber);

    Room updateRoom(Room room);

    Collection<Room> getRoomsByAvailability(String roomNumber);
}
