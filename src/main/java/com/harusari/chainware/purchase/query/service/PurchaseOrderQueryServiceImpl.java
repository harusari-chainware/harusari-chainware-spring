package com.harusari.chainware.purchase.query.service;

import com.harusari.chainware.exception.purchase.PurchaseOrderErrorCode;
import com.harusari.chainware.exception.purchase.PurchaseOrderException;
import com.harusari.chainware.purchase.query.dto.PurchaseOrderSearchCondition;
import com.harusari.chainware.purchase.query.dto.PurchaseOrderDetailResponse;
import com.harusari.chainware.purchase.query.dto.PurchaseOrderSummaryResponse;
import com.harusari.chainware.purchase.query.mapper.PurchaseOrderQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseOrderQueryServiceImpl implements PurchaseOrderQueryService {

    private final PurchaseOrderQueryMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseOrderSummaryResponse> getPurchaseOrders(Long memberId, PurchaseOrderSearchCondition condition) {
        return mapper.findPurchaseOrders(memberId, condition);
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseOrderDetailResponse getPurchaseOrderDetail(Long memberId, Long purchaseOrderId) {
        var order = mapper.findPurchaseOrderById(memberId, purchaseOrderId);

        if (order == null) {
            throw new PurchaseOrderException(PurchaseOrderErrorCode.PURCHASE_NOT_FOUND);
        }

        var products = mapper.findProductsByPurchaseOrderId(memberId, purchaseOrderId);

        PurchaseOrderDetailResponse response = new PurchaseOrderDetailResponse();
        response.setPurchaseOrderId(order.getPurchaseOrderId());
        response.setPurchaseOrderCode(order.getPurchaseOrderCode());
        response.setVendorName(order.getVendorName());
        response.setWarehouseId(order.getWarehouseId());
        response.setStatus(order.getStatus());
        response.setTotalAmount(order.getTotalAmount());
        response.setCreatedAt(order.getCreatedAt());
        response.setProducts(products);

        return response;
    }

}