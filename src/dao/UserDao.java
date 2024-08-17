package dao;

import entities.User;
import thrillio.DataStore;

import java.util.List;

public class UserDao {
    public List<User> getUsers() {
        return DataStore.getUsers();
    }

}
