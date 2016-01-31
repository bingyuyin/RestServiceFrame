package com.suiyu.data.repository;

import com.suiyu.data.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by BingyuYin on 2016/1/30.
 */
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    List<Tenant> findByName(String name);
}
