package com.harusari.chainware.takeback.command.domain.repository;

import com.harusari.chainware.takeback.command.domain.aggregate.TakeBackDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TakeBackDetailRepository extends JpaRepository<TakeBackDetail, Long> {
    List<TakeBackDetail> findByTakeBackId(Long takeBackId);
}
