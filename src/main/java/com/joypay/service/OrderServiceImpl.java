package com.joypay.service;

import com.joypay.common.OrderNumGenerator;
import com.joypay.common.responResult;
import com.joypay.model.*;
import com.joypay.model.dto.Orderdto;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.mutation.AffectedTable;
import org.babyfish.jimmer.sql.ast.mutation.SaveMode;
import org.babyfish.jimmer.sql.ast.mutation.SimpleSaveResult;
import org.babyfish.jimmer.sql.ast.query.ConfigurableRootQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author dcj
 * @Date 2023/7/18 14:32
 * 演示:
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    JSqlClient sqlClient;
    @Autowired
    OrderInfoTable orderInfo;


    //分页查询所有订单
    @Override
    public List<OrderInfo> selectOrdersByPage() {
        final OrderInfoTable orderInfoTable = OrderInfoTable.$;
        final ConfigurableRootQuery<OrderInfoTable, OrderInfo> query = sqlClient
                .createQuery(orderInfoTable)
//     省略其它逻辑:
//     1. 任意复杂的动态条件
//     2. 任意复杂的动态牌型
//     3. 任意复杂的子查询
                .select(orderInfoTable);
        int rowCount = query.count();
        List<OrderInfo> allorders = query
                .limit(/*limit*/ rowCount / 3, /*offset*/ rowCount / 3)
                .execute();
        return allorders;
    }

    @Override
    public int createOrder(Orderdto orderdto) {
        OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
        orderNumGenerator.setOrderNum();
        //result可配合缓存
        SimpleSaveResult<OrderInfo> result = sqlClient.getEntities().saveCommand(OrderInfoDraft.$.produce(orderInfoDraft ->
                orderInfoDraft
                .setOrderNum(orderNumGenerator.getOrderNum())
                .setOrderDesc(orderdto.getOrderDesc())
                .setPayAmount(orderdto.getPayAmount())
                .setMerchantName(orderdto.getMerchantName())
                .setPaymentMethods(orderdto.getPaymentMethods())
                .setPaymentStatus(orderdto.getPaymentStatus())
        )).configure(it -> it.setMode(SaveMode.INSERT_ONLY))//插入模式
                .execute();
        int affectedRowCount = result.getAffectedRowCount(AffectedTable.of(User.class));
        return affectedRowCount;
    }

}
