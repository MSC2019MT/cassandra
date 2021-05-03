package com.cassandra.controller;

import com.cassandra.beans.BaseBean;
import com.cassandra.beans.RestaurantItemsBean;
import com.cassandra.beans.RestaurantTableOrderBean;
import com.cassandra.entities.OrderItems;
import com.cassandra.entities.Restaurant;
import com.cassandra.entities.TableMaster;
import com.cassandra.entities.Visits;
import com.cassandra.repository.OrderItemsRepository;
import com.cassandra.repository.TableMasterRepository;
import com.cassandra.repository.VisitsRepository;
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

    @Autowired
    VisitsRepository visitsRepository;

    @Autowired
    OrderItemsRepository orderItemsRepository;

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

    @GetMapping("/get-orders-by-table-id-and-restaurant-id/")
    public RestaurantTableOrderBean getOrdersByTableId(Long tableId, Long restaurantId) throws Exception {
        RestaurantTableOrderBean restaurantTableOrderBean = new RestaurantTableOrderBean();
        Optional<TableMaster> optionalTableMaster = tableMasterRepository.getTableMasterById(tableId);
        if (optionalTableMaster != null && !optionalTableMaster.isEmpty()) {
            if (optionalTableMaster.get().getStatus() != null && optionalTableMaster.get().getStatus().equalsIgnoreCase("occupied")) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(restaurantId);
                Optional<Visits> visits = visitsRepository.findTopByRestaurantAndTableMasterOrderByFromDateTimeDesc(restaurant, optionalTableMaster.get());
                Optional<List<RestaurantItemsBean>> optionalRestaurantItemsBeans = orderItemsRepository.getOrderItemsByVisitId(visits.get().getId());
                Float total = 0f;
                if (optionalRestaurantItemsBeans != null && !optionalRestaurantItemsBeans.isEmpty() && optionalRestaurantItemsBeans.get() != null && !optionalRestaurantItemsBeans.get().isEmpty()) {
                    for (RestaurantItemsBean restaurantItemsBean : optionalRestaurantItemsBeans.get()) {
                        restaurantItemsBean.setItemWiseTotal(restaurantItemsBean.getPriceNet() * restaurantItemsBean.getQuantities());
                        total = total + restaurantItemsBean.getItemWiseTotal();
                    }
                    restaurantTableOrderBean.setRestaurantItemsBeanList(optionalRestaurantItemsBeans.get());
                    restaurantTableOrderBean.setTotal(total);
                }
                restaurantTableOrderBean.setTableMaster(optionalTableMaster.get());
                restaurantTableOrderBean.setCustomer(visits.get().getCustomer());
                return restaurantTableOrderBean;
            }
        }
        return null;
    }
}
