package com.example.demo.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("select n from News n where (n.createdAt >= :startDate or :startDate = null) and (n.createdAt <= :endDate or :endDate = null) and n.culturalOffer.id = :culturalOfferId order by n.createdAt desc")
    public Page<News> filter(Date startDate, Date endDate, long culturalOfferId, Pageable pageable);
}
