package com.itacademy.jd2.yi.cms.dao.api.entity.table;

public interface IUserAccount extends IBaseEntity {

    String getName();

    void setName(String name);
    
    String getEmail();
    
    void setEmail(String email);
    
    String getPassword();
    
    void setPassword(String password);
    
    String getRole();
    
    void setRole(String role);
    
    String getStatus();
    
    void setStatus(String status);

}
