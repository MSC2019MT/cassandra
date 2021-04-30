package com.cassandra.service;

import com.cassandra.beans.RestaurantTableBean;
import com.cassandra.entities.Restaurant;
import com.cassandra.entities.TableMaster;
import com.cassandra.entities.TableStatus;
import com.cassandra.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TableMasterRepository tableMasterRepository;

    @Autowired
    TableStatusRepository tableStatusRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    RestaurantItemsRepository restaurantItemsRepository;

    @Autowired
    VisitsRepository visitsRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    VisitOrderRepository visitOrderRepository;

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<RestaurantTableBean> getRestaurantTableBeanByRestaurant(Restaurant restaurant) throws Exception {
        List<RestaurantTableBean> restaurantTableBeanList = new ArrayList<>();
        if (restaurant != null) {
            {
                Optional<List<TableMaster>> optionalTableMasterList = tableMasterRepository.getTableMastersByRestaurant(restaurant);
                if (optionalTableMasterList != null && !optionalTableMasterList.isEmpty() && optionalTableMasterList.get() != null && !optionalTableMasterList.get().isEmpty()) {
                    List<TableMaster> tableMasterList = optionalTableMasterList.get();
                    for (TableMaster tableMaster : tableMasterList) {
                        RestaurantTableBean restaurantTableBean = new RestaurantTableBean();
                        Optional<TableStatus> optionalTableStatus = tableStatusRepository.getTableStatusByTableMaster(tableMaster);
                        if (optionalTableStatus != null && !optionalTableStatus.isEmpty()) {
                            restaurantTableBean.setStatus(optionalTableStatus.get().getStatus());
                        } else {
                            restaurantTableBean.setStatus("Free");
                        }
                        restaurantTableBean.setTableId(tableMaster.getId());
                        restaurantTableBean.setTableName(tableMaster.getName());
                        restaurantTableBeanList.add(restaurantTableBean);
                    }
                }
            }
        }
        return restaurantTableBeanList;
    }

}
