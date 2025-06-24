<template>
  <div class="app-container home">
    <div>
      <el-row>
        <el-col :span="9">
                    <!-- 添加一个 div 用于渲染 ECharts 饼图 -->
                    <div ref="pieChart" style="width: 100%; height: 400px;"></div>
        </el-col>
        <el-col :span="15">
          <div>
            <!-- 柱状图容器 -->
            <div ref="barChart" style="width: 100%; height: 400px;"></div>
          </div>
        </el-col>
      </el-row>
      <el-row>
                    <!-- 折线图容器 -->
                    <div ref="lineChart" style="width: 100%; height: 400px;"></div>
      </el-row>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import * as echarts from 'echarts' // 引入 ECharts

// 获取 div 元素的引用
const pieChart = ref(null)
const barChart = ref(null)
const lineChart = ref(null)
// 在组件挂载后渲染饼图
onMounted(() => {
  renderPieChart()
  renderBarChart()
  renderLineChart()
})

// 渲染饼图的函数
function renderPieChart() {
  // 初始化 ECharts 实例
  const chart = echarts.init(pieChart.value)

  // 配置饼图数据
  const option = {
    title: {
      text: '合肥各区销售额占比', // 图表标题
      left: 'center' // 标题居中
    },
    tooltip: {
      trigger: 'item', // 触发类型，item 表示数据项图形触发
      formatter: '{a} <br/>{b}: {c} ({d}%)' // 提示框格式
    },
    legend: {
      orient: 'vertical', // 图例垂直排列
      left: 'left', // 图例靠左
      data: ['瑶海区', '庐阳区', '蜀山区', '包河区', '经开区', '高新区'] // 图例数据
    },
    series: [
      {
        name: '销售额占比', // 系列名称
        type: 'pie', // 饼图类型
        radius: '50%', // 饼图半径
        data: [
          { value: 30, name: '瑶海区' }, // 各区数据
          { value: 20, name: '庐阳区' },
          { value: 25, name: '蜀山区' },
          { value: 15, name: '包河区' },
          { value: 5, name: '经开区' },
          { value: 5, name: '高新区' }
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10, // 高亮时的阴影效果
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  }


  

  // 设置图表配置
  chart.setOption(option)
}

// 渲染折线图的函数
function renderLineChart() {
  const chart = echarts.init(lineChart.value)
  const option = {
    title: {
      text: '1 到 12 月份销售额趋势',
      left: 'center'
    },
    tooltip: {
      trigger: 'axis'
    },
    xAxis: {
      type: 'category',
      data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        name: '销售额',
        type: 'line',
        data: [12000, 20000, 15000, 8000, 7000, 11000, 13000, 18000, 9000, 14000, 16000, 10000],
        smooth: true, // 平滑曲线
        lineStyle: {
          color: '#FF6384', // 折线颜色
          width: 2
        },
        itemStyle: {
          color: '#FF6384' // 数据点颜色
        }
      }
    ]
  }
  chart.setOption(option)
}

// 渲染柱状图的函数
function renderBarChart() {
  const chart = echarts.init(barChart.value)
  const option = {
    title: {
      text: '1 到 12 月份销售额', // 柱状图标题
      left: 'center'
    },
    tooltip: {
      trigger: 'axis', // 触发类型，axis 表示坐标轴触发
      axisPointer: {
        type: 'shadow' // 阴影指示器
      }
    },
    xAxis: {
      type: 'category', // 类目轴
      data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'] // X 轴数据
    },
    yAxis: {
      type: 'value' // 数值轴
    },
    series: [
      {
        name: '销售额',
        type: 'bar', // 柱状图类型
        data: [12000, 20000, 15000, 8000, 7000, 11000, 13000, 18000, 9000, 14000, 16000, 10000], // Y 轴数据（1 到 12 月的销售额）
        itemStyle: {
          color: '#36A2EB' // 柱状图颜色
        }
      }
    ]
  }
  chart.setOption(option)
}


// 跳转函数（无关紧要，可以保留）
function goTarget(url) {
  window.open(url, '__blank')
}
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}

/* 为 ECharts 容器添加样式 */
.pie-chart-container {
  width: 100%;
  height: 400px;
}

.bar-chart-container {
  width: 100%;
  height: 400px;
}

.line-chart-container {
  width: 100%;
  height: 400px;
}
</style>