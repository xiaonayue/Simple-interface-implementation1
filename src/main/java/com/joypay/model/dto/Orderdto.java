package com.joypay.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author dcj
 * @Date 2023/7/18 17:06
 * 演示:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orderdto {
    private String orderDesc;
    private BigDecimal payAmount;
    private String merchantName;
    private long paymentMethods;
    private long paymentStatus;


}
