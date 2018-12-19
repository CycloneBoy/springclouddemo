package com.cycloneboy.jsoupdata.repository;

import com.cycloneboy.jsoupdata.entity.moive.MoiveUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoiveUrlRepository extends JpaRepository<MoiveUrl,Long> {
}
