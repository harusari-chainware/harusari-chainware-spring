package com.harusari.chainware.vendor.query.dto;

import com.harusari.chainware.vendor.command.domain.aggregate.VendorStatus;
import com.harusari.chainware.vendor.command.domain.aggregate.VendorType;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class VendorDetailDto {

    private Long vendorId;
    private Long memberId;
    private String name;
    private String phoneNumber;
    private String vendorName;
    private VendorType vendorType;
    private String vendorZipcode;
    private String vendorAddressRoad;
    private String vendorAddressDetail;
    private String vendorTaxId;
    private String vendorMemo;
    private VendorStatus vendorStatus;
    private String agreement;
    private LocalDate vendorStartDate;
    private LocalDate vendorEndDate;
}
