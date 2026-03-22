<template>
  <div>
    <!-- 生产订单列表 -->
    <order-table @row-click="handleOrderSelect" @added="reloadOrders" ref="orderTable"/>

    <!-- 工单列表（传入所选订单）-->
    <div style="margin-left: 20px;">
      <div style="margin-bottom: 8px; font-size: 16px; font-weight: bold;">
        工单列表
      </div>
      <job-table @write-success="handleScgdWriteDone" :workorder-type="selectedWorkorderType" :pa-id="selectedPaId" :order-id="selectedOrderId" :brand="selectedBrand" ref="jobTable"/>
    </div>
  </div>
</template>

<script>
import OrderTable from './cpfpzb/index.vue'
import JobTable from './cyscpfpmx/index.vue'

export default {
  components: { OrderTable, JobTable },
  data() {
    return {
      selectedOrderId: '',
      selectedBrand: '',
      selectedPaId: '',
      selectedWorkorderType: ''
    };
  },
  methods: {
    handleOrderSelect(row) {
      // console.log('row = ', row);
      this.selectedOrderId = row.workorderCode || row.id;
      this.selectedBrand = row.brand;
      this.selectedPaId = row.paId;
      this.selectedWorkorderType = row.workorderType;
      // this.selectThisProess = row.;
    },
    reloadOrders() {
      this.$refs.orderTable.getList();
    },
    // ✅ 写入成功后刷新左边
    handleScgdWriteDone() {
      this.$refs.orderTable.getList();  // 调用 cpfpzb 中的方法
    }
  }
};
</script>
