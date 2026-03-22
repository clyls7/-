<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="分切编码" prop="fqCode">
        <el-input
          v-model="queryParams.fqCode"
          placeholder="请输入分切编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生产工单" prop="workorderCode">
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
      <el-form-item label="重量" prop="weight">
        <el-input
          v-model="queryParams.weight"
          placeholder="请输入重量"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
      <el-form-item label="产品状态" prop="productStatus">
        <el-select v-model="queryParams.productStatus" placeholder="请选择产品状态" clearable>
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="分切状态" prop="cuttingStatus">
        <el-select v-model="queryParams.cuttingStatus" placeholder="请选择分切状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_cys_fqzt"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="分切段数" prop="cuttingSections">
        <el-input
          v-model="queryParams.cuttingSections"
          placeholder="请输入分切段数"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['md:fq:add']"
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
          v-hasPermi="['md:fq:edit']"
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
          v-hasPermi="['md:fq:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['md:fq:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="modifiedRzList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" fixed="left"/>
      <el-table-column label="操作" width="150" align="center" class-name="small-padding" fixed="left">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-circle-check"
            @click="loadWorkorder(scope.row)"
            v-if = "scope.row.showButton"
          >分切</el-button>
        </template>
      </el-table-column>
      <el-table-column label="生产工单编码" width="150" align="center" prop="workorderCode" fixed="left"/>
      <el-table-column label="批次号" width="200" align="center" prop="batchNumber" fixed="left">
        <template slot-scope="scope">
          <!-- 使用自定义组件 BatchTag -->
          <BatchTag :batchNumber="scope.row.batchNumber" />
        </template>
      </el-table-column>
      <el-table-column label="分切状态" align="center" prop="cuttingStatus" fixed="left">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_cys_fqzt" :value="scope.row.cuttingStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="牌号" align="center" prop="brand" />
      <el-table-column label="重量" align="center" prop="weight" />
      <el-table-column label="产品厚度" align="center" prop="productThickness" />
      <el-table-column label="产品宽度" align="center" prop="productWidth" />
      <el-table-column label="产品状态" align="center" prop="productStatus" />
      <el-table-column label="分切段数" align="center" prop="cuttingSections" />
      <el-table-column label="员工姓名" align="center" prop="userName" />
      <el-table-column label="备注" width="200" align="center" prop="remark" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改产品分配对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="生产工单" prop="workorderCode">
              <el-input v-model="form.workorderCode" placeholder="请输入生产工单编码" @change="pdScgd">
                <!--                <el-button slot="append" @click="handleWorkorderSelect" icon="el-icon-search"></el-button>-->
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="牌号" prop="brand">
              <el-select v-model="form.brand" placeholder="请选择牌号" disabled>
                <el-option
                  v-for="dict in dict.type.sys_rz_ph"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="批次号" prop="batchNumber" disabled>
              <el-input v-model="form.batchNumber" placeholder="请输入批次号" disabled/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品厚度" prop="productThickness" disabled>
              <el-input-number :min="0.00" v-model="form.productThickness" placeholder="请输入产品厚度" />
              <el-span>&nbsp;mm</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品宽度" prop="productWidth" disabled>
              <el-input-number :min="0.00" v-model="form.productWidth" placeholder="请输入产品宽度" />
              <el-span>&nbsp;mm</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品状态">
              <el-select v-model="form.productStatus" placeholder="请选择产品状态" disabled>
                <el-option :label="''" :value="null"></el-option>
                <el-option
                  v-for="dict in dict.type.sys_cp_cpzt"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="分切段数" prop="cuttingSections">
              <el-input-number :min="0" :step="1" :precision="0" v-model="form.cuttingSections" placeholder="请输入分切段数" @change="generateFqList(form)"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="操作员" prop="userName">
              <el-input v-model="form.userName" placeholder="请选择操作员" >
                <el-button slot="append" @click="handleUserSelect" icon="el-icon-search"></el-button>
              </el-input>
            </el-form-item>
            <UserSingleSelect ref="userSelect" @onSelected="onUserSelected"></UserSingleSelect>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-tabs type="border-card">
        <el-tab-pane label="分切列表">
          <FqListbom ref="bomlist" :workorder="Fqform" ></FqListbom>
        </el-tab-pane>
      </el-tabs>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="" >分 切</el-button>
        <el-button type="primary" @click="submitForm" v-if="!form.showSave">保 存</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listFq, getFq, delFq, addFq, updateFq } from "@/api/mes/md/fq";
import {listScgd, updateScgd} from "@/api/mes/md/scgd";
import {listCpjy} from "@/api/mes/md/cpjy";
import {listCpfp} from "@/api/mes/md/cpfp";
import {addGz, listGz} from "@/api/mes/md/gz";
import {getFqmx} from "@/api/mes/md/fqmx";
import FqListbom from "@/views/mes/md/fq/fqmx/index.vue";

export default {
  name: "Fq",
  dicts: ['sys_cys_fqzt', 'sys_cp_cpzt', 'sys_rz_ph'],
  components: {
    FqListbom // 注册成组件
  },
  data() {
    return {
      //分切列表
      Fqform: {},
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
      // 分切表格数据
      fqList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fqCode: null,
        workorderId: null,
        workorderCode: null,
        brand: null,
        materialCode: null,
        materialName:null,
        batchNumber: null,
        weight: null,
        productThickness: null,
        productWidth: null,
        productStatus: null,
        cuttingStatus: null,
        cuttingSections: null,
        userId: null,
        userName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        weight: [
          {required: true, message: "重量不能为空", trigger: "blur"}
        ], productThickness: [
          {required: true, message: "产品厚度不能为空", trigger: "blur"}
        ],qualityJudgment: [
          { required: true, message: "质量判定不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  computed: {
    modifiedRzList() {
      return this.fqList.map(item => {
        return {
          ...item,
          showButton: item.cuttingStatus === '1'  // 根据 status 来设置 showButton 字段
        };
      });
    }
  },
  methods: {
    loadWorkorder(row) {
      this.open = true;
      const fqIds = row.fqId || this.ids;
      console.log("fqIds = " + fqIds);
      getFq(fqIds).then(res => {
        this.form = res.data;
        getFqmx(fqIds).then(fqList => {
          if (fqList && fqList.length > 0) {
            // 已分切，直接展示，不再允许分切
            this.Fqform = fqList;
            this.form.showSave = true; // 隐藏“保存/分切”按钮
          }
          this.$nextTick(() => {
            this.$refs.bomlist.initData(this.Fqform);
          });
        });
      });
    },
    async generateFqList(form) {
      const queryRz = {
        pageNum: 1,
        pageSize: 10,
        workCode: form.workorderCode,
        workorderCode: form.workorderCode
      }

      // 等待数据返回
      const response = await listCpfp(queryRz);
      const cpfp = response.rows[0];
      this.form.materialCode = cpfp.materialCode;
      this.form.materialName = cpfp.materialName;

      const {
        batchNumber,
        productWidth,
        productWeight,
        brand,
        materialCode,
        materialName,
        cuttingSections
      } = this.form; // ⚠️ 注意是 this.form，不再是参数 form，确保用的是最新值

      const width = productWidth / cuttingSections;
      const weight = productWeight / cuttingSections;
      const list = [];

      for (let i = 0; i < cuttingSections; i++) {
        list.push({
          batchNumber,
          width: parseFloat(width.toFixed(2)),
          weight: parseFloat(weight.toFixed(2)),
          materialCode,
          materialName
        });
      }

      console.log("最终生成明细：", list);
      this.Fqform = list; // 同步生成数据
    }
    ,
    /** 查询分切列表 */
    getList() {
      this.loading = true;
      listFq(this.queryParams).then(response => {
        this.fqList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    pdScgd(event){
      const queryRz = {
        pageNum: 1,
        pageSize: 10,
        workCode: this.form.workorderCode,
        workorderCode: this.form.workorderCode
      }
      listScgd(queryRz).then(response => {
        // console.log(response.rows[0]);
        const scgd = response.rows[0];
        if(response.rows.length === 0){
          this.$modal.msgError("不存在此工单，请重新扫描！");
          this.form.workorderCode = null;
        }else{
          if(scgd.thisProcess !== '13' && scgd.workStatus !== '3'){
            const gxjd = this.dict.type.sys_cys_gx.find(option => option.value === scgd.thisProcess) || { label: 'defaultLH' };
            this.$modal.msgError("该工单当前工序为：" + gxjd.label + " 请重新扫描");
            this.form.workorderCode = null;
          }else{
            this.form.workorderId = scgd.workId;
            this.form.workorderCode = scgd.workCode;
            this.form.brand = scgd.brand;
            this.form.batchNumber = scgd.batchNumber;
            listCpjy(queryRz).then(response => {
              const cpjy = response.rows[0];
              this.form.weight = cpjy.weightAfterCleaning;
              this.form.productWidth = cpjy.productWidth;
              this.form.productThickness = cpjy.productWidth;
            });
          }
        }
      });
    },

    // 表单重置
    reset() {
      this.form = {
        fqId: null,        fqCode: null, materialCode: null,  materialName:null,      workorderId: null,        workorderCode: null,        brand: null,        batchNumber: null,        weight: null,        productThickness: null,        productWidth: null,        productStatus: "0",        cuttingStatus: "1",        cuttingSections: null,        userId: null,        userName: null,        remark: null,        createBy: null,        createTime: null,        updateBy: null,        updateTime: null      };
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
    //点击人员选择按钮
    handleUserSelect(){
      this.$refs.userSelect.showFlag = true;
    },
    //人员选择返回
    onUserSelected(row){
      this.form.userId = row.userId;
      this.form.userName = row.userName;
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.fqId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加分切";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fqId = row.fqId || this.ids
      getFq(fqId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改分切";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.fqId != null) {
            updateFq(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFq(this.form).then(response => {
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
      const fqIds = row.fqId || this.ids;
      this.$modal.confirm('是否确认删除分切编号为"' + fqIds + '"的数据项？').then(function() {
        return delFq(fqIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('md/fq/export', {
        ...this.queryParams
      }, `fq_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
