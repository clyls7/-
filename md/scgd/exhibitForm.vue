<template>
  <view class="container">
    <view class="title">耀泰铜业铜带生产工艺流程卡</view>
    <view class="table">
      <view class="row">
        <text class="label">牌号：</text>
        <text class="value">H62</text>
        <text class="label">批号：</text>
        <text class="value">24121612A</text>
      </view>
      <view class="row">
        <text class="label">质量要求：</text>
        <text class="value">HV 75-85</text>
        <text class="label">包装要求：</text>
        <text class="value"></text>
      </view>
      <view class="row">
        <text class="label">规格：</text>
        <text class="value">2.48m</text>
      </view>
      <view class="row">
        <text class="label">成品重量：</text>
        <text class="value">3313KG</text>
        <text class="label">操作员：</text>
        <text class="value">刘建军</text>
        <text class="label">日期：</text>
        <text class="value">12.2</text>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      formData: {
        brand: 'H62',
        batch: '24121612A',
        quality: 'HV 75-85',
        specification: '2.48m',
        weight: '3313KG',
        operator: '刘建军',
        date: '12.2'
      }
    };
  }
};
</script>

<style>
.container {
  padding: 20px;
  background: #fff;
}
.title {
  font-size: 20px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;
}
.table {
  border: 1px solid #000;
  padding: 10px;
}
.row {
  display: flex;
  justify-content: space-between;
  padding: 5px 0;
  border-bottom: 1px solid #ddd;
}
.label {
  font-weight: bold;
  width: 25%;
}
.value {
  width: 25%;
  text-align: right;
}
</style>
