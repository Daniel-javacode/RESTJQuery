package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DAO.UserDao;
import web.model.Role;
import web.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.read();
    }
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return userDao.readRole();
    }
    @Transactional(readOnly = true)
    public Set<Role> getRoles(String[] ids) {
        return userDao.getRoles(ids);
    }
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userDao.read(id);
    }

    @Transactional
    public void insert(User user) {
        userDao.insert(user);
    }
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }
    @Transactional
    public void deleteUser(Long id) {
        userDao.delete(id);
    }

    public List<List<String>> getUserRoles(List<Role> allRoles, User user) {
        List<List<String>> newMap1 = new ArrayList<>();

        allRoles.forEach(role -> {
            List<String> newMap = new ArrayList<>();

            newMap.add(String.valueOf(role.getId()));
            newMap.add(role.getRole());
            newMap.add(user.isRoleInUser(role) ? "true" : "false");
            newMap1.add(newMap);
        });

        return newMap1;
    }

}
