package cn.wycfight.study.caching.ttl.repository;

import cn.wycfight.study.caching.ttl.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {}