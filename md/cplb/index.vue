<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="成品列表编码" prop="cplbCode">-->
<!--        <el-input-->
<!--          v-model="queryParams.cplbCode"-->
<!--          placeholder="请输入成品列表编码"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="生产工单编码" label-width="100px" prop="workorderCode">
        <el-input
          v-model="queryParams.workorderCode"
          placeholder="请输入生产工单编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="牌号" prop="brand">
        <el-input
          v-model="queryParams.brand"
          placeholder="请输入牌号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批次号" prop="batchNumber">
        <el-input
          v-model="queryParams.batchNumber"
          placeholder="请输入批次号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品状态" prop="productStatus">
        <el-select v-model="queryParams.productStatus" placeholder="请选择产品状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_cp_cpzt"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="产品厚度" prop="productThickness">
        <el-input
          v-model="queryParams.productThickness"
          placeholder="请输入产品厚度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品宽度" prop="productWidth">
        <el-input
          v-model="queryParams.productWidth"
          placeholder="请输入产品宽度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品重量" prop="weight">
        <el-input
          v-model="queryParams.weight"
          placeholder="请输入产品重量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="抗拉强度" prop="tensileStrength">
        <el-input
          v-model="queryParams.tensileStrength"
          placeholder="请输入抗拉强度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="延伸率" prop="elongation">
        <el-input
          v-model="queryParams.elongation"
          placeholder="请输入延伸率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="硬度" prop="hardness">
        <el-input
          v-model="queryParams.hardness"
          placeholder="请输入硬度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="杯凸值" prop="cupConvexValue">
        <el-input
          v-model="queryParams.cupConvexValue"
          placeholder="请输入杯凸值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="入库状态" prop="warehouseStatus">
        <el-select v-model="queryParams.warehouseStatus" placeholder="请选择入库状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_cys_rkzt"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="员工姓名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入员工姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['md:cplb:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['md:cplb:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['md:cplb:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['md:cplb:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cplbList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="入库状态" width="90" align="center" prop="warehouseStatus">-->
<!--      <template slot-scope="scope">-->
<!--        <dict-tag :options="dict.type.sys_cys_rkzt" :value="scope.row.warehouseStatus"/>-->
<!--      </template>-->
<!--      </el-table-column>-->
      <el-table-column label="生产工单" width="200" align="center" prop="workorderCode" />
      <el-table-column label="牌号" align="center" prop="brand">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_rz_ph" :value="scope.row.brand"/>
        </template>
      </el-table-column>
      <el-table-column label="批次号" width="200" align="center" prop="batchNumber">
        <template slot-scope="scope">
          <!-- 使用自定义组件 BatchTag -->
          <BatchTag :batchNumber="scope.row.batchNumber" />
        </template>
      </el-table-column>
      <el-table-column label="产品状态" align="center" prop="productStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_cp_cpzt" :value="scope.row.productStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="产品厚度" align="center" prop="productThickness" />
      <el-table-column label="产品宽度" align="center" prop="productWidth" />
      <el-table-column label="产品重量" align="center" prop="weight" />
      <el-table-column label="抗拉强度" align="center" prop="tensileStrength" />
      <el-table-column label="延伸率" align="center" prop="elongation" />
      <el-table-column label="硬度" align="center" prop="hardness" />
      <el-table-column label="杯凸值" align="center" prop="cupConvexValue" />
      <el-table-column label="导电率" align="center" prop="electricalConductivity" />
      <el-table-column label="员工姓名" align="center" prop="userName" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['md:cplb:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['md:cplb:remove']"
          >删除</el-button>
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

    <!-- 添加或修改成品列对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="成品列表编码" prop="cplbCode">
          <el-input v-model="form.cplbCode" placeholder="请输入成品列表编码" />
        </el-form-item>
        <el-form-item label="生产工单id" prop="workorderId">
          <el-input v-model="form.workorderId" placeholder="请输入生产工单id" />
        </el-form-item>
        <el-form-item label="生产工单编码" prop="workorderCode">
          <el-input v-model="form.workorderCode" placeholder="请输入生产工单编码" />
        </el-form-item>
        <el-form-item label="牌号" prop="brand" disabled>
          <el-input v-model="form.brand" placeholder="请输入牌号" />
        </el-form-item>
        <el-form-item label="批次号" prop="batchNumber">
          <el-input v-model="form.batchNumber" placeholder="请输入批次号" />
        </el-form-item>
        <el-form-item label="产品状态">
          <el-radio-group v-model="form.productStatus">
            <el-radio
              v-for="dict in dict.type.sys_cp_cpzt"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="产品厚度" prop="productThickness">
          <el-input v-model="form.productThickness" placeholder="请输入产品厚度" />
        </el-form-item>
        <el-form-item label="产品宽度" prop="productWidth">
          <el-input v-model="form.productWidth" placeholder="请输入产品宽度" />
        </el-form-item>
        <el-form-item label="产品重量" prop="weight">
          <el-input v-model="form.weight" placeholder="请输入产品重量" />
        </el-form-item>
        <el-form-item label="抗拉强度" prop="tensileStrength">
          <el-input v-model="form.tensileStrength" placeholder="请输入抗拉强度" />
        </el-form-item>
        <el-form-item label="延伸率" prop="elongation">
          <el-input v-model="form.elongation" placeholder="请输入延伸率" />
        </el-form-item>
        <el-form-item label="硬度" prop="hardness">
          <el-input v-model="form.hardness" placeholder="请输入硬度" />
        </el-form-item>
        <el-form-item label="杯凸值" prop="cupConvexValue">
          <el-input v-model="form.cupConvexValue" placeholder="请输入杯凸值" />
        </el-form-item>
        <el-form-item label="入库状态">
          <el-radio-group v-model="form.warehouseStatus">
            <el-radio
              v-for="dict in dict.type.sys_cys_rkzt"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="员工id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入员工id" />
        </el-form-item>
        <el-form-item label="员工姓名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入员工姓名" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCplb, getCplb, delCplb, addCplb, updateCplb } from "@/api/mes/md/cplb";

export default {
  name: "Cplb",
  dicts: ['sys_cp_cpzt', 'sys_cys_rkzt', 'sys_rz_ph'],
  data() {
    return {
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
      // 成品列表格数据
      cplbList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cplbCode: null,        workorderId: null,        workorderCode: null,        brand: null,        batchNumber: null,        productStatus: null,        productThickness: null,        productWidth: null,        weight: null,        tensileStrength: null,        elongation: null,        hardness: null,        cupConvexValue: null,        warehouseStatus: null,        userId: null,        userName: null,      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        productThickness: [
          { required: true, message: "产品厚度不能为空", trigger: "blur" }
        ],        productWidth: [
          { required: true, message: "产品宽度不能为空", trigger: "blur" }
        ],        weight: [
          { required: true, message: "产品重量不能为空", trigger: "blur" }
        ],        tensileStrength: [
          { required: true, message: "抗拉强度不能为空", trigger: "blur" }
        ],        elongation: [
          { required: true, message: "延伸率不能为空", trigger: "blur" }
        ],        hardness: [
          { required: true, message: "硬度不能为空", trigger: "blur" }
        ],        cupConvexValue: [
          { required: true, message: "杯凸值不能为空", trigger: "blur" }
        ],
        qualityJudgment: [
          { required: true, message: "质量判定不能为空", trigger: "blur" }
        ]      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询成品列列表 */
    getList() {
      this.loading = true;
      listCplb(this.queryParams).then(response => {
        this.cplbList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        cplbId: null,        cplbCode: null,        workorderId: null,        workorderCode: null,        brand: null,        batchNumber: null,        productStatus: "0",        productThickness: null,        productWidth: null,        weight: null,        tensileStrength: null,        elongation: null,        hardness: null,        cupConvexValue: null,        electricalConductivity: null,        warehouseStatus: "0",        userId: null,        userName: null,        remark: null,        createBy: null,        createTime: null,        updateBy: null,        updateTime: null      };
      this.resetForm("form");
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
      this.ids = selection.map(item => item.cplbId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加成品列";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cplbId = row.cplbId || this.ids
      getCplb(cplbId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改成品列";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.cplbId != null) {
            updateCplb(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCplb(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const cplbIds = row.cplbId || this.ids;
      this.$modal.confirm('是否确认删除成品列编号为"' + cplbIds + '"的数据项？').then(function() {
        return delCplb(cplbIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('md/cplb/export', {
        ...this.queryParams
      }, `cplb_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
