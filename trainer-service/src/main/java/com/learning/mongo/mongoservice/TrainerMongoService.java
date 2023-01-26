package com.learning.mongo.mongoservice;

import com.learning.models.TrainerModel;

import java.util.List;

public interface TrainerMongoService {
	
	List<TrainerModel> getAllRecords();
	
	List<TrainerModel> getLimitedRecords(int count);
	
	List<TrainerModel> getSortedRecords(String sortBy);
	
	TrainerModel saveRecord(TrainerModel record);
	
	List<TrainerModel> saveAll(List<TrainerModel> recordList);

	TrainerModel getRecordById(Long id);

	TrainerModel updateRecord(Long id ,TrainerModel record);
    
    void deleteRecordById(Long id);

}
