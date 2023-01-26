package com.learning.mongo.collections;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "student_batch")
public class StudentBatchCollection {

	@Id
	private Long id;
	private Double fees;
	private Long studentId;
	private Long batchId;

}
