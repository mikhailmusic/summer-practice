package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.entities.Service;


@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
