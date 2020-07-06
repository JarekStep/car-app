package pl.jarczi.carapp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jarczi.carapp.dao.entity.Car;


@Repository
public interface CarRepo extends CrudRepository<Car, Long> {

}
