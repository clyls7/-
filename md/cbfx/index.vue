<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!--      <el-form-item label="工单编码" prop="costaCode">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.costaCode"-->
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
      <el-form-item label="化验时间" prop="testingTime">
        <el-date-picker clearable
                        v-model="queryParams.testingTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择化验时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="铜" prop="cu">
        <el-input
          v-model="queryParams.cu"
          placeholder="请输入铜"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="铁" prop="fe">
        <el-input
          v-model="queryParams.fe"
          placeholder="请输入铁"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="铅" prop="pb">
        <el-input
          v-model="queryParams.pb"
          placeholder="请输入铅"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="铝" prop="ai">
        <el-input
          v-model="queryParams.ai"
          placeholder="请输入铝"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="镉" prop="cd">
        <el-input
          v-model="queryParams.cd"
          placeholder="请输入镉"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="锡" prop="sn">
        <el-input
          v-model="queryParams.sn"
          placeholder="请输入锡"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="化验员" prop="technicianName">
        <el-input
          v-model="queryParams.technicianName"
          placeholder="请输入化验员名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="质量判定" prop="qualityJudgment">
        <el-select v-model="queryParams.qualityJudgment" placeholder="质量判定" clearable>
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
          v-hasPermi="['md:cbfx:add']"
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
          v-hasPermi="['md:cbfx:edit']"
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
          v-hasPermi="['md:cbfx:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['md:cbfx:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="modifiedRzList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="成分分析编码" width="150" align="center" prop="costaCode" />-->
      <el-table-column label="生产工单编码" width="150" align="center" prop="workorderCode" />
      <el-table-column label="牌号" align="center" prop="brand">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_rz_ph" :value="scope.row.brand"/>
        </template>
      </el-table-column>
            <el-table-column label="品位" align="center" prop="grade">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.sys_rz_pw" :value="scope.row.grade"/>
              </template>
            </el-table-column>
      <el-table-column label="批次号" width="200" align="center" prop="batchNumber">
        <template slot-scope="scope">
          <!-- 使用自定义组件 BatchTag -->
          <BatchTag :batchNumber="scope.row.batchNumber" />
        </template>
      </el-table-column>

      <el-table-column label="化验时间" align="center" prop="testingTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.testingTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="铜" align="center" prop="cu" />
      <el-table-column label="铁" align="center" prop="fe" />
      <el-table-column label="铅" align="center" prop="pb" />
      <el-table-column label="铝" align="center" prop="ai" />
      <el-table-column label="镉" align="center" prop="cd" />
      <el-table-column label="锡" align="center" prop="sn" />
      <el-table-column label="化验员" align="center" prop="technicianName" />
      <el-table-column label="质量判定" align="center" prop="qualityJudgment" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_rz_zlpd" :value="scope.row.qualityJudgment"/>
        </template>
      </el-table-column>
      <el-table-column label="异常原因" align="center" prop="abnormalCause" />
      <el-table-column label="备注" width="180" align="center" prop="remark" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['md:cbfx:edit']"
            v-if = "scope.row.showButton"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['md:cbfx:remove']"
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

    <!-- 添加或修改成本分析对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1080px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <!--          <el-col :span="8">-->
          <!--        <el-form-item label="工单编码" prop="costaCode">-->
          <!--          <el-input v-model="form.costaCode" placeholder="请输入成分分析编码" />-->
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
                <!--          <el-button slot="append" @click="handleWorkorderSelect" icon="el-icon-search"></el-button>-->
              </el-input>
            </el-form-item>
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
                    <el-col :span="8">
                      <el-form-item label="品位" prop="grade">
                        <el-select v-model="form.grade" placeholder="请选择品位" @change="autoGenerateCode">
                          <el-option
                            v-for="dict in dict.type.sys_rz_pw"
                            :key="dict.value"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
          <el-col :span="8">
            <el-form-item label="批次号" prop="batchNumber">
              <el-input v-model="form.batchNumber" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="化验时间" prop="testingTime">
              <el-date-picker clearable
                              v-model="form.testingTime"
                              type="datetime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="请选择化验时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="铜" prop="cu">
              <el-input-number :min="0.00" v-model="form.cu" placeholder="请输入铜" />
              <el-span>&nbsp;%</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="铁" prop="fe">
              <el-input-number :min="0.00" v-model="form.fe" placeholder="请输入铁" />
              <el-span>&nbsp;%</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="铅" prop="pb">
              <el-input-number :min="0.00" v-model="form.pb" placeholder="请输入铅" />
              <el-span>&nbsp;%</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="铝" prop="ai">
              <el-input-number :min="0.00" v-model="form.ai" placeholder="请输入铝" />
              <el-span>&nbsp;%</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="镉" prop="cd">
              <el-input-number :min="0.00" v-model="form.cd" placeholder="请输入镉" />
              <el-span>&nbsp;%</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="锡" prop="sn">
              <el-input-number :min="0.00" v-model="form.sn" placeholder="请输入锡" />
              <el-span>&nbsp;%</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="化验员" prop="technicianName">
              <el-input v-model="form.technicianName" placeholder="请选择员工" >
                <el-button slot="append" @click="handleUserSelect" icon="el-icon-search"></el-button>
              </el-input>
            </el-form-item>
            <UserSingleSelect ref="userSelect" @onSelected="onUserSelected"></UserSingleSelect>
          </el-col>
          <el-col :span="8">
            <el-form-item label="质量判定" prop="qualityJudgment">
              <el-select v-model="form.qualityJudgment" placeholder="请输入质量判定" @change="handleInput">
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
        <el-button type="primary" @click="cysfun">审核通过</el-button>
        <el-button type="primary" @click="submitForm" v-if="!form.showSave">保 存</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCbfx, getCbfx, delCbfx, addCbfx, updateCbfx } from "@/api/mes/md/cbfx";
import WorkorderSelect from "@/components/scgd/single.vue";
import WorkstationSelect from "@/components/workstationSelect/simpletableSingle.vue";
import UserSingleSelect from "@/components/userSelect/single.vue";
import ProtaskSelect from "@/components/TaskSelect/taskSelectSingle.vue";
import {genCode} from "@/api/system/autocode/rule";
import {addRezha} from "@/api/mes/md/rezha";
import {getScgd, listScgd, updateScgd} from "@/api/mes/md/scgd";
import {listSx} from "@/api/mes/md/sx";
import {listRz, updateRz} from "@/api/mes/md/rz";

export default {
  name: "Cbfx",
  components: {WorkorderSelect,WorkstationSelect,UserSingleSelect,ProtaskSelect},
  dicts: ['mes_order_status', 'mes_feedback_type', 'sys_rz_type', 'sys_rz_ph', "sys_rz_lh", "sys_rz_pw", "sys_rz_zlpd", "sys_cys_gx", "sys_cys_ygbh"],
  data() {
    return {
      // 筛选条件，假设是根据工单状态进行筛选
      filterCondition: {
        gdType: '1',  // 比如筛选状态为 '1-成分分析' 的工单
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
      // 成本分析表格数据
      cbfxList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        costaCode: null,   grade: null,     workorderId: null,        workorderCode: null,        brand: null,        batchNumber: null,        testingTime: null,        cu: null,        fe: null,        pb: null,        ai: null,        cd: null,        sn: null,        technicianId: null,        technicianName: null,        qualityJudgment: null,        abnormalCause: null,      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    console.log(this.$store.state.user.userId);
  },
  computed: {
    modifiedRzList() {
      return this.cbfxList.map(item => {
        console.log("item.qualityJudgment = " + item.qualityJudgment );
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
    //生成批次号 规则：根据拉铸日期、炉号、炉次、员工编号、品位生成
    autoGenerateCode(value) {
      const queryRz = {
        pageNum: 1,
        pageSize: 10,
        workCode: this.form.workorderCode
      }
      let castingData;              //拉铸日期
      let furnaceNo;                //炉号
      let furnaceHeat;              //炉次
      let usercodeC;                 //员工编号

      listRz(queryRz).then(response => {
        const rz = response.rows[0];
        castingData = rz.castingData;
        furnaceNo = rz.furnaceNo;
        furnaceHeat = rz.furnaceHeat;
        usercodeC = rz.usercode;

        // 检查 castingData 是否有效
        if (!castingData || typeof castingData !== 'string') {
          console.error('Invalid castingData:', castingData); // 如果需要调试，可以输出错误
          return; // 避免执行后续逻辑
        }

        // 拉铸日期
        const lzrq = castingData.split('-').join('');

        // 获取炉号，找不到时给默认值
        const lh = this.dict.type.sys_rz_lh.find(option => option.value === furnaceNo) || { label: 'defaultLH' };

        // 获取品位，找不到时给默认值
        const pw = this.dict.type.sys_rz_pw.find(option => option.value === this.form.grade) || { label: 'defaultPW' };

        // 炉次
        const lc = furnaceHeat;

        // 员工编号
        const usercode = this.dict.type.sys_cys_ygbh.find(option => option.value === usercodeC) || { label: 'defaultPW' };


        // 检查所有必填项是否有效
        if (lzrq && lh && lc && usercode && pw) {
          this.form.batchNumber = `${lzrq}${lh.label}${lc}${usercode.label}-${pw.label}`;
          const querypd = {
            pageNum: 1,
            pageSize: 10,
            batchNumber: this.form.batchNumber
          }
          listCbfx(querypd).then(response => {
            console.log(response);
            if(response.rows.length > 0){
              this.$modal.msgError("批次号重复，请检查后重试！");
              this.form.batchNumber = "";
            }
          });

        }
      });
    },
    pdScgd(event){
      const queryRz = {
        pageNum: 1,
        pageSize: 10,
        workCode: this.form.workorderCode
      }
      listScgd(queryRz).then(response => {
        // console.log(response.rows[0]);
        const scgd = response.rows[0];
        if(response.rows.length === 0){
          this.$modal.msgError("不存在此工单，请重新扫描！");
          this.form.workorderCode = null;
        }else{
          if(scgd.thisProcess !== '1'  && scgd.workStatus !== '3'){
            const gxjd = this.dict.type.sys_cys_gx.find(option => option.value === scgd.thisProcess) || { label: 'defaultLH' };
            this.$modal.msgError("该工单当前工序为：" + gxjd.label + " 请重新扫描");
            this.form.workorderCode = null;
          }else{
            this.form.workorderId = scgd.workId;
            this.form.workorderCode = scgd.workCode;
            this.form.brand = scgd.brand;
            // this.form.batchNumber = scgd.batchNumber;
          }
        }
      });
    },
    /* 向下一工序-热轧中插入一条数据 */
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

      // 调用 addRezha 方法
      try {
        let cyspd = '';
        if(this.form.qualityJudgment === '0'){
          cyspd = '1';
          this.$modal.msgSuccess("已进入锯切工序");
        }else if(this.form.qualityJudgment === '1'){
          cyspd = '3';
          this.$modal.msgSuccess("已报废");
        }
        // 确保在调用 updateScgd 时使用正确的 scgdcys 变量
        const scgdcys = {
          workId: this.form.workorderId,
          workStatus : cyspd,
          thisProcess: '11',
          brand : this.form.brand,
          batchNumber : this.form.batchNumber,
        };

        const rzcys = {
          workorderId: this.form.workorderId,
          batchNumber : this.form.batchNumber
        };
        console.log(rzcys);

        const response1 = await updateScgd(scgdcys);  // 使用局部变量 scgdcys
        const response2 = await updateRz(rzcys);  // 使用局部变量 rzcys
        console.log("updateScgd response:", response1);
        console.log("updateRz response:", response2);

        // const response = await addRezha(customObjects);

        this.open = false;
        this.getList();
      } catch (error) {
        console.error("Error adding cbfx:", error);
      }
    },
    /** 查询成本分析列表 */
    getList() {
      this.loading = true;
      listCbfx(this.queryParams).then(response => {
        this.cbfxList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
    //点击人员选择按钮
    handleUserSelect(){
      this.$refs.userSelect.showFlag = true;
    },
    //人员选择返回
    onUserSelected(row){
      this.form.technicianId = row.userId;
      this.form.technicianName = row.userName;
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
        costaId: null,
        costaCode: null,
        workorderId: null,
        workorderCode: null,
        brand: null,
        grade: null,
        batchNumber: null,
        testingTime: formattedTime,
        cu: null,
        fe: null,
        pb: null,
        ai: null,
        cd: null,
        sn: null,
        technicianId: this.$store.state.user.userId,
        qualityJudgment: null,
        abnormalCause: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        buttonstatus: "PREPARE",
        technicianName: this.$store.state.user.name

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
      this.ids = selection.map(item => item.costaId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加成分分析";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const costaId = row.costaId || this.ids
      getCbfx(costaId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改成分分析";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.costaId != null) {
            updateCbfx(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCbfx(this.form).then(response => {
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
      const costaIds = row.costaId || this.ids;
      this.$modal.confirm('是否确认删除成分分析编号为"' + costaIds + '"的数据项？').then(function() {
        return delCbfx(costaIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('md/cbfx/export', {
        ...this.queryParams
      }, `cbfx_${new Date().getTime()}.xlsx`)
    },
    //自动生成编码
    handleAutoGenChange(autoGenFlag){
      if(autoGenFlag){
        genCode('COSTA_CODE').then(response =>{
          this.form.costaCode = response;
        });
      }else{
        this.form.costaCode = null;
      }
    }
  }
};
</script>
