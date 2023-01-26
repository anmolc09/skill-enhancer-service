package com.learning.mongo.mongoservice;

import com.learning.models.CourseModel;

import java.util.List;

public interface CourseMongoService {
	
	List<CourseModel> getAllRecords();
	
	List<CourseModel> getLimitedRecords(int count);
	
	List<CourseModel> getSortedRecords(String sortBy);
	
	CourseModel saveRecord(CourseModel record);
	
	List<CourseModel> saveAll(List<CourseModel> recordList);

	CourseModel getRecordById(Long id);

	CourseModel updateRecord(Long id ,CourseModel record);
    
    void deleteRecordById(Long id);

}
