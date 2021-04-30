package com.cassandra.controller;

import com.cassandra.entities.*;
import com.cassandra.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {
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

    @PostMapping("/add-user/")
    public User addUser(User user) throws Exception{
        Optional<Role> role=roleRepository.getRoleById(user.getRole()!=null?user.getRole().getId():0l);
        if(role!=null && !role.isEmpty()){
            user.setRole(role.get());
        }else{
            user.setRole(null);
        }
       return userRepository.save(user);
    }

    @PostMapping("/add-employee/")
    public Employee addEmployee(Employee employee) throws Exception{
        Optional<Restaurant> restaurant=restaurantRepository.getRestaurantById(employee.getRestaurant()!=null?employee.getRestaurant().getId():0l);
        if(restaurant!=null && !restaurant.isEmpty()){
            employee.setRestaurant(restaurant.get());
        }else{
            employee.setRestaurant(null);
        }
        Optional<Role> role=roleRepository.getRoleById(employee.getRole()!=null?employee.getRole().getId():0l);
        if(role!=null && !role.isEmpty()){
            employee.setRole(role.get());
        }else{
            employee.setRole(null);
        }
        return employeeRepository.save(employee);
    }

    @PostMapping("/add-restaurant/")
    public Restaurant addRestaurant(Restaurant restaurant) throws Exception{
        return restaurantRepository.save(restaurant);
    }

    @PostMapping("/add-customer/")
    public Customer addUser(Customer customer) throws Exception{
        return customerRepository.save(customer);
    }

    @PostMapping("/delete-user/")
    public void deleteUser(Long id) throws Exception{
        userRepository.deleteById(id);
    }

    @PostMapping("/delete-employee/")
    public void deleteEmployee(Long id) throws Exception{
        employeeRepository.deleteById(id);
    }

    @PostMapping("/delete-restaurant/")
    public void deleteRestaurant(Long id) throws Exception{
       restaurantRepository.deleteById(id);
    }

    @PostMapping("/delete-customer/")
    public void deleteCustomer(Long id) throws Exception{
         customerRepository.deleteById(id);
    }

    @PostMapping("/add-tablemaster/")
    public TableMaster addTableMaster(TableMaster tableMaster) throws Exception{
        Optional<Restaurant> restaurant=restaurantRepository.getRestaurantById(tableMaster.getRestaurant()!=null?tableMaster.getRestaurant().getId():0l);
        if(restaurant!=null && !restaurant.isEmpty()){
            tableMaster.setRestaurant(restaurant.get());
        }else{
            tableMaster.setRestaurant(null);
        }
        return tableMasterRepository.save(tableMaster);
    }

    @PostMapping("/delete-tabelmaster/")
    public void deleteTableMaster(Long id) throws Exception{
        tableMasterRepository.deleteById(id);
    }

    @PostMapping("/add-tablestatus/")
    public TableStatus addTableStatus(TableStatus tableStatus) throws Exception{
        Optional<TableMaster> tableMaster=tableMasterRepository.getTableMasterById(tableStatus.getTableMaster()!=null?tableStatus.getTableMaster().getId():0l);
        if(tableMaster!=null && !tableMaster.isEmpty()){
            tableStatus.setTableMaster(tableMaster.get());
        }else{
            tableStatus.setTableMaster(null);
        }
        return tableStatusRepository.save(tableStatus);
    }

    @PostMapping("/delete-tabelstatus/")
    public void deleteTableStatus(Long id) throws Exception{
        tableStatusRepository.deleteById(id);
    }

    @PostMapping("/add-items/")
    public Items addItems(Items items) throws Exception{
        return itemRepository.save(items);
    }

    @PostMapping("/delete-items/")
    public void deleteItem(Long id) throws Exception{
        itemRepository.deleteById(id);
    }

    @PostMapping("/add-restaurantitems/")
    public RestaurantItems addRestaurantItems(RestaurantItems restaurantItems) throws Exception{
        Optional<Restaurant> restaurant=restaurantRepository.getRestaurantById(restaurantItems.getRestaurant()!=null?restaurantItems.getRestaurant().getId():0l);
        if(restaurant!=null && !restaurant.isEmpty()){
            restaurantItems.setRestaurant(restaurant.get());
        }else{
            restaurantItems.setRestaurant(null);
        }
        Optional<Items> items=itemRepository.getItemsById(restaurantItems.getItems()!=null?restaurantItems.getItems().getId():0l);
        if(items!=null && !items.isEmpty()){
            restaurantItems.setItems(items.get());
        }else{
            restaurantItems.setItems(null);
        }
        return restaurantItemsRepository.save(restaurantItems);
    }

    @PostMapping("/delete-restaurantitems/")
    public void deleteRestaurantItem(Long id) throws Exception{
        restaurantItemsRepository.deleteById(id);
    }

    @PostMapping("/add-visits/")
    public Visits addVisits(Visits visits) throws Exception{
        Optional<Restaurant> restaurant=restaurantRepository.getRestaurantById(visits.getRestaurant()!=null?visits.getRestaurant().getId():0l);
        if(restaurant!=null && !restaurant.isEmpty()){
            visits.setRestaurant(restaurant.get());
        }else{
            visits.setRestaurant(null);
        }
        Optional<Customer> customer=customerRepository.getCustomerById(visits.getCustomer()!=null?visits.getCustomer().getId():0l);
        if(customer!=null && !customer.isEmpty()){
            visits.setCustomer(customer.get());
        }else{
            visits.setCustomer(null);
        }
        Optional<TableMaster> tableMaster=tableMasterRepository.getTableMasterById(visits.getTableMaster()!=null?visits.getTableMaster().getId():0l);
        if(tableMaster!=null && !tableMaster.isEmpty()){
            visits.setTableMaster(tableMaster.get());
        }else{
            visits.setTableMaster(null);
        }
        return visitsRepository.save(visits);
    }

    @PostMapping("/delete-visits/")
    public void deleteVisits(Long id) throws Exception{
        visitsRepository.deleteById(id);
    }

    @PostMapping("/add-order/")
    public Orders addVisits(Orders orders) throws Exception{
        return ordersRepository.save(orders);
    }

    @PostMapping("/delete-order/")
    public void deleteOrder(Long id) throws Exception{
        ordersRepository.deleteById(id);
    }

    @PostMapping("/add-orderitem/")
    public OrderItems addOrderItem(OrderItems orderItems) throws Exception{
        Optional<Orders> orders=ordersRepository.getOrdersById(orderItems.getOrders()!=null?orderItems.getOrders().getId():0l);
        if(orders!=null && !orders.isEmpty()){
            orderItems.setOrders(orders.get());
        }else{
            orderItems.setOrders(null);
        }
        Optional<Items> items=itemRepository.getItemsById(orderItems.getItems()!=null?orderItems.getItems().getId():0l);
        if(items!=null && !items.isEmpty()){
            orderItems.setItems(items.get());
        }else{
            orderItems.setItems(null);
        }
        return orderItemsRepository.save(orderItems);
    }

    @PostMapping("/delete-orderitem/")
    public void deleteOrderItem(Long id) throws Exception{
        orderItemsRepository.deleteById(id);
    }

    @PostMapping("/add-visitorder/")
    public VisitOrder addVisitOrder(VisitOrder visitOrder) throws Exception{
        Optional<Orders> orders=ordersRepository.getOrdersById(visitOrder.getOrders()!=null?visitOrder.getOrders().getId():0l);
        if(orders!=null && !orders.isEmpty()){
            visitOrder.setOrders(orders.get());
        }else{
            visitOrder.setOrders(null);
        }
        Optional<Visits> visits=visitsRepository.getVisitsById(visitOrder.getVisits()!=null?visitOrder.getVisits().getId():0l);
        if(visits!=null && !visits.isEmpty()){
            visitOrder.setVisits(visits.get());
        }else{
            visitOrder.setVisits(null);
        }
        return visitOrderRepository.save(visitOrder);
    }

    @PostMapping("/delete-visitorder/")
    public void deleteVisitOrder(Long id) throws Exception{
        visitOrderRepository.deleteById(id);
    }

    @PostMapping("/add-role/")
    public Role addRole(Role role) throws Exception{
        return roleRepository.save(role);
    }

    @PostMapping("/delete-role/")
    public void deleteRole(Long id) throws Exception{
        roleRepository.deleteById(id);
    }
}
