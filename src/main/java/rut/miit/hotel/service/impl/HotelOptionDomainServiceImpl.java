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
        List<HotelOption> hotelOptionsCustomer = hotelOptionRepository.findByCustomer(customerId);

        Map<Option, Long> optionFrequency = hotelOptionsCustomer.stream()
                .collect(Collectors.groupingBy(HotelOption::getOption, Collectors.counting()));

        List<HotelOption> filteredHotelOptions = hotelOptionRepository.findByHotelIdAndOptions(hotelId, optionFrequency.keySet());

        filteredHotelOptions.sort((ho1, ho2) -> optionFrequency.get(ho2.getOption()).compareTo(optionFrequency.get(ho1.getOption())));

        return filteredHotelOptions.stream()
                .map(e -> modelMapper.map(e, HotelOptionResponseDto.class)).toList();
    }

    @Override
    public List<HotelOptionResponseDto> findAll(Integer id) {
        return hotelOptionRepository.findByHotelId(id).stream().map(e -> modelMapper.map(e, HotelOptionResponseDto.class)).toList();
    }

    @Override
    public List<HotelOptionResponseDto> findByCustomer(Integer id) {
        return hotelOptionRepository.findByCustomer(id).stream().map(e -> modelMapper.map(e, HotelOptionResponseDto.class)).toList();
    }
}
