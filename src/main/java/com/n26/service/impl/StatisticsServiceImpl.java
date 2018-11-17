package com.n26.service.impl;

import com.n26.model.Statistics;
import com.n26.model.Transaction;
import com.n26.repository.TransactionRepository;
import com.n26.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Statistics get() {
        DoubleSummaryStatistics summaryStatistics = transactionRepository.getTransactions()
                                                    .parallelStream()
                                                    .collect( Collectors.summarizingDouble((Transaction::getAmount)));
        return new Statistics(convertToBigDecimal( summaryStatistics.getSum()),convertToBigDecimal( summaryStatistics.getAverage()),
                              convertToBigDecimal(summaryStatistics.getMax()),convertToBigDecimal( summaryStatistics.getMin()),
                              summaryStatistics.getCount());
    }
    public BigDecimal convertToBigDecimal(Double amount){
        BigDecimal decimalValue = new BigDecimal(Double.parseDouble(Double.toString(checkIfDoubleIsNaN(amount))));
        decimalValue.setScale(2,BigDecimal.ROUND_HALF_UP);
        return decimalValue;
    }
    public Double checkIfDoubleIsNaN(Double value){
      return   Double.isInfinite(value)?0 : value;
    }

}
