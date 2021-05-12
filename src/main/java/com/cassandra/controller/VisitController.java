package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.entities.Visit;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class VisitController extends BaseController {

    @PostMapping(value = "/add-visit/", produces = "application/json", consumes = "application/json")
    public Visit addVisit(@RequestBody Visit visits) throws Exception {
        if (visits.getId() != null) {
            Optional<Visit> visitsOptional = visitRepository.getVisitById(visits.getId());
            if (visitsOptional != null && !visitsOptional.isEmpty()) {
                visits.setFromDateTime(visitsOptional.get().getFromDateTime());
            }
        }
        if (visits.getComeOrLeave() != null && visits.getComeOrLeave().equals("come")) {
            visits.setFromDateTime(new Timestamp(new Date().getTime()));
        } else if (visits.getComeOrLeave() != null && visits.getComeOrLeave().equals("leave")) {
            visits.setToDateTime(new Timestamp(new Date().getTime()));
        }
        return visitRepository.save(visits);
    }

    @GetMapping(value = "/get-visit/{id}", produces = "application/json")
    public Visit getVisitById(@PathVariable("id") Long id) throws Exception {
        Optional<Visit> visitsOptional = visitRepository.getVisitById(id);
        return visitsOptional != null && !visitsOptional.isEmpty() ? visitsOptional.get() : null
                ;
    }

    @GetMapping(value = "/get-all-visit/", produces = "application/json")
    public List<Visit> getAllVisit() throws Exception {
        Optional<List<Visit>> visitsOptionalList = visitRepository.findAllBy();
        return visitsOptionalList != null && !visitsOptionalList.isEmpty() ? visitsOptionalList.get() : null
                ;
    }

    @DeleteMapping(value = "/delete-visit/{id}", produces = "application/json")
    public BaseBean deleteVisit(@PathVariable("id") Long id) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<Visit> visitsOptional = visitRepository.getVisitById(id);
        if (visitsOptional != null && !visitsOptional.isEmpty()) {
            orderRepository.deleteAllByVisit(visitsOptional.get());
            visitRepository.delete(visitsOptional.get());
            baseBean.setStatus("success");
        } else {
            baseBean.setStatus("error");
            List<String> errorList = new ArrayList<>();
            errorList.add("Don't found visits for given id.");
            baseBean.setErrorList(errorList);
        }
        return baseBean;
    }
}
