package sk.stuba.fei.uim.oop.assignment3.notAnOrder;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotAnOrderRepository extends CrudRepository<NotAnOrder, Long> {
    List<NotAnOrder> findAll();
}
