package com.harusari.chainware.franchise.query.repository;

import com.harusari.chainware.franchise.command.domain.aggregate.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FranchiseQueryRepository extends FranchiseQueryRepositoryCustom, JpaRepository<Franchise, Long> {

    Optional<Franchise> findFranchiseByFranchiseId(Long franchiseId);

}