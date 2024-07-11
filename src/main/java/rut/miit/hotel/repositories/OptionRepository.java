package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.entities.Option;


@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {

}
