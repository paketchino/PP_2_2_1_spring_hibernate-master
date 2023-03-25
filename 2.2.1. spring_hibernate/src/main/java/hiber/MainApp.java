package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      CarService carService = context.getBean(CarService.class);
      Car reno = new Car("Reno", 1313);
      Car wolw = new Car("Wolw", 2313);
      Car granda = new Car("Granda", 5676);
      Car porsche = new Car("Porsche", 3131331);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru", reno);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru", wolw);
      User user3 = new User("User3", "Lastname3", "user3@mail.ru", granda);
      User user4 = new User("User4", "Lastname4", "user4@mail.ru", porsche);

      List<Car> allCar = carService.getAllCar();
      for (Car car : allCar) {
         System.out.println("Id = " + car.getId());
         System.out.println("Model = " + car.getModel());
         System.out.println("Series = " + car.getSeries());
         System.out.println();
      }

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println(userService.findUserByModelSeries("Porsche",
              3131331));

      context.close();
   }
}
