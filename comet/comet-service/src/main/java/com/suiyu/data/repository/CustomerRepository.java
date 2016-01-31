package com.suiyu.data.repository;

import com.suiyu.data.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by BingyuYin on 2016/1/30.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByUuid(String uuid);
    List<Customer> findByNameAndTenantId(String name, String tenant_id);
    List<Customer> findByIsAdmin();
}
