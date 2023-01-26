package com.learning.mongo.mongoRepository;

import com.learning.mongo.collection.StudentCollection;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface StudentMongoRepository extends MongoRepository<StudentCollection, Long> {

}
