<template>
    <div ref="orderTotalAmountDiv" style="width: 100%; height: 100%;"></div>
</template>

<script setup>
import { ref , onMounted } from 'vue'
import * as echarts from 'echarts';         // 导入Echart库中所有的图形报表组件

// 定义chart数据模型，用来选中div组件
const orderTotalAmountDiv = ref() 

// 需要在onMounted钩子函数中对div区域使用echarts进行初始化
onMounted( () => {

    // 基于准备好的dom，初始化echarts实例
    var orderTotalAmountChart = echarts.init(orderTotalAmountDiv.value);

    // 绘制图表
    orderTotalAmountChart.setOption({
        title: {
            text: '订单数据统计'
        },
        tooltip: {},
        xAxis: {
            data: ['2023-06-01', '2023-06-02', '2023-06-03', '2023-06-04', '2023-06-05', '2023-06-06']
        },
        yAxis: {},
        series: [
            {
                name: '订单总金额（万元）',
                type: 'bar',
                data: [20, 40, 30, 100, 50, 25]
            }
        ]
	});
})
</script>

<style scoped>

</style>