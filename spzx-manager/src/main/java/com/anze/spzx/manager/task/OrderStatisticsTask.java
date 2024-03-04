package com.anze.spzx.manager.task;

import cn.hutool.core.date.DateUtil;
import com.anze.spzx.manager.mapper.OrderInfoMapper;
import com.anze.spzx.manager.mapper.OrderStatisticsMapper;
import com.anze.spzx.model.entity.order.OrderStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class OrderStatisticsTask {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics() {
        String createTime = DateUtil.offsetDay(new Date(), -1).toString(new SimpleDateFormat("yyyy-MM-dd"));
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(createTime);
        if(orderStatistics != null) {
            orderStatisticsMapper.insert(orderStatistics) ;
        }
    }

    //测试定时任务,通过Scheduled注解加cron表达式
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void testHello(){
//        //每5s一次
//        System.out.println(new Date().toInstant());
//    }
}
