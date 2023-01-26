package com.learning.service;

import com.learning.models.StudentBatchModel;

import java.util.List;

public interface StudentBatchService{
	
	List<StudentBatchModel> getAllRecords();
	
	List<StudentBatchModel> getLimitedRecords(int count);
	
	List<StudentBatchModel> getSortedRecords(String sortBy);
	
	StudentBatchModel saveRecord(StudentBatchModel record);
	
	List<StudentBatchModel> saveAll(List<StudentBatchModel> recordList);

	StudentBatchModel getRecordById(Long id);

	StudentBatchModel updateRecord(Long id ,StudentBatchModel record);
    
    void deleteRecordById(Long id);

}
