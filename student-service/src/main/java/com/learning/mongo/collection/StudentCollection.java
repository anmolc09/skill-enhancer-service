package com.learning.mongo.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "student")
public class StudentCollection {

	@Id
	private Long id;
	private String name;
	private Long contactDetails;
	private String qualification;
	private String email;

}
