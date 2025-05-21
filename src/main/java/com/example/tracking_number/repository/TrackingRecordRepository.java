package com.example.tracking_number.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tracking_number.model.TrackingNumberRecord;

@Repository
public interface TrackingRecordRepository extends JpaRepository<TrackingNumberRecord, Integer> {

}
