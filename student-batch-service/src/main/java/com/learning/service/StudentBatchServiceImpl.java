package com.learning.service;

import com.learning.constants.NumberConstant;
import com.learning.entity.StudentBatchEntity;
import com.learning.enums.ErrorMessages;
import com.learning.exceptions.DataNotFoundException;
import com.learning.models.StudentBatchModel;
import com.learning.repository.StudentBatchRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentBatchServiceImpl implements StudentBatchService {

    private final StudentBatchRepository studentBatchRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentBatchModel> getAllRecords() {
        List<StudentBatchEntity> studentBatchEntityList = studentBatchRepo.findAll();
        if (Objects.nonNull(studentBatchEntityList) && studentBatchEntityList.size() > NumberConstant.ZERO) {
            return studentBatchEntityList.stream().map(studentBatchEntity -> {
                StudentBatchModel studentBatchModel = modelMapper.map(studentBatchEntity, StudentBatchModel.class);
                return studentBatchModel;
            }).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<StudentBatchModel> getLimitedRecords(int count) {
        List<StudentBatchEntity> studentBatchEntityList = studentBatchRepo.findAll();
        if (Objects.nonNull(studentBatchEntityList) && studentBatchEntityList.size() > NumberConstant.ZERO) {
            return studentBatchEntityList.stream().limit(count)
                    .map(studentBatchEntity -> {
                        StudentBatchModel studentBatchModel = modelMapper.map(studentBatchEntity, StudentBatchModel.class);
                        return studentBatchModel;
                    }).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<StudentBatchModel> getSortedRecords(String sortBy) {
        List<StudentBatchEntity> studentBatchEntityList = studentBatchRepo.findAll();
        if (Objects.nonNull(studentBatchEntityList) && studentBatchEntityList.size() > NumberConstant.ZERO) {
            Comparator<StudentBatchEntity> comparator = findSuitableComparator(sortBy);
            return studentBatchEntityList.stream().sorted(comparator)
                    .map(studentBatchEntity -> {
                        StudentBatchModel studentBatchModel = modelMapper.map(studentBatchEntity, StudentBatchModel.class);
                        return studentBatchModel;
                    }).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public StudentBatchModel saveRecord(StudentBatchModel studentBatchModel) {
        if (Objects.nonNull(studentBatchModel)) {
            studentBatchRepo.save(modelMapper.map(studentBatchModel, StudentBatchEntity.class));
        }
        return studentBatchModel;
    }

    @Override
    public List<StudentBatchModel> saveAll(List<StudentBatchModel> studentBatchModelList) {
        if (Objects.nonNull(studentBatchModelList) && studentBatchModelList.size() > NumberConstant.ZERO) {
            List<StudentBatchEntity> studentBatchEntityList = studentBatchModelList.stream().map(studentBatchModel -> {
                StudentBatchEntity entity = modelMapper.map(studentBatchModel, StudentBatchEntity.class);
                return entity;
            }).collect(Collectors.toList());
            studentBatchRepo.saveAll(studentBatchEntityList);
        }
        return studentBatchModelList;
    }

    @Override
    public StudentBatchModel getRecordById(Long id) {
        StudentBatchEntity studentBatchEntity = studentBatchRepo.findById(id)
				.orElseThrow(() -> new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
        StudentBatchModel studentBatchModel = modelMapper.map(studentBatchEntity, StudentBatchModel.class);
        return studentBatchModel;
    }

    @Override
    public StudentBatchModel updateRecord(Long id, StudentBatchModel record) {
        StudentBatchEntity studentBatchEntity = studentBatchRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
            modelMapper.map(record, studentBatchEntity);
            studentBatchRepo.save(studentBatchEntity);
            return record;
        }

    @Override
    public void deleteRecordById(Long id) {
        studentBatchRepo.deleteById(id);
    }

    private Comparator<StudentBatchEntity> findSuitableComparator(String sortBy) {
        Comparator<StudentBatchEntity> comparator;
        switch (sortBy) {
            case "fees": {
                comparator = (studentBatchOne, studentBatchTwo) -> studentBatchOne.getFees()
                        .compareTo(studentBatchTwo.getFees());
                break;
            }
            case "batchId": {
                comparator = (studentBatchOne, studentBatchTwo) -> studentBatchOne.getBatchId()
                        .compareTo(studentBatchTwo.getBatchId());
                break;
            }
            case "studentId": {
                comparator = (studentBatchOne, studentBatchTwo) -> studentBatchOne.getStudentId()
                        .compareTo(studentBatchTwo.getStudentId());
                break;
            }
            default: {
                comparator = (studentBatchOne, studentBatchTwo) -> studentBatchOne.getId()
                        .compareTo(studentBatchTwo.getId());
            }
        }
        return comparator;
    }

}
