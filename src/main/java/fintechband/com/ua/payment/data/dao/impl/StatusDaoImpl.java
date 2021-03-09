package fintechband.com.ua.payment.data.dao.impl;

import fintechband.com.ua.payment.data.dao.StatusDao;
import fintechband.com.ua.payment.data.entity.Status;
import fintechband.com.ua.payment.data.repository.StatusRepository;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nhaitanov
 */
@Component
public class StatusDaoImpl implements StatusDao {
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> findAll() {
        return (ArrayList<Status>) statusRepository.findAll();
    }

    @SneakyThrows
    @Override
    public Status findById(Long id) {
        return statusRepository.findById(id).orElseThrow(()
                -> new NotFoundException(String.format("Status with current id '%s' not found", id)));
    }

    @Override
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public void deleteById(Long id) {
        statusRepository.deleteById(id);
    }
}
