package com.learning.mongo.mongoservice;

import com.learning.models.StudentBatchModel;

import java.util.List;

public interface StudentBatchMongoService {
	
	List<StudentBatchModel> getAllRecords();
	
	List<StudentBatchModel> getLimitedRecords(int count);
	
	List<StudentBatchModel> getSortedRecords(String sortBy);
	
	StudentBatchModel saveRecord(StudentBatchModel record);
	
	List<StudentBatchModel> saveAll(List<StudentBatchModel> recordList);

	StudentBatchModel getRecordById(Long id);

	StudentBatchModel updateRecord(Long id ,StudentBatchModel record);
    
    void deleteRecordById(Long id);

}
