<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="生产订单" prop="workorderCode">
        <el-input
          v-model="queryParams.workorderCode"
          placeholder="请输入生产订单编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="牌号" prop="brand">
        <el-select v-model="queryParams.brand" placeholder="请选择牌号" clearable>
          <el-option
            v-for="dict in dict.type.sys_rz_ph"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="批次号" prop="batchNumber">
        <el-input
          v-model="queryParams.batchNumber"
          placeholder="请输入批次号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工单类型" prop="workorderType">
        <el-select v-model="queryParams.workorderType" placeholder="请选择工单类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_cys_gdlx"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['md:cpfp:add']"
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
          v-hasPermi="['md:cpfp:edit']"
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
          v-hasPermi="['md:cpfp:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['md:cpfp:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="modifiedRzList" @selection-change="handleSelectionChange" @row-click="handleRowClick"   highlight-current-row
              :current-row="currentRow" @current-change="handleCurrentChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="操作" width="120" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['md:cpfp:edit']"
            v-if = "scope.row.showButtongd"
          >编辑</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-circle-check"
            @click="orderCompleted(scope.row)"
            v-hasPermi="['md:cpfp:remove']"
            v-if = "scope.row.showButtongd"
          >完工</el-button>
        </template>
      </el-table-column>
      <!--      <el-table-column label="分配编码" width="150" align="center" prop="paCode" />-->
      <el-table-column label="生产订单编码" width="150" align="center" prop="workorderCode" />
      <el-table-column label="牌号" align="center" prop="brand">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_rz_ph" :value="scope.row.brand"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="批次号" width="200" align="center" prop="batchNumber">-->
<!--        <template slot-scope="scope">-->
<!--          &lt;!&ndash; 使用自定义组件 BatchTag &ndash;&gt;-->
<!--          <BatchTag :batchNumber="scope.row.batchNumber" />-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="重量" align="center" prop="weight" />-->
<!--      <el-table-column label="开坯厚度" align="center" prop="blankThickness" />-->
<!--      <el-table-column label="开坯宽度" align="center" prop="blankWidth" />-->
      <el-table-column label="产品厚度" align="center" prop="productThickness" />
      <el-table-column label="产品宽度" align="center" prop="productWidth" />
      <el-table-column label="产品状态" align="center" prop="productStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_cp_cpzt" :value="scope.row.productStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="公差要求" align="center">
        <template slot-scope="scope">
          <span style="display: inline-block;">
            <dict-tag :options="dict.type.sys_cys_gc" :value="scope.row.toleranceType" />
          </span>
          <span> {{ scope.row.toleranceCount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="抗压强度" align="center" prop="tensileStrength"/>
      <el-table-column label="硬度" align="center" prop="hardness"/>
      <el-table-column label="产品物料编码" width="150" align="center" prop="materialCode" />
      <el-table-column label="产品物料名称" width="150" align="center" prop="materialName" />
      <el-table-column label="需求数量" width="100" align="center" prop="needCount" />
      <el-table-column label="已分配数量" width="150" align="center" prop="allocatedCount" />
      <el-table-column label="已完成数量" width="150" align="center" prop="completedCount" />
      <el-table-column label="执行中数量" width="150" align="center" prop="progressCount" />
      <el-table-column label="报废/改制数量" width="150" align="center" prop="sorrCount" />
      <el-table-column label="生产订单状态" width="150" align="center" prop="paStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_cys_scddzt" :value="scope.row.paStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="工单类型" align="center" prop="workorderType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_cys_gdlx" :value="scope.row.workorderType"/>
        </template>
      </el-table-column>
      <el-table-column label="操作员" align="center" prop="userName" />
      <el-table-column label="备注" width="200" align="center" prop="remark" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
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

    <!-- 添加或修改产品分配对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
                    <el-col :span="8">
                  <el-form-item label="生产订单" prop="workorderCode">
                    <el-input v-model="form.workorderCode" placeholder="请输入生产订单编码" />
                  </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item  label-width="50">
                        <el-switch v-model="autoGenFlag"
                                   active-color="#13ce66"
                                   active-text="自动生成"
                                   @change="handleAutoGenChange(autoGenFlag)" v-if="optType != 'view' && form.buttonstatus =='PREPARE'">
                        </el-switch>
                      </el-form-item>
                    </el-col>
          <el-col :span="8">
<!--            <el-form-item label="生产订单" prop="workorderCode">-->
<!--              <el-input v-model="form.workorderCode" placeholder="请输入生产订单编码">-->
<!--                &lt;!&ndash;                <el-button slot="append" @click="handleWorkorderSelect" icon="el-icon-search"></el-button>&ndash;&gt;-->
<!--              </el-input>-->
<!--            </el-form-item>-->
            <WorkorderSelect :filterCondition="filterCondition" ref="woSelect" @onSelected="onWorkorderSelected"></WorkorderSelect>
          </el-col>
          <el-col :span="8">
            <el-form-item label="牌号" prop="brand">
              <el-select v-model="form.brand" placeholder="请选择牌号">
                <el-option
                  v-for="dict in dict.type.sys_rz_ph"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
<!--          <el-col :span="8">-->
<!--            <el-form-item label="重量" prop="weight">-->
<!--              <el-input-number :min="0" v-model="form.weight" placeholder="请输入重量" />-->
<!--              <el-span>&nbsp;Kg</el-span>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="开坯厚度" prop="blankThickness">-->
<!--              <el-input-number :min="0.00" v-model="form.blankThickness" placeholder="请输入开坯厚度" />-->
<!--              <el-span>&nbsp;mm</el-span>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item label="开坯宽度" prop="blankWidth">-->
<!--              <el-input-number :min="0.00" v-model="form.blankWidth" placeholder="请输入开坯宽度" />-->
<!--              <el-span>&nbsp;mm</el-span>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
          <el-col :span="8">
            <el-form-item label="产品厚度" prop="productThickness">
              <el-input-number :min="0.00" v-model="form.productThickness" placeholder="请输入产品厚度" />
              <el-span>&nbsp;mm</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品宽度" prop="productWidth">
              <el-input-number :min="0.00" v-model="form.productWidth" placeholder="请输入产品宽度" />
              <el-span>&nbsp;mm</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品状态">
              <el-select v-model="form.productStatus" placeholder="请选择产品状态" @change="handleInput">
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
          <el-col :span="3.5">
            <el-form-item label="公差要求">
              <el-select v-model="form.toleranceType" placeholder="" style="width: 70px;">
                <el-option
                  v-for="dict in dict.type.sys_cys_gc"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-input-number :min="0.00" v-model="form.toleranceCount" placeholder="请输入公差值" style="width: 200px;"/>
            <el-span>&nbsp;mm</el-span>
          </el-col>
          <el-col :span="7">
            <el-form-item label="抗拉强度" prop="tensileStrength">
              <el-input v-model="form.tensileStrength" placeholder="请输入抗拉强度范围"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="硬度" prop="hardness">
              <el-input v-model="form.hardness" placeholder="请输入硬度范围"/>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label-width="110px" label="产品物料编码" prop="materialCode">
              <el-input v-model="form.materialCode" placeholder="请输入产品物料编码"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label-width="135px" label="产品物料名称" prop="materialName">
              <el-input v-model="form.materialName" placeholder="请输入产品物料名称" />
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label-width="100px" label="需求数量" prop="needCount">
            <el-input-number :min="0" v-model="form.needCount" placeholder="请输入需求数量" style="width: 200px;"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="工单类型" prop="workorderType">
              <el-select v-model="form.workorderType" placeholder="请选择工单类型">
                <el-option
                  v-for="dict in dict.type.sys_cys_gdlx"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
<!--          <el-col :span="5">-->
<!--            <el-form-item label="分配数量" prop="allocatedCount">-->
<!--            <el-input-number :min="0" v-model="form.allocatedCount" placeholder="请输入已分配数量" style="width: 200px;" disabled/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="5">-->
<!--            <el-form-item label="已完成数量" prop="completedCount">-->
<!--            <el-input-number :min="0" v-model="form.completedCount" placeholder="请输入已完成数量" style="width: 200px; " disabled/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="5">-->
<!--            <el-form-item label="执行中数量" prop="progressCount">-->
<!--            <el-input-number :min="0" v-model="form.progressCount" placeholder="请输入执行中数量" style="width: 200px;" disabled/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="5">-->
<!--            <el-form-item label="报废/改制数量" prop="sorrCount">-->
<!--            <el-input-number :min="0" v-model="form.sorrCount" placeholder="请输入报废/改制数量" style="width: 200px;" disabled/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
          <el-col :span="8">
            <el-form-item label-width="80px" label="操作员" prop="userName">
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
<!--      <el-tabs type="border-card">-->
<!--        <el-tab-pane label="生产工单列表">-->
<!--          <scgdListbom ref="bomlist" :workorder="scgdForm" ></scgdListbom>-->
<!--        </el-tab-pane>-->
<!--      </el-tabs>-->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" >提 交</el-button>
<!--        <el-button type="primary" @click="submitForm" v-if="!form.showSave">保 存</el-button>-->
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCpfp, getCpfp, delCpfp, addCpfp, updateCpfp } from "@/api/mes/md/cpfp";
import WorkorderSelect from "@/components/scgd/single.vue";
import WorkstationSelect from "@/components/workstationSelect/simpletableSingle.vue";
import UserSingleSelect from "@/components/userSelect/single.vue";
import ProtaskSelect from "@/components/TaskSelect/taskSelectSingle.vue";
import {genCode} from "@/api/system/autocode/rule";
import {addTh, listTh} from "@/api/mes/md/th";
import {listScgd, updateScgd} from "@/api/mes/md/scgd";
import {listSx} from "@/api/mes/md/sx";
import {listCqp} from "@/api/mes/md/cqp";
import {listKp} from "@/api/mes/md/kp";
import scgdListbom from "@/views/mes/md/cpfpNew/cyscpfpmx/index.vue";
import {updateScgdmx, addScgdmx, listScgdmx } from "@/api/mes/md/scgdmx";

export default {
  name: "Cpfp",
  components: {WorkorderSelect,WorkstationSelect,UserSingleSelect,ProtaskSelect, scgdListbom},
  dicts: ['sys_rz_ph', 'sys_cp_cpzt', 'sys_cys_gx', 'sys_cys_gc', 'sys_cys_scddzt', 'sys_cys_gdlx'],
  data() {
    return {
      // 当前选中行
      currentRow: null,
      //生产工单列表
      scgdForm: [],
      // 筛选条件，假设是根据工单状态进行筛选
      filterCondition: {
        gdType: '6',  // 比如筛选状态为 '6-产品分配' 的工单
      },
      //sx
      autoGenFlag:false,
      optType: undefined,
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
      // 产品分配表格数据
      cpfpList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        paId: null ,    paCode: null, paStatus: null,workorderType: null,  needCount:null,   allocatedCount:null,   completedCount:null,   progressCount:null,   sorrCount:null,     workorderId: null,   tensileStrengthStart: null,  tensileStrengthEnd: null,   hardnessStart: null,     hardnessEnd: null,       workorderCode: null,        brand: null,        batchNumber: null,        weight: null,        blankThickness: null,        blankWidth: null,        productThickness: null,        productWidth: null,        productStatus: null,        materialCode: null,        materialName: null,        userId: null,        userName: null,  toleranceType: null, toleranceCount: null, tensileStrength: null,hardness: null,    },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        weight: [
          { required: true, message: "重量不能为空", trigger: "blur" }
        ],        productThickness: [
          { required: true, message: "产品厚度不能为空", trigger: "blur" }
        ],        productWidth: [
          { required: true, message: "产品宽度不能为空", trigger: "blur" }
        ],      }
    };
  },
  created() {
    this.getList();
  },
  computed: {
    modifiedRzList() {
      return this.cpfpList.map(item => {
        return {
          ...item,
          showButton: item.productStatus != '' || item.productStatus != null , // 根据 productStatus 来设置 showButton 字段
          showButtongd : item.paStatus == '0'  // 根据 paStatus 来设置 showButton 字段
        };
      });
    }
  },
  methods: {

    handleCurrentChange(row) {
      this.currentRow = row;
    },
    handleRowClick(row) {
      this.currentRow = row; // 选中行更新
      this.$emit('row-click', row); // ✅ 发射自定义事件给父组件
    },
    //值变化监听
    handleInput(event) {
      // console.log('输入值变化了:', this.form.furnaceHeat);
      // console.log('输入值变化了:', this.form.furnaceNo);
      // 在这里添加你的逻辑
      this.form.showSave = this.form.productStatus != null
    },
    clearData() {
      this.fqmxList = [];
      this.ids = [];
      this.list = [];
    },
    async cysfuntest(){
      const testPayload = {
        workId: "CB2025042700001",
        workStatus: "1"
      };

      updateScgd(testPayload).then(res => {
        console.log("✅ 工单状态更新成功：", res);
      }).catch(err => {
        console.error("❌ 工单状态更新失败：", err);
      });
    },

    /** 查询产品分配列表 */
    getList() {
      this.loading = true;
      listCpfp(this.queryParams).then(response => {
        this.cpfpList = response.rows;
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
        paId: null,
        paCode: null,
        workorderId: null,
        workorderCode: null,
        brand: null,
        batchNumber: null,
        productStatus: null,
        paStatus: null,
        needCount:null,
        allocatedCount:null,
        completedCount:null,
        progressCount:null,
        sorrCount:null,
        weight: null,
        blankThickness: null,
        blankWidth: null,
        productThickness: null,
        productWidth: null,
        materialCode: null,
        materialName: null,
        toleranceType: null,
        toleranceCount: null,
        tensileStrength: null,
        tensileStrengthStart: null,
        tensileStrengthEnd: null,
        hardnessStart: null,
        hardnessEnd: null,
        hardness: null,
        userId: this.$store.state.user.userId,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        buttonstatus: "PREPARE",
        userName: this.$store.state.user.name
      };
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
      this.ids = selection.map(item => item.paId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加产品分配";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const paId = row.paId || this.ids
      getCpfp(paId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改产品分配";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.paId != null) {
            updateCpfp(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.paStatus = "0";
            addCpfp(this.form).then(response => {
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
    //   const paIds = row.paId || this.ids;
    //   this.$modal.confirm('是否确认删除产品分配编号为"' + paIds + '"的数据项？').then(function() {
    //     return delCpfp(paIds);
    //   }).then(() => {
    //     this.getList();
    //     this.$modal.msgSuccess("删除成功");
    //   }).catch(() => {});
    // }
    handleDelete(row) {
      let paIds = [];

      if (row && row.paId !== undefined && row.paId !== null) {
        // 单条删除，且paId有效
        paIds = [row.paId];
      } else if (this.ids && this.ids.length) {
        // 批量删除
        paIds = this.ids;
      } else {
        this.$message.warning("请先选择要删除的项");
        return;
      }

      console.log('准备删除的 paIds:', paIds);

      // 检查是否有 undefined 或 null 的 id，提前阻止
      if (paIds.some(id => id === undefined || id === null)) {
        console.error('存在未定义或空的 paId，禁止删除！', paIds);
        this.$modal.msgError('存在未定义或空的产品分配编号，删除失败！');
        return;
      }

      // 查询选中paId对应的工单编码
      const workorderCodes = this.cpfpList
        .filter(item => paIds.includes(item.paId))
        .map(item => item.workorderCode);

      console.log('对应的工单编码:', workorderCodes);

      // 校验是否存在子表数据，禁止删除
      const checkPromises = workorderCodes.map(code => listScgdmx({ porderCode: code }));

      Promise.all(checkPromises)
        .then(results => {
          const blocked = [];

          results.forEach((res, index) => {
            if (res && res.rows && res.rows.length > 0) {
              blocked.push(workorderCodes[index]);
            }
          });

          if (blocked.length > 0) {
            this.$modal.msgWarning(
              `工单号【${blocked.join("，")}】在生产工单明细中存在数据，禁止删除！`
            );
          }

          // 过滤出允许删除的 paId
          const allowedPaIds = paIds.filter(paId => {
            const code = this.cpfpList.find(item => item.paId === paId)?.workorderCode;
            return !blocked.includes(code);
          });

          console.log('允许删除的 paIds:', allowedPaIds);

          if (allowedPaIds.length === 0) {
            // 没有允许删除的，直接返回
            return;
          }

          // 弹确认框，确认删除允许删除的数据
          this.$modal.confirm(`确认删除未被禁止的 ${allowedPaIds.length} 条数据？`)
            .then(() => {
              // 逐条调用删除接口，避免传数组导致接口路径错误
              const deletePromises = allowedPaIds.map(id => delCpfp(id));
              return Promise.all(deletePromises);
            })
            .then(() => {
              this.getList();
              this.$modal.msgSuccess("删除成功");
            })
            .catch(err => {
              console.error('删除操作异常:', err);
              // 取消确认弹窗或删除异常均不提示错误，避免影响用户体验
            });
        })
        .catch(err => {
          console.error('校验子表数据异常:', err);
          this.$modal.msgError("校验子表数据失败：" + (err.message || "未知错误"));
        });
    },

    //完工确认 - 判断如果所属工单都在如果所属的生产工单全部属于成品检验阶段，则都算完工。
    orderCompleted(row) {
      const cysscgd = { porderCode: row.workorderCode };

      listScgdmx(cysscgd).then(response => {
        const workorders = response.rows || []; // 工单明细列表

        if (workorders.length === 0) {
          this.$modal.msgWarning("没有工单明细，无法完工");
          return;
        }

        // 遍历每个工单，调用 listScgd 获取工序
        const promises = workorders.map(item => {
          return listScgd({ workCode: item.workCode }) // 假设工单号是 workCode
            .then(res => {
              const processes = res.rows || [];
              // 判断该工单的所有工序是否都为 "10"
              const allOk = processes.every(proc => proc.thisProcess === "10");
              return allOk;
            });
        });

        // 等待所有工单的工序检查完成
        Promise.all(promises).then(results => {
          const allCompleted = results.every(flag => flag === true);

          if (allCompleted) {
            // 更新主表状态
            const payload = {
              paId: row.paId,
              paStatus: '1'
            };
            updateCpfp(payload).then(res => {
              this.$modal.msgSuccess("主表状态更新完成");
              this.getList();
            }).catch(err => {
              this.$modal.msgError("更新失败");
            });
          } else {
            this.$modal.msgWarning("还有未完成的工序，无法完工");
          }
        }).catch(err => {
          this.$modal.msgError("查询工单工序失败");
        });
      }).catch(error => {
        this.$modal.msgError("查询工单明细失败");
      });
    }
    ,

    //点击人员选择按钮
    handleUserSelect(){
      this.$refs.userSelect.showFlag = true;
    },
    //人员选择返回
    onUserSelected(row){
      this.form.userId = row.userId;
      this.form.userName = row.userName;
    },
    //选择生产工单
    handleWorkorderSelect(){
      this.$refs.woSelect.showFlag = true;
    },
    onWorkorderSelected(row){
      if(row != undefined && row != null){
        this.form.workorderId = row.workId;
        this.form.workorderCode = row.workCode;
        this.form.brand = row.brand;
        this.form.batchNumber = row.batchNumber;
      }
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('md/cpfp/export', {
        ...this.queryParams
      }, `cpfp_${new Date().getTime()}.xlsx`)
    },
    //自动生成编码
    handleAutoGenChange(autoGenFlag){
      if(autoGenFlag){
        genCode('CYS_DD_CODE').then(response =>{
          this.form.workorderCode = response;
        });
      }else{
        this.form.workorderCode = null;
      }
    }
  }
};
</script>
<style scoped>
/deep/ .el-select .el-input__inner {
  font-weight: bold; /* 选中后显示的文字加粗 */
}

/deep/ .el-select-dropdown__item {
  font-weight: bold; /* 下拉列表项加粗 */
}
/deep/ .centered-range .el-input__inner {
  text-align: center;
}
</style>
