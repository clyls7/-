<template>
  <div class="fq-table-wrapper">
    <!-- 按钮行 -->
    <div class="fq-header-bar">
      <el-button
        type="primary"
        plain
        icon="el-icon-plus"
        size="mini"
        @click="showScgdDialog = true"
        v-hasPermi="['md:scgdmx:add']"
      >新增</el-button>

      <el-button
        type="danger"
        plain
        icon="el-icon-delete"
        size="mini"
        :disabled="multiple"
        @click="handleDelete"
        v-hasPermi="['md:scgdmx:remove']"
      >删除</el-button>
    </div>

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
      <el-table-column label="生产工单编码" width="260" align="center" prop="workCode" fixed />
      <el-table-column label="开工时间" align="center" prop="startTime" width="230">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="提交时间" align="center" prop="submitTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.submitTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="工单状态" width="158" align="center" prop="workStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_sc_gdzt" :value="scope.row.workStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="当前工序" width="158" align="center" prop="thisProcess">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_cys_gx" :value="scope.row.thisProcess"/>
        </template>
      </el-table-column>
      <el-table-column label="品位" width="158" align="center" prop="grade">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_rz_pw" :value="scope.row.grade"/>
        </template>
      </el-table-column>
      <el-table-column label="牌号" width="158" align="center" prop="brand">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_rz_ph" :value="scope.row.brand"/>
        </template>
      </el-table-column>
      <el-table-column label="批次号" width="250" align="center" prop="batchNumber">
        <template slot-scope="scope">
          <!-- 使用自定义组件 BatchTag -->
          <BatchTag :batchNumber="scope.row.batchNumber" />
        </template>
      </el-table-column>
<!--      <el-table-column label="工序" width="180" align="center" prop="thisProcess">-->
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.sys_cys_gx" :value="scope.row.thisProcess"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="厚度" width="130" align="center" prop="blankThickness" />
<!--      <el-table-column label="开坯宽度" width="130" align="center" prop="blankWidth" />-->
      <el-table-column label="重量" width="163" align="center" prop="blankWidth" />
      <el-table-column label="下道工序" prop="nextProcess" align="center" width="179" >
        <template slot-scope="scope">
          <el-select
            v-model="scope.row.nextProcess"
            placeholder="请选择下道工序"
            size="mini"
            @change="handleEdgeChange(scope.row)"
            :disabled="scope.row.thisProcess !== '12'"
          >
            <el-option
              v-for="dict in filteredProcessOptions"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

<!--    <ScgdSelectDialog-->
<!--      :visible.sync="showScgdDialog"-->
<!--      :brand="dialogQueryParams.brand"-->
<!--      :order-id="dialogQueryParams.orderId"-->
<!--      :pa-id="dialogQueryParams.paId"-->
<!--      :workorder-type="dialogQueryParams.workorderType"-->
<!--      @confirm="handleSelectConfirm"-->
<!--    />-->
    <ScgdSelectDialog
      :visible.sync="showScgdDialog"
      :brand="dialogQueryParams.brand"
      :order-id="dialogQueryParams.orderId"
      :pa-id="dialogQueryParams.paId"
      :workorder-type="dialogQueryParams.workorderType"
      :exist-work-codes="existWorkCodes"
      @confirm="handleSelectConfirm"
    />

  </div>
</template>

<script>
import { listScgdmx, getScgdmx, delScgdmx, addScgdmx, updateScgdmx } from "@/api/mes/md/scgdmx";
import {listScgd} from "@/api/mes/md/scgd";
import ScgdSelectDialog from '@/views/mes/md/cpfpNew/cpfpxz/index.vue';
import {listCpfp,updateCpfp} from "@/api/mes/md/cpfp";
import {listJz} from "@/api/mes/md/jz";
import {listKp} from "@/api/mes/md/kp";
import {listRz} from "@/api/mes/md/rz";

export default {
  name: "scgdListbom",
  dicts: ['sys_rz_ph', 'sys_sc_gdzt', 'sys_cys_gx', 'sys_rz_zlpd', 'sys_smx_zlpd', 'sys_cys_gx','sys_rz_pw'],
  props: {
    workorder: {
      type: Array,
      default: () => []
    },
    orderId: {
      type: String,
      default: ''
    },
    brand: {
      type: String,
      default: ''
    },
    paId:{
      type: String,
      default: ''
    },
    workorderType: {
      type: String,
      default: ''
    }
  },
  components: { ScgdSelectDialog },
  computed :{
    combinedProps() {
      return {
        orderId: this.orderId,
        brand: this.brand,
        paId: this.paId,
        workorderType: this.workorderType
      };
    },
    existWorkCodes() {
      return (this.scgdmxList || [])
        .map(r => String(r.workCode).trim())
        .filter(Boolean);
    },
    filteredProcessOptions() {
      return this.dict.type.sys_cys_gx.filter(opt =>
        ['7', '8', '9'].includes(opt.value)
      );
    }
  },
  data() {
    return {
      //控制自定义生产工单选择的显示
      showScgdDialog: false,
      dialogQueryParams: {
        workCodes : [],
        brand: '',
        orderId: '',
        paId : '',
        workorderType: ''
      },
      //控制下拉框显示范围
      filteredOptions: [],
      // 遮罩层
      loading: true,
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
      scgdmxList: [
        {
          nextProcess : null
        }
      ],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        workCode: null,    scgdmxId: null,    porderCode: null,   cpfpParentid: null,    porderName: null,        submitTime: null,        workStatus: 1,         thisProcess: null,        startTime: null,        endTime: null,        brand: null,        batchNumber: null,        plannedOrdersCode: null,        demandTime: null,        itemId: null,        itemCode: null,        itemName: null,        unit: null,        clientId: null,        clientCode: null,        clientName: null,        performanceReq: null,        qualityReq: null,        packageReq: null,      },
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
    // 合并监听 props
    combinedProps: {
      handler({ orderId, brand, paId, workorderType }) {
        console.log("orderId = " + orderId + " brand = " + brand + " paId = " + paId + " workOrderType = " + workorderType) ;
        if (orderId && brand && paId && workorderType) {
          //生产订单号
          this.queryParams.porderCode = orderId;
          //牌号
          this.queryParams.brand = brand;
          // 把值准备好传给弹窗
          this.dialogQueryParams = { orderId, brand, paId, workorderType};
          this.getList();
        }
      },
      immediate: true,
      deep: false
    },
    workorder: {
      handler(newVal) {
        if (Array.isArray(newVal) && newVal.length > 0) {
          this.getList();
        } else {
          // 没有选中父表，不加载数据，不显示 loading
          this.scgdmxList = [];
          this.total = 0;
          this.loading = false;
        }
      },
      immediate: true,
      deep: true
    },
  },
  mounted() {
    // console.log(this.$props);
    if (this.porderCode) {
      this.queryParams.porderCode = this.porderCode;
      this.queryParams.brand = this.brand;
      this.getList();
    }
  },
  methods: {
    refreshList() {
      this.getList();
    },
    async handleSelectConfirm(selectedRows) {
      if (!selectedRows || selectedRows.length === 0) {
        this.$message.warning('请至少选择一条工单');
        return;
      }

      const addPromises = selectedRows.map(row => {
        const payload = {
          ...row,
          porderCode: this.dialogQueryParams.orderId // 建立关联关系
        };
        return addScgdmx(payload);
      });

      const results = await Promise.allSettled(addPromises);
      const successCount = results.filter(r => r.status === 'fulfilled').length;
      const failCount = results.filter(r => r.status === 'rejected').length;

      if (successCount > 0) {
        // --- 获取当前主表数量 ---
        const mainDataRes = await listCpfp({
          paId: this.dialogQueryParams.paId,
          workorderCode: this.dialogQueryParams.orderId,
        });

        const mainRow = Array.isArray(mainDataRes?.rows) && mainDataRes.rows.length > 0
          ? mainDataRes.rows[0]
          : null;

        const currentAllocated = mainRow?.allocatedCount || 0;
        const currentProgress = mainRow?.progressCount || 0;

        // --- 在当前数量基础上累加 ---
        const updateCount = {
          paId : this.dialogQueryParams.paId,
          workorderCode : this.dialogQueryParams.orderId,
          allocatedCount: currentAllocated + successCount,
          progressCount: currentProgress + successCount
        };
        await updateCpfp(updateCount);

        this.$message.success(`成功添加 ${successCount} 条工单`);

        // ✅ 关闭弹窗
        this.showScgdDialog = false;

        // ✅ 主动刷新工单列表
        this.getList();

        // ✅ 通知父组件刷新左边 cpfpzb
        this.$emit('write-success');
      }

      if (failCount > 0) {
        console.warn("失败结果：", results.filter(r => r.status === 'rejected'));
        this.$message.error(`有 ${failCount} 条工单添加失败，请检查`);
      }
    }


    ,
    initData(data) {
      this.fqmxList = data;
    },
    getData() {
      return this.fqmxList;
    },
    /** 查询生产工单明细列表 */
    // getList() {
    //   this.loading = true;
    //   listScgdmx(this.queryParams).then(response => {
    //     this.scgdmxList = response.rows;
    //     this.total = response.total;
    //     this.loading = false;
    //   });
    // },
    // getList() {
    //   this.loading = true;
    //   listScgdmx(this.queryParams).then(async response => {
    //     const {rows = [], total = 0} = response;
    //
    //     const query = {
    //       workId: this.form.workId,      // 修改时可用
    //       workCode: this.form.workCode   // 新增或修改都可用
    //     };
    //     const resp = await listScgd(query);
    //     const rows1 = resp.rows || [];
    //     // 1. 原始数据
    //     this.scgdmxList = rows;
    //     this.total = total;
    //
    //     // 2. 提取工单号，确保是数组
    //     this.dialogQueryParams.workCodes = rows
    //       .map(item => item.workCode) // 取工单号
    //       .filter(code => !!code);    // 过滤掉 null / undefined / 空串
    //
    //     this.loading = false;
    //   });
    // },
    getList() {
      this.loading = true;

      listScgdmx(this.queryParams).then(async response => {
        const { rows = [], total = 0 } = response;
        console.log("listScgdmx 返回 rows:", rows);

        // 1. 提取 rows 中的工单号
        const workCodes = rows.map(r => r.workCode).filter(code => !!code);
        console.log("待查询工单号列表:", workCodes);

        if (workCodes.length === 0) {
          // 没有工单号，直接赋值
          this.scgdmxList = rows;
          this.total = total;
          this.dialogQueryParams.workCodes = [];
          this.loading = false;
          return;
        }

        try {
          // 2. 循环查询 listRz，每个工单号单独传参
          const rows1Map = new Map();
          for (const code of workCodes) {
            const query = { workCode: code, workorderCode: code };
            const resp = await listRz(query);
            const kpRows = resp.rows || [];
            if (kpRows.length > 0) {
              rows1Map.set(code, kpRows[0]); // 假设每个工单号只取第一条
            }
          }
          console.log("listRz 按工单号查询结果 rows1Map:", rows1Map);

          // 3. 遍历 rows，根据工单号补充 rows1 字段
          const combinedRows = rows.map(r => {
            const r1 = rows1Map.get(r.workCode);
            if (r1) {
              Object.keys(r1).forEach(key => {
                if (r[key] == null) { // null 或 undefined 补充
                  r[key] = r1[key];
                }
              });
            }
            return r;
          });

          console.log("合并后 combinedRows:", combinedRows);

          // 4. 更新列表和总数
          this.scgdmxList = combinedRows;
          this.total = combinedRows.length;

          // 5. 更新工单号列表
          this.dialogQueryParams.workCodes = combinedRows
            .map(item => item.workCode)
            .filter(code => !!code);

        } catch (err) {
          console.error("listRz 查询异常:", err);
          this.scgdmxList = rows; // 回退到原始 rows
          this.total = total;
          this.dialogQueryParams.workCodes = workCodes;
        } finally {
          this.loading = false;
        }

      }).catch(err => {
        console.error("listScgdmx 查询异常:", err);
        this.scgdmxList = [];
        this.total = 0;
        this.dialogQueryParams.workCodes = [];
        this.loading = false;
      });
    }




    ,
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
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
      this.ids = selection.map(item => item.scgdmxId)
      // console.log("ids = " + this.ids);
      // this.workCodes = selection.map(item => item.workCode)
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
            addScgdmx(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    // handleDelete(row) {
    //   const workIds = row.workId || this.ids;
    //   this.$modal.confirm('是否确认删除生产工单明细编号为"' + workIds + '"的数据项？').then(function() {
    //     return delScgdmx(workIds);
    //   }).then(() => {
    //     this.getList();
    //     this.$modal.msgSuccess("删除成功");
    //   }).catch(() => {});
    // },
    async handleDelete(row) {
      const selectedIds = this.ids || [];
      if (!selectedIds.length) {
        this.$message.warning('请先选择要操作的工单明细');
        return;
      }
      // console.log("selectedIds =", selectedIds);

      const localRows = this.scgdmxList.filter(item => selectedIds.includes(item.scgdmxId));
      // console.log('[handleDelete] localRows sample=', localRows);

      const deletableIds = [];
      const blockedMsgs = [];

      // --- 校验系统工序 ---
      for (const localRow of localRows) {
        const { workCode, thisProcess: localProcess, scgdmxId } = localRow;

        try {
          const res = await listScgd({ workCode });
          const serverRows = Array.isArray(res?.rows) ? res.rows : [];

          if (!serverRows.length) {
            blockedMsgs.push(`工单 ${workCode} 在系统中未找到`);
            continue;
          }

          const matchedServer = serverRows.find(sr => sr.workCode === workCode);
          if (!matchedServer) {
            blockedMsgs.push(`工单 ${workCode} 在系统中未找到对应记录`);
            continue;
          }

          const serverProcess = matchedServer.thisProcess || '';
          const dqgx = this.dict.type.sys_cys_gx.find(option => option.value === serverProcess) || { label: 'defaultLH' };
          const sjgx = this.dict.type.sys_cys_gx.find(option => option.value === localProcess) || { label: 'defaultLH' };
          if (serverProcess === localProcess) {
            deletableIds.push(scgdmxId);
          } else {
            blockedMsgs.push(`工单 ${workCode} 工序不一致（当前:${dqgx.label}，实际:${sjgx.label}）`);
          }

        } catch (e) {
          // console.error(`[handleDelete] 查询工单 ${workCode} 失败`, e);
          blockedMsgs.push(`工单 ${workCode} 校验失败`);
        }
      }

      // console.log('[handleDelete] 可删除 scgdmxId=', deletableIds);
      // console.log('[handleDelete] 不可删除信息=', blockedMsgs);

      if (blockedMsgs.length) {
        this.$modal.msgWarning('部分工单无法删除：\n' + blockedMsgs.join('\n'));
      }

      if (!deletableIds.length) return;

      this.$modal.confirm(`是否确认删除可删除的 ${deletableIds.length} 条工单明细？`)
        .then(async () => {
          // 删除明细
          await delScgdmx(deletableIds);
          this.getList();
          this.$modal.msgSuccess(`已删除 ${deletableIds.length} 条数据`);

          const mainDataRes = await listCpfp({
            paId: this.dialogQueryParams.paId,
            workorderCode: this.dialogQueryParams.orderId,
          });

          // --- 获取当前主表数量 ---
          const mainRow = Array.isArray(mainDataRes?.rows) && mainDataRes.rows.length > 0
            ? mainDataRes.rows[0]
            : null;

          const currentAllocated = mainRow?.allocatedCount || 0;
          const currentProgress = mainRow?.progressCount || 0;

          console.log("mainRow = ", mainRow);
          console.log("currentAllocated = ", currentAllocated);
          console.log("currentProgress = ", currentProgress);

          // --- 更新主表分配数量 ---
          const updateCount = {
            paId : this.dialogQueryParams.paId,
            workorderCode : this.dialogQueryParams.orderId,
            allocatedCount: currentAllocated - deletableIds.length,
            progressCount: currentProgress - deletableIds.length
          };
          await updateCpfp(updateCount);

          // 通知父组件刷新
          this.$emit('write-success');
        })
        .catch(() => {});
    },

    handleEdgeChange(row) {
      const payload = {
        scgdmxId: row.scgdmxId,                 // 表主键
        workCode: row.workCode,
        nextProcess: row.nextProcess
      };
      listScgd(payload).then(response => {
        const scgd = response.rows[0];
        if(scgd.thisProcess === '12'){
          // 调用更新接口
          updateScgdmx(payload).then(() => {
            this.$message.success("下道工序已保存");
          }).catch(err => {
            this.$message.error("保存失败，请重试");
          });
        }else{
          this.$message.error("工单已进入下一工序！");
        }
      });

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

