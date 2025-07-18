package com.harusari.chainware.member.command.application.dto.request.vendor;

import com.harusari.chainware.common.dto.AddressRequest;
import com.harusari.chainware.vendor.command.domain.aggregate.VendorStatus;
import com.harusari.chainware.vendor.command.domain.aggregate.VendorType;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record VendorCreateRequest(
        String vendorName, VendorType vendorType, AddressRequest addressRequest, String vendorTaxId,
        String vendorMemo, VendorStatus vendorStatus, LocalDate vendorStartDate, LocalDate vendorEndDate
) {
}