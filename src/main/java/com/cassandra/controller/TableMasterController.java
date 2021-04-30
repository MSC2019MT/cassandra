package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.entities.Restaurant;
import com.cassandra.entities.TableMaster;
import com.cassandra.repository.TableMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TableMasterController {

    @Autowired
    TableMasterRepository tableMasterRepository;

    @PostMapping("/add-table/")
    public TableMaster addTableMaster(TableMaster tableMaster) throws Exception {
        return tableMasterRepository.save(tableMaster);
    }

    @GetMapping("/get-table-by-id/")
    public TableMaster getTableMasterById(Long id) throws Exception {
        Optional<TableMaster> tableMasterOptional = tableMasterRepository.getTableMasterById(id);
        return tableMasterOptional != null && !tableMasterOptional.isEmpty() ? tableMasterOptional.get() : null
                ;
    }

    @GetMapping("/get-all-table/")
    public List<TableMaster> getAllTableMaster() throws Exception {
        Optional<List<TableMaster>> tableMasterOptionalList = tableMasterRepository.findAllBy();
        return tableMasterOptionalList != null && !tableMasterOptionalList.isEmpty() ? tableMasterOptionalList.get() : null;
    }

    @GetMapping("/get-all-table-by-restaurant-id/")
    public List<TableMaster> getAllTableMasterByRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        Optional<List<TableMaster>> tableMasterOptionalList = tableMasterRepository.getTableMastersByRestaurant(restaurant);
        return tableMasterOptionalList != null && !tableMasterOptionalList.isEmpty() ? tableMasterOptionalList.get() : null
                ;
    }

    @PostMapping("/delete-table/")
    public BaseBean deleteTableMaster(Long id) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<TableMaster> tableMasterOptional = tableMasterRepository.getTableMasterById(id);
        if (tableMasterOptional != null && !tableMasterOptional.isEmpty()) {
            tableMasterRepository.delete(tableMasterOptional.get());
            baseBean.setStatus("success");
        } else {
            baseBean.setStatus("error");
            List<String> errorList = new ArrayList<>();
            errorList.add("Don't found tableMaster for given id.");
            baseBean.setErrorList(errorList);
        }
        return baseBean;
    }
}
