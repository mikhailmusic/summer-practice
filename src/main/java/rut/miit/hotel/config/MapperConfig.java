package rut.miit.hotel.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rut.miit.hotel.domain.Booking;
import rut.miit.hotel.dto.response.BookingResponseDto;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Booking.class, BookingResponseDto.class).addMapping(Booking::getPayments, BookingResponseDto::setPayments);

        return modelMapper;
    }
}
