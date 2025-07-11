package com.harusari.chainware.product.query.dto.response;

import com.harusari.chainware.product.command.domain.aggregate.StoreType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProductDto {
    private Long productId;
    private String productName;
    private String productCode;
    private Long categoryId;
    private Integer basePrice;
    private String unitQuantity;
    private String unitSpec;
    private StoreType storeType;
    private Integer safetyStock;
    private String origin;
    private Integer shelfLife;
    private Boolean productStatus;
    private LocalDateTime productCreatedAt;
    private LocalDateTime productModifiedAt;

    public ProductDto(
            Long productId, String productName, String productCode, Long categoryId,
            Integer basePrice, String unitQuantity, String unitSpec, StoreType storeType,
            Integer safetyStock, String origin, Integer shelfLife, Boolean productStatus,
            LocalDateTime productCreatedAt, LocalDateTime productModifiedAt
    ) {
        this.productId = productId;
        this.productName = productName;
        this.productCode = productCode;
        this.categoryId = categoryId;
        this.basePrice = basePrice;
        this.unitQuantity = unitQuantity;
        this.unitSpec = unitSpec;
        this.storeType = storeType;
        this.safetyStock = safetyStock;
        this.origin = origin;
        this.shelfLife = shelfLife;
        this.productStatus = productStatus != null && productStatus;
        this.productCreatedAt = productCreatedAt;
        this.productModifiedAt = productModifiedAt;
    }
}
