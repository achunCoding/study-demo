package cn.wycfight.study.caching.ttl.service;

import cn.wycfight.study.caching.ttl.model.Hotel;
import cn.wycfight.study.caching.ttl.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 使用构造器注入
 */
@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    Logger logger = LoggerFactory.getLogger(HotelService.class);

    HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Cacheable("hotels")
    public List<Hotel> getAllHotels() {
        return hotelRepository.getAllHotels();
    }

    @CacheEvict(value = "hotels", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.hotelListTTL:100}")
    public void emptyHotelsCache() {
        logger.info("emptying Hotels cache");
    }

}