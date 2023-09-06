package hotel_manager.service.implementation;

import hotel_manager.persistence.Manager;
import hotel_manager.repository.ManagerRepository;
import hotel_manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
public class ManagerServiceImplementation implements ManagerService {

    private final ManagerRepository managerRepository;

    @Override
    public Manager createNewManager(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public String deleteManager(Long id) {
        managerRepository.deleteById(id);
        return "Manager deleted";
    }

    @Override
    public Collection<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public Manager updateManager(Manager manager) {
        managerRepository.save(manager);
        return manager;
    }
}
