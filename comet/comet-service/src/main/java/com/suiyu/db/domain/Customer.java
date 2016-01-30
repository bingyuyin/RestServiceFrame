package com.suiyu.db.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.suiyu.web.common.CustomerDetails;
import com.suiyu.web.common.CustomerShowDetails;
import com.suiyu.web.common.util.ThrowUtil;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by BingyuYin on 2016/1/30.
 */
@Entity
@Table(name = "CUSTOMER", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "tenant_id"})})
@Cache(region = "lmcm",usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer extends AbstractPersistable<Long> {
    private static final long serialVersionUID = -274227630137971840L;

    // Business ID
    @Column(name = "UUID", nullable = false, unique = true)
    private String uuid;

    // Business Key: name + tenant
    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "TENANT_ID")
    @JsonIgnore
    private Tenant tenant;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @Transient
    private List<Object> roles = new ArrayList<Object>();

    public Customer(){
        createUuid();
        createSalt();
    }

    @JsonIgnore
    public CustomerDetails getCustomerDetails(){
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setTenantName(getTenant().getName());
        customerDetails.setId(getUuid());
        customerDetails.setIsAdmin(getIsAdmin());
        customerDetails.setPassword(getPassword());
        customerDetails.setRoles(getRoles());
        customerDetails.setName(getName());
        return customerDetails;
    }

    @JsonIgnore
    public CustomerShowDetails getCustomerShowDetails(){
        CustomerShowDetails customerShowDetails = new CustomerShowDetails();
        customerShowDetails.setName(getName());
        customerShowDetails.setId(getUuid());
        customerShowDetails.setIsAdmin(getIsAdmin());
        customerShowDetails.setRoles(getRoles());
        return customerShowDetails;
    }

    @Override
    public int hashCode(){
        final int seed = 31;
        int result = super.hashCode();
        return result * seed + (uuid == null? 0: uuid.hashCode());
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj.getClass() != Customer.class) {
            return false;
        }
        Customer other = (Customer)obj;
        if(uuid == null){
            if(other.uuid != null){
                return false;
            }
        }else if(!uuid.equals(other.uuid)){
            return false;
        }
        return true;
    }

    private void createUuid(){
        if(getUuid() == null){
            setUuid(UUID.randomUUID().toString());
        }
    }

    private void createSalt(){
        if(getSalt() == null){
            setSalt(UUID.randomUUID().toString());
        }
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        ThrowUtil.verifyArgumentNotNull(uuid);
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ThrowUtil.verifyArgumentNotNull(name);
        this.name = name;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        ThrowUtil.verifyArgumentNotNull(salt);
        this.salt = salt;
    }

    public List<Object> getRoles() {
        return roles;
    }

    public void setRoles(List<Object> roles) {
        this.roles = roles;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
