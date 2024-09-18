import com.tuspring.DataGenerator;
import com.tuspring.config.ApplicationConfig;
import com.tuspring.config.JdbcConfig;
import com.tuspring.repository.FriendshipRepository;
import com.tuspring.repository.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.tuspring.repository.LikeRepository;
import com.tuspring.repository.PostRepository;


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        UserRepository userRepository = context.getBean(UserRepository.class);
        FriendshipRepository friendshipRepository = context.getBean(FriendshipRepository.class);
        PostRepository postRepository = context.getBean(PostRepository.class);
        LikeRepository likeRepository = context.getBean(LikeRepository.class);

        DataGenerator generator = new DataGenerator();
        userRepository.saveAll(generator.generateUsers(1000));

        // Perform the query for users with more than 100 friends and 100 likes in March 2025
        // Implement query logic in repositories


        context.close();
    }
}
