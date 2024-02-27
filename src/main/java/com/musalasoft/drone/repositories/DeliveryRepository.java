package com.musalasoft.drone.repositories;
import com.musalasoft.drone.models.DeliveryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryDetails, Long> {
}
