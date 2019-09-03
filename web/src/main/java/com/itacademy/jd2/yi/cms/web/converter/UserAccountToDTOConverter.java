package com.itacademy.jd2.yi.cms.web.converter;

import java.util.function.Function;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.yi.cms.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.yi.cms.web.dto.UserAccountDTO;

@Component
public class UserAccountToDTOConverter implements Function<IUserAccount, UserAccountDTO> {

    @Override
    public UserAccountDTO apply(final IUserAccount entity) {
        final UserAccountDTO dto = new UserAccountDTO();
        
        String hashed = BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(hashed);
        dto.setRole(entity.getRole());
        dto.setStatus(entity.getStatus());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        return dto;
    }

}

