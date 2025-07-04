package com.harusari.chainware.contract.query.service;

import com.harusari.chainware.common.dto.PagedResult;
import com.harusari.chainware.contract.query.dto.request.VendorProductContractSearchRequest;
import com.harusari.chainware.contract.query.dto.response.VendorProductContractDto;
import com.harusari.chainware.contract.query.dto.response.VendorProductContractListDto;
import com.harusari.chainware.contract.query.mapper.VendorProductContractMapper;
import com.harusari.chainware.exception.contract.ContractAccessDeniedException;
import com.harusari.chainware.exception.contract.ContractErrorCode;
import com.harusari.chainware.exception.contract.ContractNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VendorProductContractServiceImpl implements VendorProductContractService {

    private final VendorProductContractMapper mapper;

    @Override
    public PagedResult<VendorProductContractListDto> getContracts(VendorProductContractSearchRequest request, Long memberId, boolean isManager) {

        Long vendorId = null;

        if (isManager) {
            vendorId = null;
        } else {
            // 거래처 담당자: vendorId를 직접 조회 (파라미터에 없으면)
            if (request.getVendorId() != null) {
                vendorId = request.getVendorId();
            } else {
                // DB에서 본인의 vendorId를 찾기 (필요한 경우)
                vendorId = mapper.findVendorIdByMemberId(memberId)
                        .orElseThrow(() -> new ContractAccessDeniedException(ContractErrorCode.CONTRACT_ACCESS_DENIED));
            }
        }

        List<VendorProductContractListDto> content = mapper.findVendorProductContracts
                (request, vendorId, isManager);
        long total = mapper.countVendorProductContracts(request, vendorId, isManager);

        return PagedResult.<VendorProductContractListDto>builder()
                .content(content)
                .pagination(PagedResult.PaginationMeta.builder()
                        .page(request.getPage())
                        .size(request.getSize())
                        .totalElements(total)
                        .totalPages((int) Math.ceil((double) total / request.getSize()))
                        .build())
                .build();
    }

    @Override
    public VendorProductContractDto getContractById(Long contractId, Long memberId, boolean isManager) {
        Long vendorId = isManager
                ? null
                : mapper.findVendorIdByMemberId(memberId)
                .orElseThrow(() -> new ContractAccessDeniedException(
                        ContractErrorCode.CONTRACT_ACCESS_DENIED
                ));

        return mapper.findVendorProductContractById(contractId, vendorId, isManager)
                .orElseThrow(() -> new ContractNotFoundException(
                        ContractErrorCode.CONTRACT_NOT_FOUND
                ));
    }
}