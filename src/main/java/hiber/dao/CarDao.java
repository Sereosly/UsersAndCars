package hiber.dao;

import hiber.model.Car;

import java.util.List;

public interface CarDao {
    void add(final Car car);
    List<Car> listCar();
}
