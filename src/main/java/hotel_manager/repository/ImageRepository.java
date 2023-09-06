package hotel_manager.repository;

import hotel_manager.persistence.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByImageName(String name);
    void deleteByName(String imageName);
    Collection<Image> findByRoomNumber(String roomNumber);
}
