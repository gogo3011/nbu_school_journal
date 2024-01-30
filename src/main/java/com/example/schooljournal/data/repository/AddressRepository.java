package com.example.schooljournal.data.repository;

import com.example.schooljournal.data.entity.core.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
