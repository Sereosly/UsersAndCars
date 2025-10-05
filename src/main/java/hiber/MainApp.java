package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class MainApp {
    public static void main(String[] args) throws SQLException {
        final AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        final UserService userService = context.getBean(UserService.class);

        final Car car1 = new Car("Toyota", 2020);
        final Car car2 = new Car("Honda", 2018);
        final Car car3 = new Car("AUDI", 2009);
        final Car car4 = new Car("AUDI", 2003);

        final User user1 = new User("User1", "Lastname1", "user1@mail.ru", car1);
        final User user2 = new User("User1", "Lastname1", "user1@mail.ru", car2);
        final User user3 = new User("User3", "Lastname3", "user3@mail.ru", car3);
        final User user4 = new User("User4", "Lastname4", "user4@mail.ru", car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);


        System.out.println("Список всех пользователей:");
        final List<User> users = userService.listUsers();
        users.forEach(System.out::println);

        System.out.println("\nПользователи с машиной Honda 2018:");
        final List<User> foundUsers = (userService.getUserByCarModelAndSeries("Honda", 2018));
        System.out.println(foundUsers.isEmpty() ? "Пользователи с машиной Honda 2018 не найдены" :
                foundUsers.stream().map(User::toString).collect(Collectors.joining("\n")));

        System.out.println("\nПользователи, сгруппированные по модели машины:");
        final Map<String, List<User>> usersByCarModel = users.stream()
                .filter(user -> user.getCar() != null)
                .collect(Collectors.groupingBy(user -> user.getCar().getModel()));
        usersByCarModel.forEach((model, userList) ->
                System.out.println("Модель: " + model + "\n" +
                        userList.stream().map(User::toString).collect(Collectors.joining("\n"))));

        context.close();
    }
}
