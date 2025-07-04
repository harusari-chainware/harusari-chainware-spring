package com.harusari.chainware.statistics.query.service.disposal;

import com.harusari.chainware.statistics.exception.StatisticsErrorCode;
import com.harusari.chainware.statistics.exception.StatisticsException;
import com.harusari.chainware.statistics.query.dto.disposal.DisposalRateStatisticsResponse;
import com.harusari.chainware.statistics.query.dto.disposal.DisposalRateStatisticsResponseBase;
import com.harusari.chainware.statistics.query.dto.disposal.DisposalRateTrendGroupedResponse;
import com.harusari.chainware.statistics.query.mapper.DisposalRateStatisticsQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DisposalRateStatisticsQueryServiceImpl implements DisposalRateStatisticsQueryService {

    private final DisposalRateStatisticsQueryMapper mapper;

    @Override
    @Transactional
    public List<? extends DisposalRateStatisticsResponseBase> getDisposalStatistics(
            String period, Long warehouseId, Long franchiseId, LocalDate targetDate, boolean includeProduct) {

        LocalDate baseDate = targetDate != null ? targetDate : LocalDate.now().minusDays(1);
        LocalDate today = LocalDate.now();
        LocalDate startDate;
        LocalDate endDate;

        switch (period) {
            case "DAILY" -> startDate = endDate = baseDate;

            case "WEEKLY" -> {
                startDate = baseDate.minusDays(6);
                endDate = baseDate;
                if (today.isBefore(endDate)) {
                    throw new StatisticsException(StatisticsErrorCode.PERIOD_NOT_COMPLETED);
                }
            }

            case "MONTHLY" -> {
                startDate = baseDate.withDayOfMonth(1);
                endDate = baseDate.withDayOfMonth(baseDate.lengthOfMonth());
                if (today.isBefore(endDate)) {
                    throw new StatisticsException(StatisticsErrorCode.PERIOD_NOT_COMPLETED);
                }
            }

            default -> throw new StatisticsException(StatisticsErrorCode.UNSUPPORTED_PERIOD);
        }

        if (includeProduct) {
            return mapper.getProductLevelDisposalRate(startDate, endDate, warehouseId, franchiseId);
        } else {
            return mapper.getDisposalRate(startDate, endDate, warehouseId, franchiseId);
        }
    }

    @Override
    @Transactional
    public DisposalRateTrendGroupedResponse getGroupedTrend(String period, LocalDate targetDate) {
        LocalDate baseDate = (targetDate != null) ? targetDate : LocalDate.now().minusDays(1);

        List<DisposalRateStatisticsResponse> total = mapper.getTrendForTotal(period, baseDate);
        List<DisposalRateStatisticsResponse> headquarters = mapper.getTrendForHeadquarters(period, baseDate);
        List<DisposalRateStatisticsResponse> franchises = mapper.getTrendForFranchises(period, baseDate);

        return DisposalRateTrendGroupedResponse.builder()
                .total(total)
                .headquarters(headquarters)
                .franchises(franchises)
                .build();
    }
}
