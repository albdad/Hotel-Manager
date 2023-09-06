package hotel_manager.service;

import hotel_manager.persistence.Manager;

import java.util.Collection;

public interface ManagerService {
    Manager createNewManager(Manager manager);
    String deleteManager(Long id);
    Collection<Manager> getAllManagers();
    Manager updateManager(Manager manager);

}
