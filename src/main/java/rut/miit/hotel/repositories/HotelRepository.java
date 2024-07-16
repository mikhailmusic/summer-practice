package rut.miit.hotel.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rut.miit.hotel.domain.Hotel;

import java.util.List;


@Repository
public interface HotelRepository extends GeneralRepository<Hotel, Integer> {

    @Query("SELECT h FROM Hotel h WHERE " +
            "(:country IS NULL OR h.country LIKE :country) AND " +
            "(:city IS NULL OR h.city LIKE :city) AND " +
            "(:rating IS NULL OR h.rating = :rating)")
    List<Hotel> findByCountryAndCityAndRating(@Param("country") String country,
                                              @Param("city") String city,
                                              @Param("rating") Integer rating);
}
