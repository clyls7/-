<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!--      <el-form-item label="分配编码" prop="paCode">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.paCode"-->
      <!--          placeholder="请输入产品分配编码"-->
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
      <el-form-item label="重量" prop="weight">
        <el-input
          v-model="queryParams.weight"
          placeholder="请输入重量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开坯厚度" prop="blankThickness">
        <el-input
          v-model="queryParams.blankThickness"
          placeholder="请输入开坯厚度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开坯宽度" prop="blankWidth">
        <el-input
          v-model="queryParams.blankWidth"
          placeholder="请输入开坯宽度"
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
          <el-option
            v-for="dict in dict.type.sys_cp_cpzt"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label-width="98px" label="产品物料编码" prop="materialCode">
        <el-input
          v-model="queryParams.materialCode"
          placeholder="请输入产品物料编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label-width="98px" label="产品物料名称" prop="materialName">
        <el-input
          v-model="queryParams.materialName"
          placeholder="请输入产品物料名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作员" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入操作员"
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

    <el-table v-loading="loading" :data="modifiedRzList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="分配编码" width="150" align="center" prop="paCode" />-->
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
      <el-table-column label="重量" align="center" prop="weight" />
      <el-table-column label="开坯厚度" align="center" prop="blankThickness" />
      <el-table-column label="开坯宽度" align="center" prop="blankWidth" />
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
<!--      <el-table-column label="抗拉强度" align="center">-->
<!--        <template slot-scope="scope">-->
<!--        <span>{{ scope.row.tensileStrengthStart }} </span>-->
<!--        <span>~</span>-->
<!--        <span> {{ scope.row.tensileStrengthEnd }}</span>-->
<!--        </template>-->
<!--      </el-table-column>>-->
      <el-table-column label="抗压强度" align="center" prop="tensileStrength"/>
      <el-table-column label="硬度" align="center" prop="hardness"/>
<!--      <el-table-column label="硬度" align="center">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ scope.row.hardnessStart }} </span>-->
<!--          <span>~</span>-->
<!--          <span> {{ scope.row.hardnessEnd }}</span>-->
<!--        </template>-->
<!--      </el-table-column>>-->
      <el-table-column label="产品物料编码" width="150" align="center" prop="materialCode" />
      <el-table-column label="产品物料名称" width="150" align="center" prop="materialName" />
      <el-table-column label="操作员" align="center" prop="userName" />
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
            v-hasPermi="['md:cpfp:edit']"
            v-if = "scope.row.showButton"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['md:cpfp:remove']"
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

    <!-- 添加或修改产品分配对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <!--          <el-col :span="8">-->
          <!--        <el-form-item label="分配编码" prop="paCode">-->
          <!--          <el-input v-model="form.paCode" placeholder="请输入产品分配编码" />-->
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
            <el-form-item label="重量" prop="weight">
              <el-input-number :min="0" v-model="form.weight" placeholder="请输入重量" />
              <el-span>&nbsp;Kg</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="开坯厚度" prop="blankThickness">
              <el-input-number :min="0.00" v-model="form.blankThickness" placeholder="请输入开坯厚度" />
              <el-span>&nbsp;mm</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="开坯宽度" prop="blankWidth">
              <el-input-number :min="0.00" v-model="form.blankWidth" placeholder="请输入开坯宽度" />
              <el-span>&nbsp;mm</el-span>
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
<!--          <el-col :span="8">-->
<!--          <el-form-item label="抗拉强度" class="centered-range">-->
<!--            <el-input-->
<!--              v-model="form.tensileStrengthStart"-->
<!--              placeholder="起始值"-->
<!--              style="width: 80px;"-->
<!--            ></el-input>-->
<!--            <span> MPa</span>-->
<!--            <span style="margin: 0 5px;">~</span>-->
<!--            <el-input-->
<!--              v-model="form.tensileStrengthEnd"-->
<!--              placeholder="结束值"-->
<!--              style="width: 80px;"-->
<!--            ></el-input>-->
<!--            <span> MPa</span>-->
<!--          </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="7">-->
<!--            <el-form-item label="硬度" class="centered-range">-->
<!--              <el-input-->
<!--                v-model="form.hardnessStart"-->
<!--                placeholder="起始值"-->
<!--                style="width: 80px;"-->
<!--              ></el-input>-->
<!--              <span> HV</span>-->
<!--              <span style="margin: 0 5px;">~</span>-->
<!--              <el-input-->
<!--                v-model="form.hardnessEnd"-->
<!--                placeholder="结束值"-->
<!--                style="width: 80px;"-->
<!--              ></el-input>-->
<!--              <span> HV</span>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
          <el-col :span="8">
            <el-form-item label="抗拉强度" prop="tensileStrength">
              <el-input v-model="form.tensileStrength" placeholder="请输入抗拉强度范围"/>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="硬度" prop="hardness">
              <el-input v-model="form.hardness" placeholder="请输入硬度范围"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label-width="98px" label="产品物料编码" prop="materialCode">
              <el-input v-model="form.materialCode" placeholder="请输入产品物料编码"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label-width="105px" label="产品物料名称" prop="materialName">
              <el-input v-model="form.materialName" placeholder="请输入产品物料名称" />
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
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="cysfun" >审核通过</el-button>
        <el-button type="primary" @click="submitForm" v-if="!form.showSave">保 存</el-button>
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
import {listSmx} from "@/api/mes/md/smx";

export default {
  name: "Cpfp",
  components: {WorkorderSelect,WorkstationSelect,UserSingleSelect,ProtaskSelect},
  dicts: ['sys_rz_ph', 'sys_cp_cpzt', 'sys_cys_gx', 'sys_cys_gc'],
  data() {
    return {
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
        paCode: null,        workorderId: null,   tensileStrengthStart: null,  tensileStrengthEnd: null,   hardnessStart: null,     hardnessEnd: null,       workorderCode: null,        brand: null,        batchNumber: null,        weight: null,        blankThickness: null,        blankWidth: null,        productThickness: null,        productWidth: null,        productStatus: null,        materialCode: null,        materialName: null,        userId: null,        userName: null,  toleranceType: null, toleranceCount: null, tensileStrength: null,hardness: null,    },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        weight: [
          { required: true, message: "重量不能为空", trigger: "blur" }
        ],        blankThickness: [
          { required: true, message: "开坯厚度不能为空", trigger: "blur" }
        ],        blankWidth: [
          { required: true, message: "开坯宽度不能为空", trigger: "blur" }
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
          showButton: item.productStatus == '' || item.productStatus == null  // 根据 qualityJudgment 来设置 showButton 字段
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
      this.form.showSave = this.form.productStatus != null
    },
    pdScgd(event){
      const queryRz = {
        pageNum: 1,
        pageSize: 10,
        workCode: this.form.workorderCode,
        workorderCode: this.form.workorderCode
      }
      listScgd(queryRz).then(async response => {
        // console.log(response.rows[0]);
        const scgd = response.rows[0];
        if (response.rows.length === 0) {
          this.$modal.msgError("不存在此工单，请重新扫描！");
          this.form.workorderCode = null;
        } else {
          if (scgd.thisProcess !== '6' && scgd.workStatus !== '3') {
            const gxjd = this.dict.type.sys_cys_gx.find(option => option.value === scgd.thisProcess) || {label: 'defaultLH'};
            this.$modal.msgError("该工单当前工序为：" + gxjd.label + " 请重新扫描");
            this.form.workorderCode = null;
          } else {
            this.form.workorderId = scgd.workId;
            this.form.workorderCode = scgd.workCode;
            this.form.brand = scgd.brand;
            this.form.batchNumber = scgd.batchNumber;
            // await listSmx(queryRz).then(response => {
            //   const smx = response.rows[0];
            //   this.form.blankThickness = smx.spAfterMilling;
            // });
            await listKp(queryRz).then(response => {
              const kp = response.rows[0];
              this.form.blankThickness = kp.spAfterMilling;
              this.form.weight = kp.openAfterWeight;
            });
            await listCqp(queryRz).then(response => {
              if (response.rows.length > 0) {
                const cqp = response.rows[0];
                this.form.weight = cqp.cutAfterWeight;
                this.form.blankWidth = cqp.cutAfterMilling;
              }
            });
          }
        }
      });
    },

    /* 向下一工序-成分分析中插入一条数据 */
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

      // 调用 addTh 方法
      try {
        let cyspd = '';
        let thisGx = '';
        const phpd = this.dict.type.sys_rz_ph.find(option => option.value === this.form.brand) || { label: 'defaultLH' };
        const pdstr = phpd.label.substring(0, 1);

        if(pdstr === 'H'){
          cyspd = '7';
          thisGx = '已进入退火工序';
        }else if(pdstr === 'T'){
          cyspd = '9';
          thisGx = '已进入精轧工序';
        }
        // 确保在调用 updateScgd 时使用正确的 scgdcys 变量
        const scgdcys = {
          workId: this.form.workorderId,
          workStatus : '1',
          thisProcess: cyspd
        };

        const response1 = await updateScgd(scgdcys);  // 使用局部变量 scgdcys
        console.log("updateScgd response:", response1);
        // const response = await addTh(customObjects);
        this.$modal.msgSuccess(thisGx);
        this.open = false;
        this.getList();
      } catch (error) {
        console.error("Error adding addTh:", error);
      }
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
        weight: null,
        blankThickness: null,
        blankWidth: null,
        productThickness: null,
        productWidth: null,
        productStatus: null,
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
    handleDelete(row) {
      const paIds = row.paId || this.ids;
      this.$modal.confirm('是否确认删除产品分配编号为"' + paIds + '"的数据项？').then(function() {
        return delCpfp(paIds);
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
      this.download('md/cpfp/export', {
        ...this.queryParams
      }, `cpfp_${new Date().getTime()}.xlsx`)
    },
    //自动生成编码
    handleAutoGenChange(autoGenFlag){
      if(autoGenFlag){
        genCode('CAS_CODE').then(response =>{
          this.form.paCode = response;
        });
      }else{
        this.form.paCode = null;
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
