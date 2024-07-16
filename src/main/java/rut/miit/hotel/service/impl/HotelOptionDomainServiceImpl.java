package rut.miit.hotel.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rut.miit.hotel.domain.HotelOption;
import rut.miit.hotel.domain.Option;
import rut.miit.hotel.dto.response.HotelOptionResponseDto;
import rut.miit.hotel.service.HotelOptionDomainService;
import rut.miit.hotel.repositories.HotelOptionRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HotelOptionDomainServiceImpl implements HotelOptionDomainService {
    private final HotelOptionRepository hotelOptionRepository;
    private final ModelMapper modelMapper;

    public HotelOptionDomainServiceImpl(HotelOptionRepository hotelOptionRepository, ModelMapper modelMapper) {
        this.hotelOptionRepository = hotelOptionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<HotelOptionResponseDto> recommendOptions(Integer customerId, Integer hotelId){

        List<HotelOption> hotelOptionsCustomer = hotelOptionRepository.findOptionsByCustomer(customerId);
        List<HotelOption> hotelOptions = hotelOptionRepository.findHotelOptionsByHotelId(hotelId);

        // Calculate the frequency of each 'Option' used by the client
        Map<Option, Long> optionFrequency = hotelOptionsCustomer.stream()
                .collect(Collectors.groupingBy(HotelOption::getOption, Collectors.counting()));

        // Filter 'Options' based on the most frequently used by the client
        List<Option> frequentOptions = optionFrequency.entrySet().stream()
                .sorted(Map.Entry.<Option, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();

        return hotelOptions.stream()
                .filter(ho -> frequentOptions.contains(ho.getOption()))
                .map(e -> modelMapper.map(e, HotelOptionResponseDto.class)).toList();

    }


    @Override
    public List<HotelOptionResponseDto> findAll(Integer id) {
        return hotelOptionRepository.findHotelOptionsByHotelId(id).stream().map(e -> modelMapper.map(e, HotelOptionResponseDto.class)).toList();
    }

    @Override
    public List<HotelOptionResponseDto> findByCustomer(Integer id) {
        return hotelOptionRepository.findOptionsByCustomer(id).stream().map(e -> modelMapper.map(e, HotelOptionResponseDto.class)).toList();
    }
}
