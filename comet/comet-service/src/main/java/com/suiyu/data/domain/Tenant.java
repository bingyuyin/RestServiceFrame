package com.suiyu.data.domain;

import com.suiyu.comet.common.TenantDetails;
import com.suiyu.comet.common.util.ThrowUtil;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by BingyuYin on 2016/1/30.
 */
@Entity
@Table(name = "TENANT")
@Cache(region = "lmcm", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tenant extends AbstractPersistable<Long> {
    private static final long serialVersionUID = -6659880808636829629L;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Tenant(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ThrowUtil.verifyArgumentNotNull(name);
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if(obj.getClass() != Tenant.class) {
            return false;
        }
        Tenant other = (Tenant)obj;
        if(name == null){
            if(other.name != null){
                return false;
            }
        }else if(!name.equals(other.name)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result =  super.hashCode();
        return result * seed + (name == null? 0: name.hashCode());
    }

    public TenantDetails createTenantDetails(){
        TenantDetails tenantDetails = new TenantDetails();
        tenantDetails.setTenantName(name);
        return tenantDetails;
    }
}
