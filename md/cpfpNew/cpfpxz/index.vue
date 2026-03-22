<template>
  <el-dialog
    title="选择生产工单"
    :visible.sync="visible"
    width="1027px"
    :modal="false"
    @close="handleClose"
  >
    <!-- 可扩展为搜索区域 -->
<!--    <el-form inline size="small">-->
<!--      <el-form-item label="工单类型" prop="workorderType">-->
<!--        <el-select v-model="queryParams.workorderType" placeholder="请选择工单类型" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.sys_cys_gdlx"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-search" @click="getList">查询</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->

    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="scgdmxList"
      style="width: 100%"
      border
      stripe
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="生产工单号" width="200" align="center" prop="workCode" fixed />
      <el-table-column label="开工时间" align="center" prop="startTime" width="200">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工单状态" width="100" align="center" prop="workStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_sc_gdzt" :value="scope.row.workStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="牌号" width="100" align="center" prop="brand">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_rz_ph" :value="scope.row.brand"/>
        </template>
      </el-table-column>
      <el-table-column label="批次号" width="200" align="center" prop="batchNumber">
        <template slot-scope="scope">
          <BatchTag :batchNumber="scope.row.batchNumber" />
        </template>
      </el-table-column>
      <el-table-column label="当前工序" width="130" align="center" prop="thisProcess">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_cys_gx" :value="scope.row.thisProcess"/>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 底部按钮 -->
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="handleConfirm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { listScgdmx, getScgdmx, delScgdmx, addScgdmx, updateScgdmx } from "@/api/mes/md/scgdmx";
import {listScgd, updateScgd} from "@/api/mes/md/scgd";
import {listCpfp} from "@/api/mes/md/cpfp";
import { listSx, getSx, delSx, addSx, updateSx } from "@/api/mes/md/sx";
import { listJjb, addJjb, delJjb, getJjb, updateJjb } from "@/api/mes/md/jjb";
import { listCpjy, getCpjy, delCpjy, addCpjy, updateCpjy } from "@/api/mes/md/cpjy";
import { listJz, getJz, delJz, addJz, updateJz } from "@/api/mes/md/jz";

export default {
  name: "scgdListbom",
  dicts: ['sys_rz_ph', 'sys_sc_gdzt', 'sys_cys_gx', 'sys_rz_zlpd', 'sys_smx_zlpd', 'sys_cys_gx','sys_cys_gdlx', 'sys_cys_gdlx'],
  props: {
    existWorkCodes: {
      type: Array,
      default: () => []
    },
    workorder: {
      type: Array,
      default: () => []
    },
    brand: {
      type: String,
      default: ''
    },
    visible: {
      type: Boolean,
      default: false
    },
    workorderType: {
      type: String,
      default: ''
    },
    workCodes: {
      type: Array,
      default: () => []
    },
    paId: {
      type: String,
      default: ''
    }
  },
  computed :{
    filteredProcessOptions() {
      return this.dict.type.sys_cys_gx.filter(opt =>
        ['7', '8', '9'].includes(opt.value)
      );
    }
  },
  data() {
    return {
      //控制下拉框显示范围
      filteredOptions: [],
      // 遮罩层
      loading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 生产工单明细表格数据
      scgdmxList: [],
      selectedList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        workCode: null,    scgdmxId: null,  workorderType: null,    porderCode: null,   cpfpParentid: null,    porderName: null,        submitTime: null,        workStatus: 1,         thisProcess: null,        startTime: null,        endTime: null,        brand: null,        batchNumber: null,        plannedOrdersCode: null,        demandTime: null,        itemId: null,        itemCode: null,        itemName: null,        unit: null,        clientId: null,        clientCode: null,        clientName: null,        performanceReq: null,        qualityReq: null,        packageReq: null,      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        width: [
          { required: true, message: "宽度不能为空", trigger: "blur" }
        ],        weight: [
          { required: true, message: "重量不能为空", trigger: "blur" }
        ],      }
    };
  },
  watch: {
    visible(val) {
      if (val) {
        this.queryParams.brand = this.brand;
        console.log("this.queryParams.brand = ", this.queryParams.brand);
        this.getList();
      }
    },

    workCodes: {
      handler(newVal) {
        console.log('workCodes 变了:', newVal);
        if (this.visible) {
          this.getList();   // 🔥 关键
        }
      },
      deep: true
    }
  },
  methods: {
    handleClose() {
      this.$emit('update:visible', false); // ✅ 关键！同步关闭到父组件
    },
    async handleConfirm() {
      if (!this.ids || this.ids.length === 0) {
        this.$message.warning('请至少选择一条工单');
        return;
      }
      // 根据 ids 从 scgdmxList 中找出完整的选中行
      const selectedRows = this.scgdmxList.filter(row => this.ids.includes(row.workId));

      // 遍历选中的工单，逐个更新状态
      for (const row of selectedRows) {
        // 1. 获取当前行的工序 (转成字符串以防万一)
        const currentProcess = String(row.thisProcess);

        // 2. 找出对应牌号信息
        // 确保字典 dict.type.sys_rz_ph 已加载
        const phpd = this.dict.type.sys_rz_ph.find(option => option.value === row.brand) || {label: 'defaultLH'};
        const pdstr = phpd.label.substring(0, 1).toUpperCase(); // 建议转大写防止大小写不一致

        let cyspd = currentProcess; // 默认为当前工序，防止 undefined
        let thisGx = '状态更新';

        // 3. 逻辑判断：这里全部改成使用 currentProcess (即 row.thisProcess)
        if (currentProcess === '6') {
          if (pdstr === 'H') {
            cyspd = '7';
            thisGx = '已进入退火工序';
          } else if (pdstr === 'T') {
            cyspd = '9';
            thisGx = '已进入精轧工序';
          } else {
            // 如果是工序6但牌号既不是H也不是T，保持原样或按需处理
            cyspd = '6';
          }
        } else {
          // 如果不是工序6，保持原有工序
          cyspd = currentProcess;
        }

        // 构造更新数据
        const scgdcys = {
          workId: row.workId,
          workStatus: '1',
          thisProcess: cyspd
        };

        console.log("准备提交的数据 scgdcys = ", scgdcys); // 这里应该有值了

        try {
          await updateScgd(scgdcys);
          console.log(`工单 ${row.workCode} 更新成功: ${thisGx}`);
        } catch (e) {
          console.error(`工单 ${row.workCode} 更新失败`, e);
          this.$message.error(`工单 ${row.workCode} 更新失败`);
        }
      }

      this.$emit('confirm', selectedRows); // 把完整数据发回去
      this.handleClose(); // 关闭弹窗
    },
    initData(data) {
      this.fqmxList = data;
    },
    getData() {
      return this.fqmxList;
    },
    /** 查询生产工单明细列表 */
    // getList() {
    //   this.loading = true;
    //   this.queryParams.brand = this.brand; // 🔥 关键：动态设置牌号条件
    //   this.queryParams.thisProcessList = [4,5,12];
    //   const existCodes = this.workCodes || [];
    //   console.log("existCodes = " , existCodes);
    //   listScgd(this.queryParams).then(response => {
    //     console.log(response.rows);
    //     this.scgdmxList = response.rows.filter(row => !existCodes.includes(row.workCodes));
    //     // this.scgdmxList = response.rows;
    //     this.total = response.total;
    //     this.loading = false;
    //   });
    // },
    // getList() {
    //   this.loading = true;
    //   this.queryParams.brand = this.brand; // 动态设置牌号条件
    //   if(this.form.workOrderType === '0'){
    //     this.queryParams.thisProcessList = [6,12];
    //   }else{
    //     this.queryParams.thisProcessList = [6,9,10,12,14];
    //   }
    //
    //   // 已存在的工单号
    //   const existCodes = this.workCodes || [];
    //   console.log("existCodes =", existCodes); // 确认是数组
    //
    //   listScgd(this.queryParams).then(response => {
    //     const { rows = [], total = 0 } = response;
    //     console.log("接口返回 rows:", rows);
    //     console.log("已存在工单号 existCodes:", existCodes);
    //
    //     // existCodes 转成 Set，比较更高效
    //     const existSet = new Set((existCodes || []).map(c => String(c).trim()));
    //
    //     // 过滤掉已存在的工单
    //     this.scgdmxList = rows.filter(row => !existSet.has(String(row.workCode).trim()));
    //
    //     this.total = this.scgdmxList.length;
    //     this.loading = false;
    //
    //     console.log("过滤后的列表:", this.scgdmxList);
    //   });
    // },

    async getList() {
      this.loading = true;
      this.scgdmxList = []; // 先清空，防止界面闪烁

      try {
        // 1️⃣ 统一获取需要排除的工单号
        // 优先使用 existWorkCodes (父组件传来的)，兼容 workCodes
        const codesToExclude = (this.existWorkCodes && this.existWorkCodes.length > 0)
          ? this.existWorkCodes
          : (this.workCodes || []);

        const existSet = new Set(
          codesToExclude
            .map(c => String(c).trim())
            .filter(Boolean)
        );

        console.log("需要排除的工单号:", [...existSet]);

        // 2️⃣ 准备查询参数
        const workType = String(this.workorderType || '0');
        this.queryParams.brand = this.brand;

        // 设置工序范围
        if (workType === '0') {
          this.queryParams.thisProcessList = [6, 12];
        } else {
          this.queryParams.thisProcessList = [6, 9, 10, 12, 14];
        }

        // 3️⃣ 调用主接口获取列表
        const response = await listScgd(this.queryParams);
        const rows = Array.isArray(response?.rows) ? response.rows : [];

        // 4️⃣ 开始过滤逻辑
        let finalResults = [];

        // ==========================================
        // 🟢 情况 A：普通工单 (workorderType === '0')
        // ==========================================
        if (workType === '0') {
          // 只做简单的 ID 排除
          finalResults = rows.filter(row => {
            const code = String(row.workCode || '').trim();
            return code && !existSet.has(code);
          });
        }
          // ==========================================
          // 🟠 情况 B：复杂工单 (需要校验酸洗/轧制时间)
        // ==========================================
        else {
          for (const row of rows) {
            const workCode = String(row.workCode || '').trim();
            const procNum = Number(row.thisProcess);

            // [步骤 1] 基础校验：无工单号 或 已存在于父组件列表，直接跳过
            if (!workCode || existSet.has(workCode)) {
              continue;
            }

            // [步骤 2] 快速放行特定工序 (优化性能，减少接口请求)
            if (procNum === 6 && workType === '0') {
              finalResults.push(row);
              continue;
            }
            if (procNum === 12 && workType === '1') {
              finalResults.push(row);
              continue;
            }

            // [步骤 3] 复杂逻辑校验 (查询子表)
            // 注意：这里会有性能损耗，建议后端优化。但在前端修改只能如此。
            const querySub = {
              pageNum: 1,
              pageSize: 10,
              workCode: workCode,
              workorderCode: workCode // 兼容不同接口字段
            };

            // 并发请求子表数据
            const [sxResp, jzResp, jjbResp, cpjyResp] = await Promise.all([
              listSx(querySub),
              listJz(querySub),
              listJjb(querySub),
              listCpjy(querySub)
            ]);

            // 3.1 必须有酸洗记录
            const sxList = sxResp?.rows || [];
            if (sxList.length === 0) continue;

            // 3.2 必须没有成品检验记录
            if ((cpjyResp?.rows || []).length > 0) continue;

            // 3.3 时间比对工具函数
            const getLatestTime = (list) => {
              if (!list || list.length === 0) return 0;
              // 按照创建时间降序排序，取第一条
              const sorted = list.sort((a, b) => new Date(b.createTime) - new Date(a.createTime));
              return new Date(sorted[0].createTime).getTime();
            };

            const sxTime = getLatestTime(sxList);
            const jzTime = getLatestTime(jzResp?.rows);
            const jjbTime = getLatestTime(jjbResp?.rows);

            // 3.4 核心业务逻辑：酸洗时间必须 大于 轧制时间 和 精整包时间
            // 意味着：最新的操作必须是酸洗
            if (sxTime > jzTime && sxTime > jjbTime) {
              finalResults.push(row);
            }
          }
        }

        // 5️⃣ 赋值结果
        this.scgdmxList = finalResults;
        // console.log("ccscgdmxList = ", this.scgdmxList);
        this.total = finalResults.length;

      } catch (err) {
        console.error("加载工单列表失败:", err);
        this.$message.error("获取工单列表失败");
        this.scgdmxList = [];
        this.total = 0;
      } finally {
        this.loading = false;
      }
    },


    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
      this.$emit('update:visible', false);   // 🚀 通知父组件
    },
    // 表单重置
    reset() {
      this.form = {
        scgdmxId: null,
        workId: null,
        workCode: null,
        porderCode: null,
        porderName: null,
        submitTime: null,
        workStatus: null,
        thisProcess: null,
        startTime: null,
        endTime: null,
        brand: null,
        cpfpParentid: null,
        batchNumber: null,
        plannedOrdersCode: null,
        workorderType: null,
        demandTime: null,
        itemId: null,
        itemCode: null,
        itemName: null,
        unit: null,
        clientId: null,
        clientCode: null,
        clientName: null,
        performanceReq: null,
        qualityReq: null,
        packageReq: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    getSelectedRows() {
      if (!this.scgdmxList || !Array.isArray(this.scgdmxList)) return [];
      return this.scgdmxList.filter(row => this.ids.includes(row.workId));
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.workId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加生产工单明细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const workId = row.workId || this.ids
      getScgdmx(workId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改生产工单明细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.workId != null) {
            updateScgdmx(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addScgdmx(this.form).then(async response => {
              this.$modal.msgSuccess("新增成功");
              // console.log("updateScgd response:", response1);
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const workIds = row.workId || this.ids;
      this.$modal.confirm('是否确认删除生产工单明细编号为"' + workIds + '"的数据项？').then(function() {
        return delScgdmx(workIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('md/scgdmx/export', {
        ...this.queryParams
      }, `scgdmx_${new Date().getTime()}.xlsx`)
    },
    clearData() {
      this.scgdmxList = [
        {
          nextProcess : null
        }
      ];
      this.ids = [];
    }
  }
};
</script>
<style scoped>
.fq-table-wrapper {
  width: 100%;
  margin-top: 20px;
  padding: 15px;
  background: #fff;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  overflow-x: auto;
  box-sizing: border-box;
}

.fq-header-bar {
  margin-bottom: 10px;
  text-align: left;
}
</style>

