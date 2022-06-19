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
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(new Car("m1",1));
      userService.add(user1);
      User user2= new User("User2", "Lastname2", "user2@mai.ru");
      user2.setCar(new Car("m2",12));
      userService.add(user2);
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user3.setCar(new Car("m3",3));
      userService.add(user3);
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setCar(new Car("m4",4));
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      System.out.println("search users with id :m1 and series: 1");
      List<User> usersfiltered = userService.getUsers("m1", 1);
      for (User user : usersfiltered) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      System.out.println("search users with id :m3 and series: 4");
      List<User> usersfiltered1 = userService.getUsers("m3", 4);
      if (!usersfiltered1.isEmpty()) {
         for (User user : usersfiltered1) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
         }
      } else System.out.println("No such users");

      context.close();
   }
}
