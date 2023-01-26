package com.learning.service;

import com.learning.model.StudentModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
	
	List<StudentModel> getAllRecords();
	
	List<StudentModel> getLimitedRecords(int count);
	
	List<StudentModel> getSortedRecords(String sortBy);
	
	StudentModel saveRecord(StudentModel record);
	
	List<StudentModel> saveAll(List<StudentModel> recordList);

	StudentModel getRecordById(Long id);

	StudentModel updateRecord(Long id ,StudentModel record);
    
    void deleteRecordById(Long id);

	void saveExcelFile(MultipartFile file);

}
