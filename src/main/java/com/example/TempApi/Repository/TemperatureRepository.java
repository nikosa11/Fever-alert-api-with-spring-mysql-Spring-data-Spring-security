package com.example.TempApi.Repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.TempApi.Model.Temperature;


@Repository
public interface TemperatureRepository extends CrudRepository<Temperature, Long> {
	ArrayList<Temperature> findByUserId(Long userId);

}
