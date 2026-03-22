<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="工单编码" prop="doublesidedCode">-->
<!--        <el-input-->
<!--          v-model="queryParams.doublesidedCode"-->
<!--          placeholder="请输入开坯工序编码"-->
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
          disabled
        />
      </el-form-item>
      <el-form-item label="设备名称" prop="deviceName">
        <el-input
          v-model="queryParams.deviceName"
          placeholder="请输入设备名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="轧前规格" prop="spBeforeMilling">
        <el-input
          v-model="queryParams.spBeforeMilling"
          placeholder="请输入轧前规格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="轧后规格" prop="spAfterMilling">
        <el-input
          v-model="queryParams.spAfterMilling"
          placeholder="请输入轧后规格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="重量" prop="weight">-->
<!--        <el-input-->
<!--          v-model="queryParams.weight"-->
<!--          placeholder="请输入重量"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="粗轧时间" prop="rollingTime">
        <el-date-picker clearable
          v-model="queryParams.rollingTime"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="请选择粗轧时间">
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
      <el-form-item label="是否切边" prop="whetherCut">
        <el-select v-model="queryParams.whetherCut" placeholder="是否切边" clearable>
          <el-option
            v-for="dict in dict.type.sys_yes_no"
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
          v-hasPermi="['md:kp:add']"
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
          v-hasPermi="['md:kp:edit']"
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
          v-hasPermi="['md:kp:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['md:kp:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="modifiedRzList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="工单编码" width="150" align="center" prop="doublesidedCode" />-->
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
      <el-table-column label="设备名称" width="150" align="center" prop="deviceName" />
      <el-table-column label="轧前规格" align="center" prop="spBeforeMilling" />
      <el-table-column label="轧后规格" align="center" prop="spAfterMilling" />
      <el-table-column label="开坯前重量" width="100" align="center" prop="openBeforeWeight" />
      <el-table-column label="开坯后重量" width="100" align="center" prop="openAfterWeight" />
      <el-table-column label="粗轧时间" align="center" prop="rollingTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.rollingTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否切边" align="center" prop="whetherCut" />
      <el-table-column label="员工姓名" align="center" prop="userName" />
      <el-table-column label="质量判定" align="center" prop="qualityJudgment">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_rz_zlpd" :value="scope.row.qualityJudgment"/>
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
            v-hasPermi="['md:kp:edit']"
            v-if = "scope.row.showButton"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['md:kp:remove']"
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

    <!-- 添加或修改开坯对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1080px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
<!--          <el-col :span="8">-->
<!--        <el-form-item label="工单编码" prop="doublesidedCode">-->
<!--          <el-input v-model="form.doublesidedCode" placeholder="请输入开坯工序编码" />-->
<!--        </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="8">-->
<!--            <el-form-item  label-width="80">-->
<!--              <el-switch v-model="autoGenFlag"-->
<!--                         active-color="#13ce66"-->
<!--                         active-text="自动生成"-->
<!--                         @change="handleAutoGenChange(autoGenFlag)" v-if="optType != 'view' && form.buttonstatus =='PREPARE'">-->
<!--              </el-switch>-->
<!--            </el-form-item>-->
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
        <el-form-item label="设备名称" prop="deviceName">
          <el-input v-model="form.deviceName" placeholder="请输入设备名称" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="轧前规格" prop="spBeforeMilling">
          <el-input-number :min="0.00" v-model="form.spBeforeMilling" placeholder="请输入轧前规格" />
          <el-span>&nbsp;mm</el-span>
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="轧后规格" prop="spAfterMilling">
          <el-input-number :min="3.90" v-model="form.spAfterMilling" placeholder="请输入轧前规格" />
          <el-span>&nbsp;mm</el-span>
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label-width="95px" label="开坯前重量" prop="openBeforeWeight">
          <el-input-number :min="0" v-model="form.openBeforeWeight" placeholder="请输入开坯前重量" />
          <el-span>&nbsp;Kg</el-span>
        </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label-width="95px" label="开坯后重量" prop="openAfterWeight">
              <el-input-number :min="0" v-model="form.openAfterWeight" placeholder="请输入开坯后重量" />
              <el-span>&nbsp;Kg</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="粗轧时间" prop="rollingTime">
          <el-date-picker clearable
            v-model="form.rollingTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择粗轧时间">
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
            <el-form-item label="是否切边" prop="qualityJudgment">
              <el-select v-model="form.whetherCut" placeholder="是否切边" @change="handleInput" >
                <el-option
                  v-for="dict in dict.type.sys_yes_no"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="质量判定" prop="qualityJudgment">
              <el-select v-model="form.qualityJudgment" placeholder="请输入质量判定" @change="handleInput" >
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
import { listKp, getKp, delKp, addKp, updateKp } from "@/api/mes/md/kp";
import WorkorderSelect from "@/components/scgd/single.vue";
import WorkstationSelect from "@/components/workstationSelect/simpletableSingle.vue";
import UserSingleSelect from "@/components/userSelect/single.vue";
import ProtaskSelect from "@/components/TaskSelect/taskSelectSingle.vue";
import {genCode} from "@/api/system/autocode/rule";
import {addCbfx} from "@/api/mes/md/cbfx";
import {addCqp} from "@/api/mes/md/cqp";
import {listScgd, updateScgd} from "@/api/mes/md/scgd";
import {listSmx} from "@/api/mes/md/smx";
import {listRezha} from "@/api/mes/md/rezha";

export default {
  name: "Kp",
  components: {WorkorderSelect,WorkstationSelect,UserSingleSelect,ProtaskSelect},
  dicts: ['sys_rz_ph', 'sys_rz_zlpd', 'sys_cys_gx', 'sys_yes_no'],
  data() {
    return {
      // 筛选条件，假设是根据工单状态进行筛选
      filterCondition: {
        gdType: '4',  // 比如筛选状态为 '4-开坯' 的工单
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
      // 开坯表格数据
      kpList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        doublesidedCode: null, openBeforeWeight: null,   openAfterWeight: null,      workorderId: null,        workorderCode: null,        brand: null,        batchNumber: null,        deviceName: null,        spBeforeMilling: null,        spAfterMilling: null,        weight: null,        rollingTime: null,        userId: null,        userName: null,        qualityJudgment: null,        abnormalCause: null,   whetherCut:null,      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        spBeforeMilling: [
          { required: true, message: "轧前规格不能为空", trigger: "blur" }
        ],        spAfterMilling: [
          { required: true, message: "轧后规格不能为空", trigger: "blur" }
        ],        weight: [
          { required: true, message: "重量不能为空", trigger: "blur" }
        ],qualityJudgment: [
          { required: true, message: "质量判定不能为空", trigger: "blur" }
        ]       }
    };
  },
  created() {
    this.getList();
  },
  computed: {
    modifiedRzList() {
      return this.kpList.map(item => {
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
          if(scgd.thisProcess !== '4' && scgd.workStatus !== '3'){
            const gxjd = this.dict.type.sys_cys_gx.find(option => option.value === scgd.thisProcess) || { label: 'defaultLH' };
            this.$modal.msgError("该工单当前工序为：" + gxjd.label + " 请重新扫描");
            this.form.workorderCode = null;
          }else{
            this.form.workorderId = scgd.workId;
            this.form.workorderCode = scgd.workCode;
            this.form.brand = scgd.brand;
            this.form.batchNumber = scgd.batchNumber;
            listSmx(queryRz).then(response => {
              const smxlistLength = response.rows.length;
              if(smxlistLength > 0){
                const smx = response.rows[smxlistLength - 1];
                console.log(smx);
                this.form.openBeforeWeight = smx.spAfterWeight;
                this.form.spBeforeMilling = smx.spAfterMilling;
              }
            });
          }
        }
      });
    },
    /* 向下一工序-成分分析中插入一条数据 */
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

      // 调用 addCqp 方法
      try {
        let cyspd = '';
        let thisProcess = '';
        if(this.form.qualityJudgment === '0'){
          cyspd = '1';
          if(this.form.whetherCut === 'Y'){
            thisProcess = '5';
            this.$modal.msgSuccess("已进入粗切边工序");
          }else{
            thisProcess = '6';
            this.$modal.msgSuccess("已进入产品分配工序");
          }

        }else if(this.form.qualityJudgment === '1'){
          cyspd = '3';
          this.$modal.msgSuccess("已报废");
        }
        // 确保在调用 updateScgd 时使用正确的 scgdcys 变量
        const scgdcys = {
          workId: this.form.workorderId,
          workStatus : cyspd,
          thisProcess: thisProcess
        };

        const response1 = await updateScgd(scgdcys);  // 使用局部变量 scgdcys
        console.log("updateScgd response:", response1);
        // const response = await addCqp(customObjects);

        this.open = false;
        this.getList();
      } catch (error) {
        console.error("Error adding cbfx:", error);
      }
    },
    /** 查询开坯列表 */
    getList() {
      this.loading = true;
      listKp(this.queryParams).then(response => {
        this.kpList = response.rows;
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
        doublesidedId: null,
        doublesidedCode: null,
        workorderId: null,
        workorderCode: null,
        brand: null,
        batchNumber: null,
        deviceName: null,
        spBeforeMilling: null,
        spAfterMilling: null,
        openBeforeWeight: null,
        openAfterWeight: null,
        weight: null,
        rollingTime: formattedTime,
        userId: this.$store.state.user.userId,
        userName: this.$store.state.user.name,
        qualityJudgment: null,
        abnormalCause: null,
        whetherCut: null,
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
      this.ids = selection.map(item => item.doublesidedId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加开坯";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const doublesidedId = row.doublesidedId || this.ids
      getKp(doublesidedId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改开坯";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.doublesidedId != null) {
            updateKp(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addKp(this.form).then(response => {
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
      const doublesidedIds = row.doublesidedId || this.ids;
      this.$modal.confirm('是否确认删除开坯编号为"' + doublesidedIds + '"的数据项？').then(function() {
        return delKp(doublesidedIds);
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
      this.download('md/kp/export', {
        ...this.queryParams
      }, `kp_${new Date().getTime()}.xlsx`)
    },
    //自动生成编码
    handleAutoGenChange(autoGenFlag){
      if(autoGenFlag){
        genCode('CAS_CODE').then(response =>{
          this.form.doublesidedCode = response;
        });
      }else{
        this.form.doublesidedCode = null;
      }
    }
  }
};
</script>
