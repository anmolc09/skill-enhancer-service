package com.learning.mongo.mongoservice;

import com.learning.models.TimeSlotModel;

import java.util.List;

public interface TimeSlotMongoService {
	
	List<TimeSlotModel> getAllRecords();
	
	List<TimeSlotModel> getLimitedRecords(int count);
	
	List<TimeSlotModel> getSortedRecords(String sortBy);
	
	TimeSlotModel saveRecord(TimeSlotModel record);
	
	List<TimeSlotModel> saveAll(List<TimeSlotModel> recordList);

	TimeSlotModel getRecordById(Long id);

	TimeSlotModel updateRecord(Long id ,TimeSlotModel record);
    
    void deleteRecordById(Long id);

}
