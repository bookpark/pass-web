package com.fc.pass.service.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AggregatedStatistics {
    private LocalDateTime statisticsAt;
    private long allCount;
    private long attendedCount;
    private long cancelledCount;

    public AggregatedStatistics(LocalDateTime statisticsAt, long allCount, long attendedCount, long cancelledCount) {
        this.statisticsAt = statisticsAt;
        this.allCount = allCount;
        this.attendedCount = attendedCount;
        this.cancelledCount = cancelledCount;
    }

    public void merge(final AggregatedStatistics statistics) {
        this.allCount += statistics.getAllCount();
        this.attendedCount += statistics.getAttendedCount();
        this.cancelledCount += statistics.getCancelledCount();
    }
}
