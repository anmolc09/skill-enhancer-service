package com.learning.service;

import com.learning.constants.NumberConstant;
import com.learning.entity.TimeSlotEntity;
import com.learning.enums.ErrorMessages;
import com.learning.exceptions.DataNotFoundException;
import com.learning.models.TimeSlotModel;
import com.learning.repository.TimeSlotRepository;
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
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<TimeSlotModel> getAllRecords() {
        List<TimeSlotEntity> timeSlotEntityList = timeSlotRepo.findAll();
        if (Objects.nonNull(timeSlotEntityList) && timeSlotEntityList.size() > NumberConstant.ZERO) {
            return timeSlotEntityList.stream().map(timeSlotEntity -> {
                TimeSlotModel timeSlotModel = modelMapper.map(timeSlotEntity, TimeSlotModel.class);
                return timeSlotModel;
            }).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<TimeSlotModel> getLimitedRecords(int count) {
        List<TimeSlotEntity> timeSlotEntityList = timeSlotRepo.findAll();
        if (Objects.nonNull(timeSlotEntityList) && timeSlotEntityList.size() > NumberConstant.ZERO) {
            return timeSlotEntityList.stream().limit(count)
                    .map(timeSlotEntity -> {
                        TimeSlotModel timeSlotModel = modelMapper.map(timeSlotEntity, TimeSlotModel.class);
                        return timeSlotModel;
                    }).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<TimeSlotModel> getSortedRecords(String sortBy) {
        List<TimeSlotEntity> timeSlotEntityList = timeSlotRepo.findAll();
        if (Objects.nonNull(timeSlotEntityList) && timeSlotEntityList.size() > NumberConstant.ZERO) {
            Comparator<TimeSlotEntity> comparator = findSuitableComparator(sortBy);
            return timeSlotEntityList.stream().sorted(comparator)
                    .map(timeSlotEntity -> {
                        TimeSlotModel timeSlotModel = modelMapper.map(timeSlotEntity, TimeSlotModel.class);
                        return timeSlotModel;
                    }).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public TimeSlotModel saveRecord(TimeSlotModel timeSlotModel) {
        if (Objects.nonNull(timeSlotModel)) {
            timeSlotRepo.save(modelMapper.map(timeSlotModel, TimeSlotEntity.class));
        }
        return timeSlotModel;
    }

    @Override
    public List<TimeSlotModel> saveAll(List<TimeSlotModel> timeSlotModelList) {
        if (Objects.nonNull(timeSlotModelList) && timeSlotModelList.size() > NumberConstant.ZERO) {
            List<TimeSlotEntity> timeSlotEntityList = timeSlotModelList.stream().map(timeSlotModel -> {
                TimeSlotEntity entity = modelMapper.map(timeSlotModel, TimeSlotEntity.class);
                return entity;
            }).collect(Collectors.toList());
            timeSlotRepo.saveAll(timeSlotEntityList);
        }
        return timeSlotModelList;
    }

    @Override
    public TimeSlotModel getRecordById(Long id) {
        TimeSlotEntity timeSlotEntity = timeSlotRepo.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
        TimeSlotModel timeSlotModel = modelMapper.map(timeSlotEntity, TimeSlotModel.class);
        return timeSlotModel;
    }

    @Override
    public TimeSlotModel updateRecord(Long id, TimeSlotModel record) {
        TimeSlotEntity timeSlotEntity = timeSlotRepo.findById(id).orElseThrow(() -> new DataNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
        modelMapper.map(record, timeSlotEntity);
        timeSlotRepo.save(timeSlotEntity);
        return record;
    }

    @Override
    public void deleteRecordById(Long id) {
        timeSlotRepo.deleteById(id);
    }

    private Comparator<TimeSlotEntity> findSuitableComparator(String sortBy) {
        Comparator<TimeSlotEntity> comparator;
        switch (sortBy) {
            case "startTime": {
                comparator = (timeSlotOne, timeSlotTwo) -> timeSlotOne.getStartTime()
                        .compareTo(timeSlotTwo.getStartTime());
                break;
            }
            case "endTime": {
                comparator = (timeSlotOne, timeSlotTwo) -> timeSlotOne.getEndTime()
                        .compareTo(timeSlotTwo.getEndTime());
                break;
            }
            case "trainerId": {
                comparator = (timeSlotOne, timeSlotTwo) -> timeSlotOne.getTrainerId()
                        .compareTo(timeSlotTwo.getTrainerId());
                break;
            }
            default: {
                comparator = (timeSlotOne, timeSlotTwo) -> timeSlotOne.getId()
                        .compareTo(timeSlotTwo.getId());
            }
        }
        return comparator;
    }

}
