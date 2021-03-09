package fintechband.com.ua.payment.data.dao.impl;

import fintechband.com.ua.payment.data.dao.RouteDao;
import fintechband.com.ua.payment.data.entity.Route;
import fintechband.com.ua.payment.data.repository.RouteRepository;
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
public class RouteDaoImpl implements RouteDao {
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public List<Route> findAll() {
        return (ArrayList<Route>) routeRepository.findAll();
    }

    @SneakyThrows
    @Override
    public Route findById(Long id) {
        return routeRepository.findById(id).orElseThrow(()
                -> new NotFoundException(String.format("Route with current id '%s' not found", id)));
    }

    @Override
    public Route save(Route status) {
        return routeRepository.save(status);
    }

    @Override
    public void deleteById(Long id) {
        routeRepository.deleteById(id);
    }
}
