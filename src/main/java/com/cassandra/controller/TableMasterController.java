package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.entities.TableMaster;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TableMasterController extends BaseController {

    @PostMapping(value = "/add-table/", produces = "application/json", consumes = "application/json")
    public TableMaster addTableMaster(@RequestBody TableMaster tableMaster) throws Exception {
        return tableMasterRepository.save(tableMaster);
    }

    @GetMapping(value = "/get-table/{id}", produces = "application/json")
    public TableMaster getTableMasterById(@PathVariable("id") Long id) throws Exception {
        Optional<TableMaster> tableMasterOptional = tableMasterRepository.getTableMasterById(id);
        return tableMasterOptional != null && !tableMasterOptional.isEmpty() ? tableMasterOptional.get() : null
                ;
    }

    @GetMapping(value = "/get-all-table/", produces = "application/json")
    public List<TableMaster> getAllTableMaster() throws Exception {
        Optional<List<TableMaster>> tableMasterOptionalList = tableMasterRepository.findAllBy();
        return tableMasterOptionalList != null && !tableMasterOptionalList.isEmpty() ? tableMasterOptionalList.get() : null;
    }

    @DeleteMapping(value = "/delete-table/{id}", produces = "application/json")
    public BaseBean deleteTableMaster(@PathVariable("id") Long id) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<TableMaster> tableMasterOptional = tableMasterRepository.getTableMasterById(id);
        if (tableMasterOptional != null && !tableMasterOptional.isEmpty()) {
            visitRepository.deleteAllByTableMaster(tableMasterOptional.get());
            tableMasterRepository.delete(tableMasterOptional.get());
            baseBean.setStatus("success");
        } else {
            baseBean.setStatus("error");
            List<String> errorList = new ArrayList<>();
            errorList.add("Don't found table for given id.");
            baseBean.setErrorList(errorList);
        }
        return baseBean;
    }

}
