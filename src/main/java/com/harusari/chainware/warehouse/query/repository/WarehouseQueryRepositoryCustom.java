package com.harusari.chainware.warehouse.query.repository;

import com.harusari.chainware.warehouse.query.dto.request.WarehouseSearchRequest;
import com.harusari.chainware.warehouse.query.dto.response.WarehouseDetailResponse;
import com.harusari.chainware.warehouse.query.dto.response.WarehouseSearchResponse;
import com.harusari.chainware.warehouse.query.dto.response.WarehouseSimpleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import java.util.List;

public interface WarehouseQueryRepositoryCustom {
    Page<WarehouseSearchResponse> searchWarehouses(WarehouseSearchRequest request, Pageable pageable);
    WarehouseDetailResponse findWarehouseDetailById(Long warehouseId);
    Long findWarehouseIdByManagerId(Long memberId);

    List<WarehouseSimpleResponse> findAllWarehouseSimple();
}
