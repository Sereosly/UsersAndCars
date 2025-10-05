package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(final User user);
   List<User> listUsers();
   List<User> getUserByCarModelAndSeries(String model, int series);
}
