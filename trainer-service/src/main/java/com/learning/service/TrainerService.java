package com.learning.service;

import com.learning.models.TrainerModel;

import java.util.List;

public interface TrainerService {
	
	List<TrainerModel> getAllRecords();
	
	List<TrainerModel> getLimitedRecords(int count);
	
	List<TrainerModel> getSortedRecords(String sortBy);
	
	TrainerModel saveRecord(TrainerModel record);
	
	List<TrainerModel> saveAll(List<TrainerModel> recordList);

	TrainerModel getRecordById(Long id);

	TrainerModel updateRecord(Long id ,TrainerModel record);
    
    void deleteRecordById(Long id);

}
