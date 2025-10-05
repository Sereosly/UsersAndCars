package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      User user1 = new User();
      user1.setFirstName("User1");
      user1.setLastName("Lastname1");
      user1.setEmail("user1@mail.ru");

      User user2 = new User();
      user2.setFirstName("User2");
      user2.setLastName("Lastname2");
      user2.setEmail("user2@mail.ru");

      User user3 = new User("User3", "Lastname3", "user3@gmail.com");
      User user4 = new User("User3", "Lastname3", "user3@gmail.com");

      Car car1 = new Car();
      car1.setModel("Toyota");
      car1.setSeries(2020);

      Car car2 = new Car("Honda",2018);
      Car car3 = new Car("BMW", 2009);
      Car car4 = new Car("AUDI", 2003);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User foundUser = userService.getUserByCarModelAndSeries("Honda", 2018);
      if (foundUser != null) {
         System.out.println("Id = "+foundUser.getId());
         System.out.println("First Name = "+foundUser.getFirstName());
         System.out.println("Last Name = "+foundUser.getLastName());
      } else {
         System.out.println("No user found");
      }

      context.close();
   }
}
