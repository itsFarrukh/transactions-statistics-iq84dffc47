package com.n26.service;

import com.n26.model.Statistics;
import com.n26.model.Transaction;
import com.n26.repository.TransactionRepository;
import com.n26.service.impl.StatisticsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;
import static java.time.Instant.now;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTest {



    @Mock
    TransactionRepository transactionRepository= new TransactionRepository();

    @InjectMocks
    StatisticsServiceImpl statisticsService = new StatisticsServiceImpl();



    @Test
    public void shouldReturnStatisticsFromTransactions() throws Exception {
        final Transaction t1 = new Transaction(10.0, now());
        final Transaction t2 = new Transaction(8.0, now());
        when(transactionRepository.getTransactions()).thenReturn(asList(t1, t2));
        Statistics expectedResult = new Statistics(
                new BigDecimal(18.0),new BigDecimal(9.0),new BigDecimal(10.0),new BigDecimal(8.0),2);

        final Statistics actualStatistic = statisticsService.get();
        assertThat(actualStatistic, is(expectedResult));

    }
}
