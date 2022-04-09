package com.ncirl.x21153213.cloudurlshortner.repository;

import com.ncirl.x21153213.cloudurlshortner.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity,Long> {
}
