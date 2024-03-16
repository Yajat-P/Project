package nitconf.reviewermodule.reviewer.Repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import nitconf.reviewermodule.reviewer.Entities.User;

public interface UserRepository extends MongoRepository<User,ObjectId> {


    User findUserByemail(String email);

}
