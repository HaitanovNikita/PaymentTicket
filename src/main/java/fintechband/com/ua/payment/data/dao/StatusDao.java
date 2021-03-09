package fintechband.com.ua.payment.data.dao;

import fintechband.com.ua.payment.data.entity.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nhaitanov
 */
@Repository
public interface StatusDao {
    List<Status> findAll();

    Status findById(Long id);

    Status save(Status status);

    void deleteById(Long id);
}
