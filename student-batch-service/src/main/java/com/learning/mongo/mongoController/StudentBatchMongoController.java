package com.learning.mongo.mongoController;

import com.learning.models.StudentBatchModel;
import com.learning.mongo.mongoservice.StudentBatchMongoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/student-batch-mongo")
@RequiredArgsConstructor
public class StudentBatchMongoController {
	
	private final StudentBatchMongoServiceImpl studentBatchService;

	@GetMapping("/{id}")
	public StudentBatchModel getRecordById(@PathVariable Long id) {
		return studentBatchService.getRecordById(id);
	}

	@GetMapping
	public List<StudentBatchModel> getAllRecords(@RequestParam(value = "count" ,required = false , defaultValue = "0") int count, @RequestParam(value = "sortBy", required = false,defaultValue = "") String sortBy) {
		if (count == 0 && (Objects.isNull(sortBy) || sortBy.isBlank())) {
			return studentBatchService.getAllRecords();
		} else if (count > 0) {
			return studentBatchService.getLimitedRecords(count);
		} else {
			return studentBatchService.getSortedRecords(sortBy);
		}
	}

	@PostMapping
	public List<StudentBatchModel> saveRecord(@RequestBody List<StudentBatchModel> studentBatchModelList) {
		try {
			if (studentBatchModelList.size() == 1) {
				return Arrays.asList(studentBatchService.saveRecord(studentBatchModelList.get(0)));
			} else {
				return studentBatchService.saveAll(studentBatchModelList);
			}
		} catch (Exception exception) {
			System.out.println("Exception Occurs in StudentBatchMongoController || saveAll");
			System.err.print(exception);
			return Collections.emptyList();
		}
	}

	@PutMapping("/{id}")
	public StudentBatchModel updateRecord(@PathVariable Long id,@RequestBody StudentBatchModel record) {
		return studentBatchService.updateRecord(id, record);
	}

	@DeleteMapping("/{id}")
	public void deleteRecordById(@PathVariable Long id) {
		studentBatchService.deleteRecordById(id);
	}

}
