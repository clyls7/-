<template>
  <div class="fq-table-wrapper">
    <!-- 按钮行 -->
    <div class="fq-header-bar">
      <el-button
        type="warning"
        plain
        icon="el-icon-download"
        size="mini"
        @click="handleExport"
        v-hasPermi="['md:fqmx:export']"
      >导出</el-button>
    </div>

    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="fqmxList"
      style="width: 100%"
      border
      stripe
      @selection-change="handleSelectionChange"
    >
<!--      <el-table-column label="牌号" align="center" prop="brand" />-->
      <el-table-column label="批次号" :min-width="200" align="center" prop="batchNumber">
        <template slot-scope="scope">
          <!-- 使用自定义组件 BatchTag -->
          <BatchTag :batchNumber="scope.row.batchNumber" />
        </template>
      </el-table-column>
      <el-table-column label="宽度" prop="width" :min-width="100" />
      <el-table-column label="重量" prop="weight" :min-width="100" />
      <el-table-column label="产品编码" prop="materialCode" :min-width="200" />
      <el-table-column label="产品名称" prop="materialName" :min-width="200" />
      <el-table-column label="员工姓名" prop="userName" :min-width="100" />
      <el-table-column label="备注" prop="remark" :min-width="150" />
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['md:fqmx:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['md:fqmx:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改分切明细对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="分切编码" prop="fqmxCode">
          <el-input v-model="form.fqmxCode" placeholder="请输入分切编码" />
        </el-form-item>
        <el-form-item label="生产工单id" prop="workorderId">
          <el-input v-model="form.workorderId" placeholder="请输入生产工单id" />
        </el-form-item>
        <el-form-item label="生产工单编码" prop="workorderCode">
          <el-input v-model="form.workorderCode" placeholder="请输入生产工单编码" />
        </el-form-item>
        <el-form-item label="牌号" prop="brand">
          <el-input v-model="form.brand" placeholder="请输入牌号" />
        </el-form-item>
        <el-form-item label="批次号" prop="batchNumber">
          <el-input v-model="form.batchNumber" placeholder="请输入批次号" />
        </el-form-item>
        <el-form-item label="分切父id" prop="cuttingParentid">
          <el-input v-model="form.cuttingParentid" placeholder="请输入分切父id" />
        </el-form-item>
        <el-form-item label="宽度" prop="width">
          <el-input v-model="form.width" placeholder="请输入宽度" />
        </el-form-item>
        <el-form-item label="重量" prop="weight">
          <el-input v-model="form.weight" placeholder="请输入重量" />
        </el-form-item>
        <el-form-item label="产品编码" prop="materialCode">
          <el-input v-model="form.materialCode" placeholder="请输入产品编码" />
        </el-form-item>
        <el-form-item label="产品名称" prop="materialName">
          <el-input v-model="form.materialName" placeholder="请输入产品名称" />
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
import { listFqmx, getFqmx, delFqmx, addFqmx, updateFqmx } from "@/api/mes/md/fqmx";

export default {
  name: "Fqmx",
  props: {
    workorder: {
      type: Array,
      default: () => []
    }
  },
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
      // 分切明细表格数据
      fqmxList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fqmxCode: null,        workorderId: null,        workorderCode: null,        brand: null,        batchNumber: null,        cuttingParentid: null,        width: null,        weight: null,        materialCode: null,        materialName: null,        userName: null,      },
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
    // 监听 workorder 的变化，自动更新 list
    workorder: {
      handler(newVal) {
        this.fqmxList = newVal;
      },
      immediate: true, // 初始值也能生效
      deep: true
    }
  },
  created() {
    this.getList();
  },
  methods: {
    initData(data) {
      this.fqmxList = data;
    },
    getData() {
      return this.fqmxList;
    },
    /** 查询分切明细列表 */
    getList() {
      this.loading = true;
      listFqmx(this.queryParams).then(response => {
        this.fqmxList = response.rows;
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
        fqmxId: null,        fqmxCode: null,        workorderId: null,        workorderCode: null,        brand: null,        batchNumber: null,        cuttingParentid: null,        width: null,        weight: null,        materialCode: null,        materialName: null,        userName: null,        remark: null,        createBy: null,        createTime: null,        updateBy: null,        updateTime: null      };
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
      this.ids = selection.map(item => item.fqmxId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加分切明细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fqmxId = row.fqmxId || this.ids
      getFqmx(fqmxId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改分切明细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.fqmxId != null) {
            updateFqmx(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFqmx(this.form).then(response => {
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
      const fqmxIds = row.fqmxId || this.ids;
      this.$modal.confirm('是否确认删除分切明细编号为"' + fqmxIds + '"的数据项？').then(function() {
        return delFqmx(fqmxIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('md/fqmx/export', {
        ...this.queryParams
      }, `fqmx_${new Date().getTime()}.xlsx`)
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

