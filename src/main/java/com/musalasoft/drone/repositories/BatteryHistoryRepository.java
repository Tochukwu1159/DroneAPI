package com.musalasoft.drone.repositories;

import com.musalasoft.drone.models.BatteryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatteryHistoryRepository extends JpaRepository<BatteryHistory, Long> {
    List<BatteryHistory> findAllByDrone_SerialNumber(String serialNumber);

}
