package com.harusari.chainware.delivery.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "delivery")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long deliveryId;

    @Column(name = "store_order_id", nullable = false)
    private Long orderId;

    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;

    @Column(name = "take_back_id")
    private Long takeBackId;

    @Column(name = "tracking_number", length = 50)
    private String trackingNumber;

    @Column(name = "carrier", length = 50)
    private String carrier;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_method", nullable = false, length = 20)
    private DeliveryMethod deliveryMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status", nullable = false, length = 20)
    private DeliveryStatus deliveryStatus;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Builder
    public Delivery(Long orderId, Long warehouseId, Long takeBackId,
                    String trackingNumber, String carrier,
                    DeliveryMethod deliveryMethod, DeliveryStatus deliveryStatus,
                    LocalDateTime startedAt, LocalDateTime deliveredAt,
                    LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.orderId = orderId;
        this.warehouseId = warehouseId;
        this.takeBackId = takeBackId;
        this.trackingNumber = trackingNumber;
        this.carrier = carrier;
        this.deliveryMethod = deliveryMethod;
        this.deliveryStatus = deliveryStatus;
        this.startedAt = startedAt;
        this.deliveredAt = deliveredAt;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.modifiedAt = modifiedAt;
    }

    public void startDelivery(String trackingNumber, String carrier, LocalDateTime startedAt) {
        this.trackingNumber = trackingNumber;
        this.carrier = carrier;
        this.deliveryStatus = DeliveryStatus.IN_TRANSIT;
        this.startedAt = startedAt;
        this.modifiedAt = LocalDateTime.now();
    }

    public void completeDelivery(LocalDateTime deliveredAt) {
        this.deliveryStatus = DeliveryStatus.DELIVERED;
        this.deliveredAt = deliveredAt;
        this.modifiedAt = LocalDateTime.now();
    }

}