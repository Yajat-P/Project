package nitconf.reviewermodule.reviewer.Repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import nitconf.reviewermodule.reviewer.Entities.User;

public interface UserRepository extends MongoRepository<User,String> {


    Optional<User> findByEmail(String email);

    User findByUserId(Integer userId);
}
