package com.example.carpark.service.impl;

import com.example.carpark.entity.RoleEntity;
import com.example.carpark.entity.RoleEnumType;
import com.example.carpark.repository.UserRoleRepository;
import com.example.carpark.service.BaseService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
@AllArgsConstructor
public class UserRoleService implements BaseService<RoleEntity> {
    private final UserRoleRepository userRoleRepository;

    @Override
    public Collection<RoleEntity> getAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public RoleEntity create(RoleEntity model) {
        return userRoleRepository.save(model);
    }

    @Override
    public RoleEntity findById(String id) throws NotFoundException {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can not find such Role!"));
    }

    @Override
    public boolean remove(String id) {
        return userRoleRepository.findAll().remove(id);
    }

    @Override
    public RoleEntity update(String id, RoleEntity viewDto){
        return null;
    }

    @Override
    public RoleEntity getByName(String name) {
        return null;
    }

    public RoleEntity getByName(RoleEnumType roleType) throws NotFoundException {
        return userRoleRepository.findByRole(roleType)
                .orElseThrow(() -> new NotFoundException("Role with roleType {} do not exist!"));
    }
}
