<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
<!--      <el-form-item label="工单编码" prop="hotrollCode">-->
<!--        <el-input-->
<!--          v-model="queryParams.hotrollCode"-->
<!--          placeholder="请输入工单编码"-->
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
      <el-form-item label="加热炉位置" prop="heatfLocation">
        <el-input
          v-model="queryParams.heatfLocation"
          placeholder="请输入加热炉位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="升温时间" prop="heatTime">
        <el-date-picker clearable
                        v-model="queryParams.heatTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择升温时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="开轧时间" prop="rollTime">
        <el-date-picker clearable
                        v-model="queryParams.rollTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择开轧时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="加热温度" prop="heatTemperature">
        <el-input
          v-model="queryParams.heatTemperature"
          placeholder="请输入加热温度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="装炉日期" prop="installationDate">
        <el-date-picker clearable
                        v-model="queryParams.installationDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择装炉日期">
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
            v-for="dict in dict.type.sys_rz_zlpd"
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
          v-hasPermi="['md:rezha:add']"
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
          v-hasPermi="['md:rezha:edit']"
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
          v-hasPermi="['md:rezha:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['md:rezha:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="modifiedRzList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="工单编码" width="150" align="center" prop="hotrollCode" />-->
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
      <el-table-column label="加热炉位置" width="100" align="center" prop="heatfLocation" />
      <el-table-column label="升温时间" align="center" prop="heatTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.heatTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="开轧时间" align="center" prop="rollTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.rollTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="加热温度" align="center" prop="heatTemperature" />
      <el-table-column label="装炉日期" align="center" prop="installationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.installationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="轧前重量" align="center" prop="weightBeforeRoll" />
      <el-table-column label="轧后重量" align="center" prop="weightAfterRoll" />
      <el-table-column label="员工姓名" align="center" prop="userName" />
      <el-table-column label="质量判定" align="center" prop="qualityJudgment">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_rz_zlpd" :value="scope.row.qualityJudgment"/>
        </template>
      </el-table-column>
      <el-table-column label="异常原因" align="center" prop="abnormalCause" />
      <el-table-column label="备注" align="center" prop="remark" />
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
            v-hasPermi="['md:rezha:edit']"
            v-if = "scope.row.showButton"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['md:rezha:remove']"
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

    <!-- 添加或修改热轧对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1080px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
<!--          <el-col :span="8">-->
<!--        <el-form-item label="工单编码" prop="hotrollCode">-->
<!--          <el-input v-model="form.hotrollCode" placeholder="请输入热轧工序编码" />-->
<!--        </el-form-item>-->
<!--        </el-col>-->
<!--        <el-col :span="8">-->
<!--          <el-form-item  label-width="80">-->
<!--            <el-switch v-model="autoGenFlag"-->
<!--                       active-color="#13ce66"-->
<!--                       active-text="自动生成"-->
<!--                       @change="handleAutoGenChange(autoGenFlag)" v-if="optType != 'view' && form.buttonstatus =='PREPARE'">-->
<!--            </el-switch>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
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
            <el-form-item label="批次号" prop="batchNumber" >
              <el-input v-model="form.batchNumber" placeholder="自动带出" disabled/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="加热炉位置" prop="heatfLocation">
              <el-input v-model="form.heatfLocation" placeholder="请输入加热炉位置" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="升温时间" prop="heatTime">
              <el-date-picker clearable
                              v-model="form.heatTime"
                              type="datetime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="请选择升温时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="开轧时间" prop="rollTime">
              <el-date-picker clearable
                              v-model="form.rollTime"
                              type="datetime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="请选择开轧时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="加热温度" prop="heatTemperature">
              <el-input-number :min="0.00" v-model="form.heatTemperature" placeholder="请输入加热温度" />
              <el-span>&nbsp;℃</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="装炉日期" prop="installationDate">
              <el-date-picker clearable
                              v-model="form.installationDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择装炉日期">
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
            <el-form-item label="质量判定" prop="qualityJudgment">
              <el-select v-model="form.qualityJudgment" placeholder="请选择质量判定" @change="handleInput" >
                <el-option :label="''" :value="null"></el-option>
                <el-option
                  v-for="dict in dict.type.sys_rz_zlpd"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="16">
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
import { listRezha, getRezha, delRezha, addRezha, updateRezha } from "@/api/mes/md/rezha";
import {genCode} from "@/api/system/autocode/rule";
import WorkorderSelect from "@/components/scgd/single.vue";
import WorkstationSelect from "@/components/workstationSelect/simpletableSingle.vue";
import UserSingleSelect from "@/components/userSelect/single.vue";
import ProtaskSelect from "@/components/TaskSelect/taskSelectSingle.vue";
import {addCbfx} from "@/api/mes/md/cbfx";
import {addSmx} from "@/api/mes/md/smx";
import {listScgd, updateScgd} from "@/api/mes/md/scgd";
import {listRz} from "@/api/mes/md/rz";

export default {
  name: "Rezha",
  components: {WorkorderSelect,WorkstationSelect,UserSingleSelect,ProtaskSelect},
  dicts: ['sys_rz_ph', 'sys_rz_zlpd', 'sys_cys_gx'],
  data() {
    return {
      // 筛选条件，假设是根据工单状态进行筛选
      filterCondition: {
        gdType: '2',  // 比如筛选状态为 '2-热轧' 的工单
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
      // 热轧表格数据
      rezhaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        batchNumber: null  ,weightBeforeRoll: null, weightAfterRoll:null,  hotrollCode: null,        workorderId: null,        workorderCode: null,        brand: null,        heatfLocation: null,        heatTime: null,        rollTime: null,        heatTemperature: null,        installationDate: null,        userId: null,        userName: null,        qualityJudgment: null,        abnormalCause: null,      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        qualityJudgment: [
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
      return this.rezhaList.map(item => {
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
          if(scgd.thisProcess !== '2' && scgd.workStatus !== '3'){
            const gxjd = this.dict.type.sys_cys_gx.find(option => option.value === scgd.thisProcess) || { label: 'defaultLH' };
            this.$modal.msgError("该工单当前工序为：" + gxjd.label + " 请重新扫描");
            this.form.workorderCode = null;
          }else{
            this.form.workorderId = scgd.workId;
            this.form.workorderCode = scgd.workCode;
            this.form.brand = scgd.brand;
            this.form.batchNumber = scgd.batchNumber;
            listRz(queryRz).then(response => {
              if(response.rows.length > 0){
                // console.log("afterSawing = " , response.rows[0].afterSawing);
                this.form.weightBeforeRoll = response.rows[0].afterSawing;
          }
            });
        }
        }
      });
    },
    /* 向下一工序-双面铣中插入一条数据 */
    async cysfun() {
      this.submitForm()
      // 表单验证
      const valid = await new Promise(resolve => {
        this.$refs["form"].validate(valid => resolve(valid));
      });

      // 如果表单验证不通过，则退出
      if (!valid) {
        return;
      }

      // 调用 addCbfx 方法
      try {
        let cyspd = '';
        if(this.form.qualityJudgment === '0'){
          cyspd = '1';
        }else if(this.form.qualityJudgment === '1'){
          cyspd = '3';
        }
        // 确保在调用 updateScgd 时使用正确的 scgdcys 变量
        const scgdcys = {
          workId: this.form.workorderId,
          workStatus : cyspd,
          thisProcess: '3'
        };

        const response1 = await updateScgd(scgdcys);  // 使用局部变量 scgdcys
        console.log("updateScgd response:", response1);

        // const response = await addSmx(customObjects);
        this.$modal.msgSuccess("已进入双面铣工序");
        this.open = false;
        this.getList();
      } catch (error) {
        console.error("Error adding cbfx:", error);
      }
    },
    /** 查询热轧列表 */
    getList() {
      this.loading = true;
      listRezha(this.queryParams).then(response => {
        this.rezhaList = response.rows;
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
        hotrollId: null,
        hotrollCode: null,
        workorderId: null,
        workorderCode: null,
        brand: null,
        weightBeforeRoll: null,
        weightAfterRoll: null,
        heatfLocation: null,
        heatTime: formattedTime,
        rollTime: formattedTime,
        heatTemperature: null,
        installationDate: formattedTime,
        userId: this.$store.state.user.userId,
        userName: this.$store.state.user.name,
        qualityJudgment: null,
        abnormalCause: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        buttonstatus: "PREPARE",
        batchNumber : null
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
      this.ids = selection.map(item => item.hotrollId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加热轧";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const hotrollId = row.hotrollId || this.ids
      getRezha(hotrollId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改热轧";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.hotrollId != null) {
            updateRezha(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRezha(this.form).then(response => {
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
      const hotrollIds = row.hotrollId || this.ids;
      this.$modal.confirm('是否确认删除热轧编号为"' + hotrollIds + '"的数据项？').then(function() {
        return delRezha(hotrollIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('md/rezha/export', {
        ...this.queryParams
      }, `rezha_${new Date().getTime()}.xlsx`)
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
    //自动生成编码
    handleAutoGenChange(autoGenFlag){
      if(autoGenFlag){
        genCode('HOTROLL_CODE').then(response =>{
          this.form.hotrollCode = response;
        });
      }else{
        this.form.hotrollCode = null;
      }
    }
  }
};
</script>
