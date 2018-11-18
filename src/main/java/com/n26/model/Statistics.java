package com.n26.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Data
public class Statistics {

    BigDecimal sum;
    BigDecimal avg;
    BigDecimal max;
    BigDecimal min;
    long count;

    public Statistics(BigDecimal sum, BigDecimal avg, BigDecimal max, BigDecimal min, long count) {
        this.sum =  sum.setScale(2,RoundingMode.HALF_UP);
        this.avg = avg.setScale(2, RoundingMode.HALF_UP);
        this.max = max.setScale(2,RoundingMode.HALF_UP);;
        this.min = min.setScale(2,RoundingMode.HALF_UP);;
        this.count = count;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
