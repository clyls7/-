<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!--      <el-form-item label="工单编码" prop="fpiCode">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.fpiCode"-->
      <!--          placeholder="请输入成本检验编码"-->
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
      <el-form-item label="导电率" prop="electricalConductivity">
        <el-input
          v-model="queryParams.electricalConductivity"
          placeholder="请输入导电率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检验类型" prop="inspectionType">
        <el-select v-model="queryParams.inspectionType" placeholder="请选择检验类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_cpjy_jylx"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="检验时间" prop="inspectionTime">
        <el-date-picker clearable
                        v-model="queryParams.inspectionTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择检验时间">
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
            v-for="dict in dict.type.sys_csfs_zlpd"
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
          v-hasPermi="['md:cpjy:add']"
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
          v-hasPermi="['md:cpjy:edit']"
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
          v-hasPermi="['md:cpjy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['md:cpjy:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cpjyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="工单编码" width="150" align="center" prop="fpiCode" />-->
      <el-table-column label="生产工单" width="150" align="center" prop="workorderCode" />
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
      <el-table-column label="抗拉强度" align="center" prop="tensileStrength" />
      <el-table-column label="延伸率" align="center" prop="elongation" />
      <el-table-column label="硬度" align="center" prop="hardness" />
      <el-table-column label="杯凸值" align="center" prop="cupConvexValue" />
      <el-table-column label="导电率" align="center" prop="electricalConductivity" />
      <el-table-column label="检验类型" align="center" prop="inspectionType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_cpjy_jylx" :value="scope.row.inspectionType"/>
        </template>
      </el-table-column>
      <el-table-column label="检验时间" align="center" prop="inspectionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inspectionTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="员工姓名" align="center" prop="userName" />
      <el-table-column label="质量判定" align="center" prop="qualityJudgment">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_csfs_zlpd" :value="scope.row.qualityJudgment"/>
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
            v-hasPermi="['md:cpjy:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['md:cpjy:remove']"
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

    <!-- 添加或修改成品检验对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1080px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <!--          <el-col :span="8">-->
          <!--        <el-form-item label="工单编码" prop="fpiCode">-->
          <!--          <el-input v-model="form.fpiCode" placeholder="请输入成品检验编码" />-->
          <!--        </el-form-item>-->
          <!--          </el-col>-->
          <!--            <el-col :span="8">-->
          <!--              <el-form-item  label-width="80">-->
          <!--                <el-switch v-model="autoGenFlag"-->
          <!--                           active-color="#13ce66"-->
          <!--                           active-text="自动生成"-->
          <!--                           @change="handleAutoGenChange(autoGenFlag)" v-if="optType != 'view' && form.buttonstatus =='PREPARE'">-->
          <!--                </el-switch>-->
          <!--              </el-form-item>-->
          <!--            </el-col>-->
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
            <el-form-item label="抗拉强度" prop="tensileStrength">
              <el-input-number :min="0.00" v-model="form.tensileStrength" placeholder="请输入抗拉强度" />
              <el-span>&nbsp;N/mm</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="延伸率" prop="elongation">
              <el-input-number :min="0.00" v-model="form.elongation" placeholder="请输入延伸率" />
              <el-span>&nbsp;%</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="硬度" prop="hardness">
              <el-input-number :min="0.00" v-model="form.hardness" placeholder="请输入硬度" />
              <el-span>&nbsp;HV</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="杯凸值" prop="cupConvexValue">
              <el-input-number :min="0.00" v-model="form.cupConvexValue" placeholder="请输入杯凸值" />
              <el-span>&nbsp;mm</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="导电率" prop="electricalConductivity">
              <el-input-number :min="0.00" v-model="form.electricalConductivity" placeholder="请输入导电率" />
              <el-span>&nbsp;Ms/m</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="检验类型" prop="inspectionType">
              <el-select v-model="form.inspectionType" placeholder="请选择检验类型" disabled>
                <el-option
                  v-for="dict in dict.type.sys_cpjy_jylx"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="检验时间" prop="inspectionTime">
              <el-date-picker clearable
                              v-model="form.inspectionTime"
                              type="datetime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="请选择检验时间">
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
                  v-for="dict in dict.type.sys_csfs_zlpd"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
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
        <el-button type="primary" @click="cysfun">审核通过</el-button>
        <el-button type="primary" @click="submitForm" v-if="!form.showSave">保 存</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCpjy, getCpjy, delCpjy, addCpjy, updateCpjy } from "@/api/mes/md/cpjy";
import WorkorderSelect from "@/components/scgd/single.vue";
import WorkstationSelect from "@/components/workstationSelect/simpletableSingle.vue";
import UserSingleSelect from "@/components/userSelect/single.vue";
import ProtaskSelect from "@/components/TaskSelect/taskSelectSingle.vue";
import {genCode} from "@/api/system/autocode/rule";
import {listScgd, updateScgd} from "@/api/mes/md/scgd";
import {listSx} from "@/api/mes/md/sx";
import {listCpfp} from "@/api/mes/md/cpfp";
import {listGz} from "@/api/mes/md/gz";
import {addCplb} from "@/api/mes/md/cplb";

export default {
  name: "Cpjy",
  components: {WorkorderSelect,WorkstationSelect,UserSingleSelect,ProtaskSelect},
  dicts: ['sys_rz_ph', 'sys_rz_zlpd', 'sys_cp_cpzt', 'sys_cpjy_jylx', 'sys_cys_gx','sys_csfs_zlpd'],
  data() {
    return {
      // 筛选条件，假设是根据工单状态进行筛选
      filterCondition: {
        gdType: '10',  // 比如筛选状态为 '10-成品检验' 的工单
      },
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
      // 成品检验表格数据
      cpjyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fpiCode: null,        workorderId: null,        workorderCode: null,        brand: null,        batchNumber: null,        productStatus: null,        productThickness: null,        productWidth: null,        tensileStrength: null,        elongation: null,        hardness: null,        cupConvexValue: null,        electricalConductivity: null,        inspectionType: null,        inspectionTime: null,        userId: null,        userName: null,        qualityJudgment: null,        abnormalCause: null,      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        hardness: [
          { required: true, message: "硬度不能为空", trigger: "blur" }
        ],
        qualityJudgment: [
          { required: true, message: "质量判定不能为空", trigger: "blur" }
        ]}
    };
  },
  created() {
    this.getList();
  },
  computed: {
    modifiedTHList() {
      return this.thList.map(item => {
        return {
          ...item,
          showButton: item.qualityJudgment == '' || item.qualityJudgment == null  // 根据 qualityJudgment 来设置 showButton 字段
        };
      });
    }
  },
  methods: {
    handleInput(event) {
      // console.log('输入值变化了:', this.form.furnaceHeat);
      // console.log('输入值变化了:', this.form.furnaceNo);
      // 在这里添加你的逻辑
      this.form.showSave = this.form.qualityJudgment != null
    },
    /** 查询成品检验列表 */
    getList() {
      this.loading = true;
      listCpjy(this.queryParams).then(response => {
        this.cpjyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    async cysfun() {
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

      // 调用 addSx 方法
      try {
        let customObjects = null;
        let cyspd = '';
        let cysendTime = '';
        if(this.form.qualityJudgment === '0'){
          // 获取当前日期时间
          const now = new Date();
          const year = now.getFullYear();
          const month = String(now.getMonth() + 1).padStart(2, '0'); // 补零
          const day = String(now.getDate()).padStart(2, '0'); // 补零
          const hours = String(now.getHours()).padStart(2, '0'); // 补零
          const minutes = String(now.getMinutes()).padStart(2, '0'); // 补零
          const seconds = String(now.getSeconds()).padStart(2, '0'); // 补零

          // 格式化为 YYYY-MM-DD HH:MM:SS
          cysendTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
          cyspd = '2';
          this.form.warehouseStatus = '1';
          addCplb(this.form);
          this.$modal.msgSuccess("已完工，请继续进行入库操作");
        }else if(this.form.qualityJudgment === '1'){
          cyspd = '3';
          this.$modal.msgSuccess("已报废");
        }else if(this.form.qualityJudgment === '2'){
          cyspd = '1';
          this.$modal.msgSuccess("已进入成品检验-复试阶段");
        }else if(this.form.qualityJudgment === '3'){
          cyspd = '1';
          this.$modal.msgSuccess("已进入改制");
        }
        const scgdcys = {
          workId: this.form.workorderId,
          workStatus : cyspd,
          thisProcess: '10',
          endTime: cysendTime
        };

        const response1 = await updateScgd(scgdcys);  // 使用局部变量 scgdcys
        // const response = await addSx(customObjects);

        this.open = false;
        this.getList();
      } catch (error) {
        console.error("Error adding sx:", error);
      }
    },
    pdScgd(event){
      const queryRz = {
        pageNum: 1,
        pageSize: 10,
        workCode: this.form.workorderCode,
        workorderCode: this.form.workorderCode
      }
      listScgd(queryRz).then(async response => {
        console.log(response.rows[0]);
        const scgd = response.rows[0];
        if (response.rows.length === 0) {
          this.$modal.msgError("不存在此工单，请重新扫描！");
          this.form.workorderCode = null;
        } else {
          if (scgd.thisProcess !== '10' && scgd.workStatus !== '3') {
            const gxjd = this.dict.type.sys_cys_gx.find(option => option.value === scgd.thisProcess) || {label: 'defaultLH'};
            this.$modal.msgError("该工单当前工序为：" + gxjd.label + " 请重新扫描");
            this.form.workorderCode = null;
          } else if (scgd.workStatus === '2') {
            this.$modal.msgError("该工单已完工");
            this.form.workorderCode = null;
          } else {
            this.form.workorderId = scgd.workId;
            this.form.workorderCode = scgd.workCode;
            this.form.brand = scgd.brand;
            this.form.batchNumber = scgd.batchNumber;
            await listGz(queryRz).then(async response => {
              if (response.rows.length > 0) {
                const gz = response.rows[0];
                // console.log(response.rows);
                this.form.productWidth = gz.restructuringWidth;
                this.form.productStatus = gz.restructuringStatus;
                // this.form.weightBeforeRoll = gz.weightAfterCleaning;
                // this.form.speBeforeRoll = gz.thicknessAfterCleaning;
                this.form.productThickness = gz.restructuringThickness;
              } else {
                listCpfp(queryRz).then(response => {
                  const cpfp = response.rows[0];
                  this.form.productThickness = cpfp.productThickness;
                });
                listSx(queryRz).then(response => {
                  const sx = response.rows[0];
                  // console.log(sx);
                  // this.form.productThickness = sx.thicknessAfterCleaning;
                  this.form.productWidth = sx.widthAfterCleaning;
                  this.form.productStatus = sx.productStatus;
                });
              }
            });
            listCpjy(queryRz).then(response => {
              if (response.rows.length > 0) {
                this.form.inspectionType = '1';
              }
            });
          }
        }
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
        fpiId: null,
        fpiCode: null,
        workorderId: null,
        workorderCode: null,
        brand: null,
        batchNumber: null,
        productStatus: null,
        productThickness: null,
        productWidth: null,
        tensileStrength: null,
        elongation: null,
        hardness: null,
        cupConvexValue: null,
        electricalConductivity: null,
        inspectionType: "0",
        inspectionTime: formattedTime,
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
      this.ids = selection.map(item => item.fpiId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加成品检验";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fpiId = row.fpiId || this.ids
      getCpjy(fpiId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改成品检验";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.fpiId != null) {
            updateCpjy(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCpjy(this.form).then(response => {
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
      const fpiIds = row.fpiId || this.ids;
      this.$modal.confirm('是否确认删除成品检验编号为"' + fpiIds + '"的数据项？').then(function() {
        return delCpjy(fpiIds);
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
      this.download('md/cpjy/export', {
        ...this.queryParams
      }, `cpjy_${new Date().getTime()}.xlsx`)
    },
    //自动生成编码
    handleAutoGenChange(autoGenFlag){
      if(autoGenFlag){
        genCode('CAS_CODE').then(response =>{
          this.form.fpiCode = response;
        });
      }else{
        this.form.fpiCode = null;
      }
    }
  }
};
</script>
