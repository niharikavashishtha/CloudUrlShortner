package com.ncirl.x21153213.cloudurlshortner.repository;

import com.ncirl.x21153213.cloudurlshortner.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
