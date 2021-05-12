package com.cassandra.repository;

import com.cassandra.beans.CustomerBean;
import com.cassandra.entities.Customer;
import com.cassandra.entities.Restaurant;
import com.cassandra.entities.TableMaster;
import com.cassandra.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface VisitRepository extends JpaRepository<Visit, Long> {

    public Optional<Visit> getVisitById(Long id);

    public Optional<List<Visit>> findAllBy();

    @Query("Select v.id from Visit v where v.customer.id=:customerId")
    public Optional<List<Long>> getIdListByCustomerId(Long customerId);

    public void deleteAllByCustomer(Customer customer);

    public void deleteAllByTableMaster(TableMaster tableMaster);

}
