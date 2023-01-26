package com.learning.service;

import com.learning.models.BatchModel;

import java.util.List;

public interface BatchService {
	
	List<BatchModel> getAllRecords();
	
	List<BatchModel> getLimitedRecords(int count);
	
	List<BatchModel> getSortedRecords(String sortBy);
	
	BatchModel saveRecord(BatchModel record);
	
	List<BatchModel> saveAll(List<BatchModel> recordList);

	BatchModel getRecordById(Long id);

	BatchModel updateRecord(Long id ,BatchModel record);
    
    void deleteRecordById(Long id);

}
