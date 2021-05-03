package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.entities.Visits;
import com.cassandra.repository.VisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class VisitsController {

    @Autowired
    VisitsRepository visitsRepository;

    @PostMapping("/add-visit/")
    public Visits addVisits(Visits visits) throws Exception {
        if (visits.getId() != null) {
            Optional<Visits> visitsOptional = visitsRepository.getVisitsById(visits.getId());
            if (visitsOptional != null && !visitsOptional.isEmpty()) {
                visits.setFromDateTime(visitsOptional.get().getFromDateTime());
            }
        }
        if (visits.getComeOrLeave() != null && visits.getComeOrLeave().equals("come")) {
            visits.setFromDateTime(new Timestamp(new Date().getTime()));
        } else {
            visits.setToDateTime(new Timestamp(new Date().getTime()));
        }
        return visitsRepository.save(visits);
    }

    @GetMapping("/get-visit-by-id/")
    public Visits getVisitsById(Long id) throws Exception {
        Optional<Visits> visitsOptional = visitsRepository.getVisitsById(id);
        return visitsOptional != null && !visitsOptional.isEmpty() ? visitsOptional.get() : null
                ;
    }

    @GetMapping("/get-all-visits/")
    public List<Visits> getAllVisits() throws Exception {
        Optional<List<Visits>> visitsOptionalList = visitsRepository.findAllBy();
        return visitsOptionalList != null && !visitsOptionalList.isEmpty() ? visitsOptionalList.get() : null
                ;
    }

    @PostMapping("/delete-visits/")
    public BaseBean deleteVisits(Long id) throws Exception {
        BaseBean baseBean = new BaseBean();
        Optional<Visits> visitsOptional = visitsRepository.getVisitsById(id);
        if (visitsOptional != null && !visitsOptional.isEmpty()) {
            visitsRepository.delete(visitsOptional.get());
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
