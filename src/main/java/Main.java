import com.tuspring.config.DataGenerator;
import com.tuspring.config.ApplicationConfig;
import com.tuspring.config.DatabaseSetup;
import com.tuspring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;


public class Main {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        DatabaseSetup dbSetUp = context.getBean(DatabaseSetup.class);
        dbSetUp.dropTables();
        dbSetUp.createTables();
        logger.info("Tables created");

        DataGenerator generator = context.getBean(DataGenerator.class);
        generator.generateUsers();
        generator.generateFriendships();
        generator.generatePosts();
        generator.generateLikes();
        logger.info("Data generated");

        UserService userService = context.getBean(UserService.class);

        long friendShipNumberAverage = userService.getFriendshipNumberAverage();

        logger.info(String.format("Users with more than %s friends and 100 likes in the last month: %n", friendShipNumberAverage));
        userService.getUserNamesWithMoreThanFriendsNumberAnd100LikesInTheLasthMonth(friendShipNumberAverage).forEach(System.out::println);

        dbSetUp.dropTables();
        context.close();
    }
}
