package com.customers.backend.infra.respositories;

import com.customers.backend.domain.contracts.repositories.CustomersRepository;
import com.customers.backend.domain.entities.Customer;
import com.customers.backend.domain.entities.response.CustomersGetAllResponse;
import com.customers.backend.infra.contracts.services.MetadataService;
import com.customers.backend.infra.contracts.services.PaginateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class CustomersRepositoryImpl implements CustomersRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    PaginateService paginateService;

    @Autowired
    MetadataService metadataService;

    List<Customer> customers;

    public CustomersRepositoryImpl() {
        customers = new ArrayList<Customer>();
    }

    @Override
    public CustomersGetAllResponse get(Integer start, Integer limit) {

        List<Customer> customers = null;
        customers = buildList(jdbcTemplate.queryForList("select * from customers.customer"));

        List<Customer> customersFiltered = paginateService.getPage(customers, start, limit);

        int customers_size = 0;
        if (customers != null)
            customers_size = customers.size();

        return CustomersGetAllResponse.builder()
                .metadata(metadataService.fromList(start, limit, customers_size, customersFiltered))
                .results(customersFiltered).build();

    }

    private List<Customer> buildList(List<Map<String, Object>> listResult) {

        if (listResult == null || listResult != null && listResult.size() <= 0)
            return null;

        List<Customer> list = new ArrayList<Customer>();

        for (Map<String, Object> rs : listResult) {
            Customer item = buildItem(rs);

            if (item != null)
                list.add(item);
        }

        return list;
    }

    private Customer buildItem(Map<String, Object> rs) {
        if (rs.isEmpty())
            return null;

        return Customer.builder()
                .id((Integer) rs.get("id"))
                .sex((String) rs.get("sex"))
                .name((String) rs.get("name"))
                .cel_ddd((String) rs.get("cel_ddd"))
                .cel_ddi((String) rs.get("cel_ddi"))
                .cel_number((String) rs.get("cel_number"))
                .date_birth((Date) rs.get("date_birth"))
                .email((String) rs.get("email"))
                .register_date((Date) rs.get("register_date"))
                .last_update_date((Date) rs.get("last_update_date"))
                .build();

    }
}