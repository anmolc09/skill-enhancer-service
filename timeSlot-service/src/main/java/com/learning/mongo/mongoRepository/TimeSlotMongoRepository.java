package com.learning.mongo.mongoRepository;

import com.learning.mongo.collections.TimeSlotCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimeSlotMongoRepository extends MongoRepository<TimeSlotCollection, Long> {

}
