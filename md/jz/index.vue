<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!--      <el-form-item label="工单编码" prop="finishrCode">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.finishrCode"-->
      <!--          placeholder="请输入精轧工序编码"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="生产工单" prop="workorderCode">
        <el-input
          v-model="queryParams.workorderCode"
          placeholder="请输入生产工单编码"
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
      <el-form-item label="精轧次数" prop="finishrollCount">
        <el-input
          v-model="queryParams.finishrollCount"
          placeholder="请输入精轧次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="精轧设备" prop="finishrollEquipment">
        <el-select v-model="queryParams.finishrollEquipment" placeholder="请选择精轧设备" clearable>
          <el-option
            v-for="dict in dict.type.sys_jz_jzsb"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="轧前重量" prop="weightBeforeRoll">
        <el-input
          v-model="queryParams.weightBeforeRoll"
          placeholder="请输入轧前重量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="轧后重量" prop="weightAfterRoll">
        <el-input
          v-model="queryParams.weightAfterRoll"
          placeholder="请输入轧后重量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="轧前规格" prop="speBeforeRoll">
        <el-input
          v-model="queryParams.speBeforeRoll"
          placeholder="请输入轧前规格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="轧后规格" prop="speAfterRoll">
        <el-input
          v-model="queryParams.speAfterRoll"
          placeholder="请输入轧后规格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="精轧时间" prop="finishrTime">
        <el-date-picker clearable
                        v-model="queryParams.finishrTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择精轧时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="员工姓名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入员工姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="质量判定" prop="qualityJudgment">
        <el-select v-model="queryParams.qualityJudgment" placeholder="请选择质量判定" clearable>
          <el-option
            v-for="dict in dict.type.sys_cpjy_zlpd"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="异常原因" prop="abnormalCause">
        <el-input
          v-model="queryParams.abnormalCause"
          placeholder="请输入异常原因"
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
          v-hasPermi="['md:jz:add']"
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
          v-hasPermi="['md:jz:edit']"
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
          v-hasPermi="['md:jz:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['md:jz:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="modifiedJZList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="工单编码" width="150" align="center" prop="finishrCode" />-->
      <el-table-column label="生产工单编码" width="150" align="center" prop="workorderCode" />
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
      <el-table-column label="精轧次数" align="center" prop="finishrollCount" />
      <el-table-column label="精轧设备" align="center" prop="finishrollEquipment">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_jz_jzsb" :value="scope.row.finishrollEquipment"/>
        </template>
      </el-table-column>
      <el-table-column label="轧前重量" align="center" prop="weightBeforeRoll" />
      <el-table-column label="轧后重量" align="center" prop="weightAfterRoll" />
      <el-table-column label="轧前规格" align="center" prop="speBeforeRoll" />
      <el-table-column label="轧后规格" align="center" prop="speAfterRoll" />
      <el-table-column label="精轧时间" align="center" prop="finishrTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.finishrTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="员工姓名" align="center" prop="userName" />
      <el-table-column label="质量判定" align="center" prop="qualityJudgment">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_cpjy_zlpd" :value="scope.row.qualityJudgment"/>
        </template>
      </el-table-column>
      <el-table-column label="异常原因" width="150" align="center" prop="abnormalCause" />
      <el-table-column label="备注" width="200" align="center" prop="remark" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="120" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['md:jz:edit']"
            v-if = "scope.row.showButton"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['md:jz:remove']"
            v-if = "scope.row.showButton"
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

    <!-- 添加或修改精轧对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1080px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <!--          <el-col :span="8">-->
          <!--        <el-form-item label="工单编码" prop="finishrCode">-->
          <!--          <el-input v-model="form.finishrCode" placeholder="请输入精轧工序编码" />-->
          <!--        </el-form-item>-->
          <!--          </el-col>-->
          <!--          <el-col :span="8">-->
          <!--          <el-form-item  label-width="80">-->
          <!--            <el-switch v-model="autoGenFlag"-->
          <!--                       active-color="#13ce66"-->
          <!--                       active-text="自动生成"-->
          <!--                       @change="handleAutoGenChange(autoGenFlag)" v-if="optType != 'view' && form.buttonstatus =='PREPARE'">-->
          <!--            </el-switch>-->
          <!--          </el-form-item>-->
          <!--          </el-col>-->
          <el-col :span="8">
            <el-form-item label="生产工单" prop="workorderCode">
              <el-input v-model="form.workorderCode" placeholder="请输入生产工单编码" @change="pdScgd">
                <!--                <el-button slot="append" @click="handleWorkorderSelect" icon="el-icon-search"></el-button>-->
              </el-input>
            </el-form-item>
            <WorkorderSelect :filterCondition="filterCondition" ref="woSelect" @onSelected="onWorkorderSelected"></WorkorderSelect>
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
            <el-form-item label="批次号" prop="batchNumber">
              <el-input v-model="form.batchNumber" placeholder="请输入批次号" disabled/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品状态" prop="productStatus">
              <el-select v-model="form.productStatus" placeholder="请选择产品状态">
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
            <el-form-item label="精轧次数" prop="finishrollCount">
              <el-input-number :min="1" v-model="form.finishrollCount" placeholder="请输入精轧次数" disabled/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="精轧设备" prop="finishrollEquipment">
              <el-select v-model="form.finishrollEquipment" placeholder="请选择精轧设备">
                <el-option
                  v-for="dict in dict.type.sys_jz_jzsb"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="轧前重量" prop="weightBeforeRoll">
              <el-input-number :min="0.00" v-model="form.weightBeforeRoll" placeholder="请输入轧前重量" />
              <el-span>&nbsp;Kg</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="轧后重量" prop="weightAfterRoll">
              <el-input-number :min="0.00" v-model="form.weightAfterRoll" placeholder="请输入轧后重量" />
              <el-span>&nbsp;Kg</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="轧前规格" prop="speBeforeRoll">
              <el-input-number :min="0.00" v-model="form.speBeforeRoll" placeholder="请输入轧前规格" />
              <el-span>&nbsp;mm</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="轧后规格" prop="speAfterRoll">
              <el-input-number :min="0.00" v-model="form.speAfterRoll" placeholder="请输入轧后规格" />
              <el-span>&nbsp;mm</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="精轧时间" prop="finishrTime">
              <el-date-picker clearable
                              v-model="form.finishrTime"
                              type="datetime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="请选择精轧时间">
              </el-date-picker>
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
          <el-col :span="8">
            <el-form-item label="质量判定" prop="qualityJudgment">
              <el-select v-model="form.qualityJudgment" placeholder="请选择质量判定" @change="handleInput">
                <el-option :label="''" :value="null"></el-option>
                <el-option
                  v-for="dict in filteredOptions"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="异常原因" prop="abnormalCause">
              <el-input v-model="form.abnormalCause" placeholder="请输入异常原因" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <!--        <el-button type="primary" @click="sxfuc">合 格</el-button>-->
        <!--        <el-button type="primary" @click="thfuc">退 火</el-button>-->
        <!--        <el-button type="danger" @click="scrap">报 废</el-button>-->
        <el-button type="primary" @click="cysfun">审核通过</el-button>
        <el-button type="primary" @click="submitForm" v-if="!form.showSave">保 存</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listJz, getJz, delJz, addJz, updateJz } from "@/api/mes/md/jz";
import WorkorderSelect from "@/components/scgd/single.vue";
import WorkstationSelect from "@/components/workstationSelect/simpletableSingle.vue";
import UserSingleSelect from "@/components/userSelect/single.vue";
import ProtaskSelect from "@/components/TaskSelect/taskSelectSingle.vue";
import {genCode} from "@/api/system/autocode/rule";
import {addSx } from "@/api/mes/md/sx";
import {addTh} from "@/api/mes/md/th";
import {listScgd, updateScgd} from "@/api/mes/md/scgd";
import {listSx} from "@/api/mes/md/sx";
import {listKp} from "@/api/mes/md/kp";
import {listCqp} from "@/api/mes/md/cqp";
import {listCpfp} from "@/api/mes/md/cpfp";
import {addGz, listGz} from "@/api/mes/md/gz";
import {listScgdmx} from "@/api/mes/md/scgdmx";

export default {
  name: "Jz",
  components: {WorkorderSelect,WorkstationSelect,UserSingleSelect,ProtaskSelect},
  dicts: ['sys_rz_ph', 'sys_rz_zlpd', 'sys_cp_cpzt', 'sys_jz_jzsb', 'sys_cpjy_zlpd', 'sys_cys_gx'],
  data() {
    return {
      // 筛选条件，假设是根据工单状态进行筛选
      filterCondition: {
        gdType: '9',  // 比如筛选状态为 '9-精轧' 的工单
      },
      filteredOptions: [],
      //自动生成编码
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
      // 精轧表格数据
      jzList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        finishrCode: null,        workorderId: null,        workorderCode: null,        brand: null,        batchNumber: null,        productStatus: null,        productThickness: null,        productWidth: null,        finishrollCount: null,        finishrollEquipment: null,        weightBeforeRoll: null,        weightAfterRoll: null,        speBeforeRoll: null,        speAfterRoll: null,        finishrTime: null,        userId: null,        userName: null,        qualityJudgment: null,        abnormalCause: null,      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        productThickness: [
          { required: true, message: "产品厚度不能为空", trigger: "blur" }
        ],        productWidth: [
          { required: true, message: "产品宽度不能为空", trigger: "blur" }
        ],        finishrollCount: [
          { required: true, message: "精轧次数不能为空", trigger: "blur" }
        ],        weightBeforeRoll: [
          { required: true, message: "轧前重量不能为空", trigger: "blur" }
        ],        weightAfterRoll: [
          { required: true, message: "轧后重量不能为空", trigger: "blur" }
        ],        speBeforeRoll: [
          { required: true, message: "轧前规格不能为空", trigger: "blur" }
        ],        speAfterRoll: [
          { required: true, message: "轧后规格不能为空", trigger: "blur" }
        ],qualityJudgment: [
          { required: true, message: "质量判定不能为空", trigger: "blur" }
        ]       }
    };
  },
  created() {
    this.getList();
  },
  computed: {
    modifiedJZList() {
      return this.jzList.map(item => {
        return {
          ...item,
          showButton: item.qualityJudgment == '' || item.qualityJudgment == null  // 根据 qualityJudgment 来设置 showButton 字段
        };
      });
    }
  },
  methods: {
    //值变化监听
    handleInput(event) {
      // console.log('输入值变化了:', this.form.furnaceHeat);
      // console.log('输入值变化了:', this.form.furnaceNo);
      // 在这里添加你的逻辑
      this.form.showSave = this.form.qualityJudgment != null
    },
    filterOptions(selectedValues) {
      this.filteredOptions = this.dict.type.sys_cpjy_zlpd.filter(dict =>
        selectedValues.includes(dict.value)
      );
    },
    async pdScgd(event) {

      let cpfpfinal;
      const cysQuery = {
        workCode: this.form.workorderCode
      }

      await listScgdmx(cysQuery).then(async response => {
        cpfpfinal = response.rows[0].porderCode;
      });

      const queryCpfp = {
        workorderCode: cpfpfinal
      }

      const queryRz = {
        pageNum: 1,
        pageSize: 10,
        workCode: this.form.workorderCode,
        workorderCode: this.form.workorderCode,
        status: '0'
      }
      listScgd(queryRz).then(async response => {
        // console.log(response.rows[0]);
        const scgd = response.rows[0];
        if (response.rows.length === 0) {
          this.$modal.msgError("不存在此工单，请重新扫描！");
          this.form.workorderCode = null;
        } else {
          if (scgd.thisProcess !== '9' && scgd.workStatus !== '3') {
            const gxjd = this.dict.type.sys_cys_gx.find(option => option.value === scgd.thisProcess) || {label: 'defaultLH'};
            this.$modal.msgError("该工单当前工序为：" + gxjd.label + " 请重新扫描");
            this.form.workorderCode = null;
          } else {
            this.form.workorderId = scgd.workId;
            this.form.workorderCode = scgd.workCode;
            this.form.brand = scgd.brand;
            this.form.batchNumber = scgd.batchNumber;

            let selectedValues = [];
            const phpd = this.dict.type.sys_rz_ph.find(option => option.value === this.form.brand) || {label: 'defaultLH'};
            const pdstr = phpd.label.substring(0, 1);
            if (pdstr === 'H') {
              selectedValues = ['4', '0', '1', '2', '3'];
            } else if (pdstr === 'T') {
              selectedValues = ['4', '0', '1', '2', '3'];
            }
            this.filterOptions(selectedValues);

            listJz(queryRz).then(response => {
              // console.log(queryRz);
              const listLength = response.rows.length;
              // console.log(response.rows);
              this.form.finishrollCount = listLength + 1;
            });
            await listCpfp(queryCpfp).then(response => {
              const cpfp = response.rows[0];
              console.log(cpfp);
              this.form.productWidth = cpfp.productWidth;
              this.form.productThickness = cpfp.productThickness;
              this.form.productStatus = cpfp.productStatus;
            });
            await listGz(queryRz).then(async response => {
              if (response.rows.length > 0) {
                const gz = response.rows[0];
                console.log("cyslistGz = ", response.rows);
                this.form.productWidth = gz.restructuringWidth;
                this.form.productStatus = gz.restructuringStatus;
                this.form.weightBeforeRoll = gz.weightAfterCleaning;
                this.form.speBeforeRoll = gz.thicknessAfterCleaning;
                this.form.productThickness = gz.restructuringThickness;
              } else {
                await listSx(queryRz).then(async response => {
                  if (response.rows.length > 0) {
                    const sx = response.rows[0];
                    // console.log(sx);
                    this.form.weightBeforeRoll = sx.weightAfterCleaning;
                    this.form.speBeforeRoll = sx.thicknessAfterCleaning;
                  } else {
                    await listCqp(queryRz).then(async response => {
                      if (response.rows.length > 0) {
                        const cqp = response.rows[0];
                        this.form.weightBeforeRoll = cqp.cutAfterWeight;
                        this.form.speBeforeRoll = cqp.cutAfterMilling;
                      } else {
                        await listKp(queryRz).then(response => {
                          if (response.rows.length > 0) {
                            const kp = response.rows[0];
                            this.form.weightBeforeRoll = kp.openAfterWeight;
                            this.form.speBeforeRoll = kp.spAfterMilling;
                          }
                        });
                      }
                    });
                  }
                });
              }
            });
          }
        }
      });
    },
    /**
     * 审核通过按钮点击事件
     * 功能：校验表单 -> 校验厚度 -> 保存数据 -> 变更工单状态
     */
    async cysfun() {
      console.log("=== 1. 进入审核流程 cysfun ===");

      // 1. 表单基础验证
      const valid = await new Promise(resolve => {
        if (this.$refs["form"]) {
          this.$refs["form"].validate(valid => resolve(valid));
        } else {
          resolve(false);
        }
      });

      if (!valid) {
        console.warn("表单基础验证未通过");
        return;
      }

      const currentWorkCode = this.form.workorderCode;
      if (!currentWorkCode) {
        this.$modal.msgError("错误：缺少工单号，无法继续！");
        return;
      }

      // ============================================================
      // 3. 【核心逻辑】厚度校验
      // ============================================================

      // 判断是否改制，改制直接跳过
      if (this.form.qualityJudgment === '3') {
        console.log("当前为【改制】，跳过厚度校验。");
      } else {
        try {
          console.log(`=== 2. 开始厚度校验, 工单号: ${currentWorkCode} ===`);

          // 3.1 查询生产工单明细
          const scgdmxResp = await listScgdmx({ workCode: currentWorkCode });

          if (!scgdmxResp.rows || scgdmxResp.rows.length === 0) {
            this.$modal.msgError("校验失败：未找到生产工单明细数据！");
            return;
          }

          // 获取最新的关联记录
          const latestScgd = scgdmxResp.rows[scgdmxResp.rows.length - 1];
          const porderCode = latestScgd.porderCode;

          if (!porderCode) {
            this.$modal.msgError("校验失败：生产订单号不存在！");
            return;
          }

          // 3.2 查询成品分批/订单信息 (标准厚度)
          const cpfpResp = await listCpfp({ workorderCode: porderCode });

          if (!cpfpResp.rows || cpfpResp.rows.length === 0) {
            this.$modal.msgError("校验失败：未找到订单标准数据！");
            return;
          }

          const cpfpData = cpfpResp.rows[0];

          // --- 【修复点 2】：根据工单类型区分 中品 vs 成品 ---
          // 使用 String() 强转，防止后端传来的是数字 0 而不是字符串 '0'
          // 注意：这里假设 '0' 代表中品（半成品）。如果你的系统中 '1' 才是中品，请把下面的 '0' 改成 '1'
          const isIntermediate = String(cpfpData.workorderType) === '0';

          if (isIntermediate) {
            console.log(`当前工单【${cpfpData.workorderCode}】类型为中品(workorderType=${cpfpData.workorderType})，跳过厚度下限拦截。`);
            // 中品直接放行，不执行下面的拦截逻辑
          } else {
            // === 只有成品才执行严格校验 ===
            const standThickness = Number(cpfpData.productThickness); // 标准厚度
            const currentThickness = Number(this.form.speAfterRoll);  // 当前厚度

            console.log(`=== 成品厚度对比(workorderType=${cpfpData.workorderType}): 标准=${standThickness}, 当前=${currentThickness} ===`);

            if (isNaN(standThickness) || isNaN(currentThickness)) {
              this.$modal.msgError("校验失败：厚度数据格式错误！");
              return;
            }

            // 校验规则：当前厚度 不能小于 标准厚度
            if (currentThickness < standThickness) {
              this.$modal.msgError(`校验拦截：成品当前厚度【${currentThickness}】不能小于订单要求厚度【${standThickness}】！`);
              return;
            }
            console.log("=== 3. 成品厚度校验通过 ===");
          }

        } catch (error) {
          console.error("校验异常:", error);
          this.$modal.msgError("系统异常：校验服务失败");
          return;
        }
      }
      // ==================== 校验结束 ====================


      // 4. 保存业务数据 (addJz / updateJz)
      try {
        console.log("=== 4. 开始保存精轧数据 ===");
        if (this.form.finishrId != null) {
          await updateJz(this.form);
        } else {
          await addJz(this.form);
        }
        console.log("精轧数据保存成功");
      } catch (error) {
        console.error("保存失败:", error);
        this.$modal.msgError("保存失败，请重试");
        return;
      }

      // 5. 更新工单状态及处理下道工序
      try {
        console.log("=== 5. 开始更新工单状态 ===");

        let cyspd = '';
        let cysprocess = '';
        let thisGx = '';
        let customObjects = null;

        const qj = this.form.qualityJudgment;

        if (qj === '0') {
          cyspd = '1'; cysprocess = '8'; thisGx = '已进入酸洗工序';
        } else if (qj === '1') {
          cyspd = '3'; cysprocess = '9'; thisGx = '已报废';
        } else if (qj === '2') {
          cyspd = '1'; cysprocess = '7'; thisGx = '已进入退火工序';
        } else if (qj === '4') {
          cyspd = '1'; cysprocess = '10'; thisGx = '已进入成品检验工序';
        } else if (qj === '3') {
          // 判定：改制
          cyspd = '1';
          cysprocess = '12';
          thisGx = '已进入改制工序';

          const queryGz = {
            pageNum: 1,
            pageSize: 10,
            workCode: currentWorkCode,
            workorderCode: currentWorkCode
          };

          // 1. 尝试获取改制信息
          const response1 = await listGz(queryGz);
          const cpgz = (response1.rows && response1.rows.length > 0)
            ? response1.rows[response1.rows.length - 1]
            : null;

          if (cpgz) {
            customObjects = {
              workorderId: cpgz.workorderId,
              workorderCode: cpgz.workorderCode,
              brand: cpgz.brand,
              batchNumber: cpgz.batchNumber,
              weight: cpgz.weight,
              blankThickness: cpgz.blankThickness,
              blankWidth: cpgz.blankWidth,
              productThickness: cpgz.restructuringThickness,
              productWidth: cpgz.restructuringWidth,
              productStatus: cpgz.restructuringStatus,
              toleranceType: cpgz.toleranceType,
              toleranceCount: cpgz.toleranceCount,
              tensileStrength: cpgz.tensileStrength,
              hardness: cpgz.hardness,
              materialCode: cpgz.materialCode,
              materialName: cpgz.materialName,
              status: '1'
            };
          } else {
            // 2. 如果没有改制记录，查询精轧记录
            const response = await listJz(queryGz);
            const cpfp = (response.rows && response.rows.length > 0) ? response.rows[0] : null;
            if (cpfp) {
              customObjects = {
                workorderId: cpfp.workorderId,
                workorderCode: cpfp.workorderCode,
                brand: cpfp.brand,
                batchNumber: cpfp.batchNumber,
                weight: cpfp.weight,
                blankThickness: cpfp.blankThickness,
                blankWidth: cpfp.blankWidth,
                productThickness: cpfp.productThickness,
                productWidth: cpfp.productWidth,
                productStatus: cpfp.productStatus,
                toleranceType: cpfp.toleranceType,
                toleranceCount: cpfp.toleranceCount,
                tensileStrength: cpfp.tensileStrength,
                hardness: cpfp.hardness,
                materialCode: cpfp.materialCode,
                materialName: cpfp.materialName,
                status: '1'
              };
            } else {
              this.$modal.msgError("未找到相关改制/精轧数据，无法提交！");
              return;
            }
          }
        }

        // 构造状态更新对象
        const scgdcys = {
          workId: this.form.workorderId,
          workStatus: cyspd,
          thisProcess: cysprocess
        };

        // 执行状态更新
        await updateScgd(scgdcys);

        // 如果是改制，还需要插入改制表
        if (qj === '3' && customObjects) {
          await addGz(customObjects);
        }

        // 6. 全部成功，提示并关闭
        this.$modal.msgSuccess(thisGx || "操作成功");
        this.open = false;
        this.getList();

      } catch (error) {
        console.error("工单状态流转失败:", error);
        this.$modal.msgError("流程流转失败，数据已保存但状态未更新。");
      }
    },

    /* 向下一工序-中插入一条数据 */
    async sxfuc() {
      this.form.qualityJudgment = '0';
      this.submitForm()
      let code = "";
      // 表单验证
      const valid = await new Promise(resolve => {
        this.$refs["form"].validate(valid => resolve(valid));
      });

      // 如果表单验证不通过，则退出
      if (!valid) {
        return;
      }

      try {
        const scgdcys = {
          workId: this.form.workorderId,
          workStatus : '1',
          thisProcess: '8'
        };
        // const response = await addSx(customObjects);
        const response1 = await updateScgd(scgdcys);  // 使用局部变量 scgdcys
        this.$modal.msgSuccess("已进入酸洗工序");
        this.open = false;
        this.getList();
      } catch (error) {
        console.error("Error adding addSx:", error);
      }
    },
    /* 向下一工序-中插入一条数据 */
    async thfuc() {
      this.form.qualityJudgment = '4';
      this.submitForm()
      // 表单验证
      const valid = await new Promise(resolve => {
        this.$refs["form"].validate(valid => resolve(valid));
      });

      // 如果表单验证不通过，则退出
      if (!valid) {
        return;
      }

      // 调用 addSx 方法
      try {
        // 确保在调用 updateScgd 时使用正确的 scgdcys 变量
        const scgdcys = {
          workId: this.form.workorderId,
          workStatus : '1',
          thisProcess: '7'
        };

        const response1 = await updateScgd(scgdcys);  // 使用局部变量 scgdcys
        // const response = await addTh(customObjects);
        this.$modal.msgSuccess("已进入退火工序");
        this.open = false;
        this.getList();
      } catch (error) {
        console.error("Error adding addTh:", error);
      }
    },
    // 报废
    async scrap() {
      this.submitForm()
      let code = "";
      // 表单验证
      const valid = await new Promise(resolve => {
        this.$refs["form"].validate(valid => resolve(valid));
      });

      // 如果表单验证不通过，则退出
      if (!valid) {
        return;
      }

      // 调用 addCpjy 方法
      try {
        // 确保在调用 updateScgd 时使用正确的 scgdcys 变量
        const scgdcys = {
          workId: this.form.workorderId,
          workStatus : '3',
          thisProcess: '9'
        };

        const response1 = await updateScgd(scgdcys);  // 使用局部变量 scgdcys
        this.$modal.msgSuccess("已报废");
        this.open = false;
        this.getList();
      } catch (error) {
        console.error("Error adding addCpjy:", error);
      }
    },

    /** 查询精轧列表 */
    getList() {
      this.loading = true;
      listJz(this.queryParams).then(response => {
        this.jzList = response.rows;
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
      // 获取当前日期时间
      const now = new Date();
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, '0'); // 补零
      const day = String(now.getDate()).padStart(2, '0'); // 补零
      const hours = String(now.getHours()).padStart(2, '0'); // 补零
      const minutes = String(now.getMinutes()).padStart(2, '0'); // 补零
      const seconds = String(now.getSeconds()).padStart(2, '0'); // 补零

      // 格式化为 YYYY-MM-DD HH:MM:SS
      const formattedTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
      this.form = {
        finishrId: null,
        finishrCode: null,
        workorderId: null,
        workorderCode: null,
        brand: null,
        batchNumber: null,
        productStatus: null,
        productThickness: null,
        productWidth: null,
        finishrollCount: null,
        finishrollEquipment: null,
        weightBeforeRoll: null,
        weightAfterRoll: null,
        speBeforeRoll: null,
        speAfterRoll: null,
        finishrTime: formattedTime,
        userId: this.$store.state.user.userId,
        userName: this.$store.state.user.name,
        qualityJudgment: null,
        abnormalCause: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        buttonstatus: "PREPARE"
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
      this.ids = selection.map(item => item.finishrId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加精轧";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const finishrId = row.finishrId || this.ids
      getJz(finishrId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改精轧";
      });
    },
    /** 提交按钮 */
    submitForm(){
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.finishrId != null) {
            updateJz(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addJz(this.form).then(response => {
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
      const finishrIds = row.finishrId || this.ids;
      this.$modal.confirm('是否确认删除精轧编号为"' + finishrIds + '"的数据项？').then(function() {
        return delJz(finishrIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
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
      this.download('md/jz/export', {
        ...this.queryParams
      }, `jz_${new Date().getTime()}.xlsx`)
    },
    //自动生成编码
    handleAutoGenChange(autoGenFlag){
      if(autoGenFlag){
        genCode('CAS_CODE').then(response =>{
          this.form.finishrCode = response;
        });
      }else{
        this.form.finishrCode = null;
      }
    }
  }
};
</script>
