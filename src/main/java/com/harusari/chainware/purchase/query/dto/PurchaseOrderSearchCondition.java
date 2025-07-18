package com.harusari.chainware.purchase.query.dto;

import com.harusari.chainware.purchase.command.domain.aggregate.PurchaseOrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PurchaseOrderSearchCondition {

    private PurchaseOrderStatus status;
    private Long requesterId;
    private Long vendorMemberId;
    private String drafterName;
    private String vendorName;
    private Long warehouseId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int page = 0;
    private int size = 10;

    public int getOffset() {
        return page * size;
    }
}
