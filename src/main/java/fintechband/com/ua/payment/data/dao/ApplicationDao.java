package fintechband.com.ua.payment.data.dao;

import fintechband.com.ua.payment.data.entity.Application;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nhaitanov
 */
@Repository
public interface ApplicationDao {

    List<Application> findAll();

    Application findById(Long id);

    List<Application> findAllByStatus(Long status);

    Application findByStatus(Long status);

    Long save(Application application);

    void deleteById(Long id);
}
