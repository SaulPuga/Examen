package com.chakray.ExamenChakray3.repositories;

import com.chakray.ExamenChakray3.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<AddressModel, Long> {
}
