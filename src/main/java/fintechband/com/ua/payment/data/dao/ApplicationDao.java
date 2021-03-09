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

    List<Application> findByStatus(Long status);

    Application save(Application application);

    void deleteById(Long id);
}
