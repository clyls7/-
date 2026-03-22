<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!--      <el-form-item label="工单编码" prop="picklingCode">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.picklingCode"-->
      <!--          placeholder="请输入酸洗工序编码"-->
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
      <el-form-item label="牌号" prop="brand" disabled>
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
      <el-form-item label="酸洗次数" prop="picklingCount">
        <el-input
          v-model="queryParams.picklingCount"
          placeholder="请输入酸洗次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="来料情况" prop="incomingMaterials">
        <el-select v-model="queryParams.incomingMaterials" placeholder="请选择来料情况" clearable>
          <el-option
            v-for="dict in dict.type.sys_ty_llqk"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="清洗设备" prop="cleaningEquipment">
        <el-input
          v-model="queryParams.cleaningEquipment"
          placeholder="请输入清洗设备"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="清洗后重量" label-width="82px" prop="weightAfterCleaning">
        <el-input
          v-model="queryParams.weightAfterCleaning"
          placeholder="请输入清洗后重量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="清洗后厚度" label-width="82px" prop="thicknessAfterCleaning">
        <el-input
          v-model="queryParams.thicknessAfterCleaning"
          placeholder="请输入清洗后厚度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="清洗后宽度" label-width="82px" prop="widthAfterCleaning">
        <el-input
          v-model="queryParams.widthAfterCleaning"
          placeholder="请输入清洗后宽度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="清洗时间" prop="picklingTime">
        <el-date-picker clearable
                        v-model="queryParams.picklingTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择清洗时间">
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
            v-for="dict in dict.type.sys_sx_zlpd"
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
          v-hasPermi="['md:sx:add']"
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
          v-hasPermi="['md:sx:edit']"
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
          v-hasPermi="['md:sx:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['md:sx:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="modifiedSXList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="工单编码" width="150" align="center" prop="picklingCode" />-->
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
      <el-table-column label="酸洗次数" align="center" prop="picklingCount" />
      <el-table-column label="来料情况" align="center" prop="incomingMaterials">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_ty_llqk" :value="scope.row.incomingMaterials"/>
        </template>
      </el-table-column>
      <el-table-column label="清洗设备" align="center" prop="cleaningEquipment" />
      <el-table-column label="清洗后重量" width="100" align="center" prop="weightAfterCleaning" />
      <el-table-column label="清洗后厚度" width="100" align="center" prop="thicknessAfterCleaning" />
      <el-table-column label="清洗后宽度" width="100" align="center" prop="widthAfterCleaning" />
      <el-table-column label="清洗时间" align="center" prop="picklingTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.picklingTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作员" align="center" prop="userName" />
      <el-table-column label="质量判定" width="100" align="center" prop="qualityJudgment">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_sx_zlpd" :value="scope.row.qualityJudgment"/>
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
            v-hasPermi="['md:sx:edit']"
            v-if = "scope.row.showButton"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['md:sx:remove']"
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

    <!-- 添加或修改酸洗对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <!--          <el-col :span="8">-->
          <!--        <el-form-item label="工单编码" prop="picklingCode">-->
          <!--          <el-input v-model="form.picklingCode" placeholder="请输入酸洗工序编码" />-->
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
            <el-form-item label="酸洗次数" prop="picklingCount">
              <el-input-number :min="1" v-model="form.picklingCount" placeholder="请输入酸洗次数" disabled/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来料情况" prop="incomingMaterials">
              <el-select v-model="form.incomingMaterials" placeholder="请选择来料情况" disabled>
                <el-option
                  v-for="dict in dict.type.sys_ty_llqk"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="清洗设备" prop="cleaningEquipment">
              <el-input v-model="form.cleaningEquipment" placeholder="请输入清洗设备" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="清洗后重量" label-width="92px" prop="weightAfterCleaning">
              <el-input-number :min="0.00" v-model="form.weightAfterCleaning" placeholder="请输入清洗后重量" />
              <el-span>&nbsp;Kg</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="清洗后厚度" label-width="92px" prop="thicknessAfterCleaning">
              <el-input-number :min="0.00" v-model="form.thicknessAfterCleaning" placeholder="请输入清洗后厚度" />
              <el-span>&nbsp;mm</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="清洗后宽度" label-width=" 92px" prop="widthAfterCleaning">
              <el-input-number :min="0.00" v-model="form.widthAfterCleaning" placeholder="请输入清洗后宽度" />
              <el-span>&nbsp;mm</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="清洗时间" prop="picklingTime">
              <el-date-picker clearable
                              v-model="form.picklingTime"
                              type="datetime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="请选择清洗时间">
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
          <!--            <el-form-item label="质量判定" prop="qualityJudgment">-->
          <!--              <el-select v-model="form.qualityJudgment" placeholder="请选择质量判定" @change="handleInput">-->
          <!--                <el-option :label="''" :value="null"></el-option>-->
          <!--                <el-option-->
          <!--                  v-for="dict in dict.type.sys_sx_zlpd"-->
          <!--                  :key="dict.value"-->
          <!--                  :label="dict.label"-->
          <!--                  :value="dict.value"-->
          <!--                ></el-option>-->
          <!--              </el-select>-->
          <!--            </el-form-item>-->
          <!--          </el-col>-->
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
        <!--        <el-button type="success" @click="thsucess">退火件合格</el-button>-->
        <!--        <el-button type="success" @click="finalSucess">成品合格</el-button>-->
        <!--        <el-button type="info" @click="reform">改制</el-button>-->
        <!--        <el-button type="danger" @click="scrap">报废</el-button>-->
        <el-button type="primary" @click="cysfun">审核通过</el-button>
        <el-button type="primary" @click="submitForm" v-if="!form.showSave">保 存</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSx, getSx, delSx, addSx, updateSx } from "@/api/mes/md/sx";
import WorkorderSelect from "@/components/scgd/single.vue";
import WorkstationSelect from "@/components/workstationSelect/simpletableSingle.vue";
import UserSingleSelect from "@/components/userSelect/single.vue";
import ProtaskSelect from "@/components/TaskSelect/taskSelectSingle.vue";
import {genCode} from "@/api/system/autocode/rule";
import {addJz} from "@/api/mes/md/jz"
import {addCpjy } from "@/api/mes/md/cpjy";
import {listScgd, updateScgd} from "@/api/mes/md/scgd";
import {listTh} from "@/api/mes/md/th";
import {listJz} from "@/api/mes/md/jz";
import {listCpfp} from "@/api/mes/md/cpfp";
import {listGz, addGz} from "@/api/mes/md/gz";
import {listScgdmx} from "@/api/mes/md/scgdmx";

export default {
  name: "Sx",
  components: {WorkorderSelect,WorkstationSelect,UserSingleSelect,ProtaskSelect},
  dicts: ['sys_rz_ph', 'sys_sx_zlpd', 'sys_cp_cpzt', 'sys_ty_llqk', 'sys_cys_gx'],
  data() {
    return {
      // 筛选条件，假设是根据工单状态进行筛选
      filterCondition: {
        gdType: '8',  // 比如筛选状态为 '8-酸洗' 的工单
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
      // 酸洗表格数据
      sxList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        picklingCode: null,        workorderId: null,        workorderCode: null,        brand: null,        batchNumber: null,        productStatus: null,        productThickness: null,        productWidth: null,        picklingCount: null,        incomingMaterials: null,        cleaningEquipment: null,        weightAfterCleaning: null,        thicknessAfterCleaning: null,        widthAfterCleaning: null,        picklingTime: null,        userId: null,        userName: null,        qualityJudgment: null,        abnormalCause: null,      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        productThickness: [
          { required: true, message: "产品厚度不能为空", trigger: "blur" }
        ],        productWidth: [
          { required: true, message: "产品宽度不能为空", trigger: "blur" }
        ],        picklingCount: [
          { required: true, message: "酸洗次数不能为空", trigger: "blur" }
        ],        weightAfterCleaning: [
          { required: true, message: "清洗后重量不能为空", trigger: "blur" }
        ],        thicknessAfterCleaning: [
          { required: true, message: "清洗后厚度不能为空", trigger: "blur" }
        ],        widthAfterCleaning: [
          { required: true, message: "清洗后宽度不能为空", trigger: "blur" }
        ],qualityJudgment: [
          { required: true, message: "质量判定不能为空", trigger: "blur" }
        ]       }
    };
  },
  created() {
    this.getList();
  },
  computed: {
    modifiedSXList() {
      return this.sxList.map(item => {
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
    filterOptions(selectedValues) {
      const allOptions = this.dict.type.sys_sx_zlpd;
      this.filteredOptions = selectedValues
        .map(value => allOptions.find(dict => dict.value === value))
        .filter(Boolean); // 去掉可能没找到的值
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
      listScgd(queryRz).then(response => {

        // console.log(response.rows[0]);
        const scgd = response.rows[0];
        if (response.rows.length === 0) {
          this.$modal.msgError("不存在此工单，请重新扫描！");
          this.form.workorderCode = null;
        } else {
          if (scgd.thisProcess !== '8' && scgd.workStatus !== '3') {
            const gxjd = this.dict.type.sys_cys_gx.find(option => option.value === scgd.thisProcess) || {label: 'defaultLH'};
            this.$modal.msgError("该工单当前工序为：" + gxjd.label + " 请重新扫描");
            this.form.workorderCode = null;
          } else {
            this.form.workorderId = scgd.workId;
            this.form.workorderCode = scgd.workCode;
            this.form.brand = scgd.brand;
            this.form.batchNumber = scgd.batchNumber;
            listSx(queryRz).then(response => {
              // console.log(queryRz);
              const listLength = response.rows.length;
              // console.log(response.rows);
              this.form.picklingCount = listLength + 1;
              console.log("cys000");
            });
            let th;
            let jz;
            let thTime;
            let jzTime;
            let cphd;
            let cpkd;
            let gzhcpzt;
            let cpfp;
            let gz;
            let selectedValues = [];

            const phpd = this.dict.type.sys_rz_ph.find(option => option.value === this.form.brand) || {label: 'defaultLH'};
            const pdstr = phpd.label.substring(0, 1);
            if (pdstr === 'H') {
              selectedValues = ['0', '1', '4', '3'];
            } else if (pdstr === 'T') {
              selectedValues = ['0', '4', '1', '3', '5'];
            }

            this.filterOptions(selectedValues);

            //incomingMaterials 来料情况  sys_ty_llqk
            // incomingMaterials 来料情况 sys_ty_llqk
            Promise.all([listTh(queryRz), listJz(queryRz), listCpfp(queryCpfp), listGz(queryRz)])
              .then(([responseTh, responseJz, responseCpfp, responseGz]) => {
                console.log("cys111");
                // console.log(responseCpfp.rows[responseGz.rows.length - 1]);
                if (responseGz.rows.length > 0) {
                  gz = responseGz.rows[responseGz.rows.length - 1];
                  cphd = gz.restructuringThickness;
                  cpkd = gz.restructuringWidth;
                  gzhcpzt = gz.restructuringStatus;
                } else {
                  if (responseCpfp.rows.length > 0) {
                    cpfp = responseCpfp.rows[0];
                    cphd = cpfp.productThickness;
                    cpkd = cpfp.productWidth;
                    gzhcpzt = cpfp.productStatus;
                    console.log("cys222");
                  }
                }
                // 处理 listTh 的响应
                if (responseTh.rows.length > 0) {
                  th = responseTh.rows[0];
                  // this.form.productThickness = th.productThickness;
                  // this.form.productWidth = th.productWidth;
                  this.form.productStatus = th.productStatus;
                  thTime = th.createTime;
                  console.log("cys333");
                  // console.log("thTime1 = " + thTime);
                }

                // 处理 listJz 的响应
                if (responseJz.rows.length > 0) {
                  jz = responseJz.rows[0];
                  jzTime = jz.createTime;
                } else {
                  jzTime = "1999-01-01 00:00:00";
                }

                // 确保 thTime 和 jzTime 都已经赋值
                // console.log("thTime = " + thTime);
                // console.log("jzTime = " + jzTime);

                // 比较时间
                if (thTime > jzTime) {
                  // selectedValues = ['1', '2', '3'];
                  this.form.incomingMaterials = '0';
                  // cphd = th.productThickness;
                  // cpkd = th.productWidth;
                } else {
                  // selectedValues = ['0', '1', '3'];
                  this.form.incomingMaterials = '1';
                  console.log(jz);
                  // cphd = jz.speAfterRoll;
                  // cpkd = jz.productWidth;
                }
                // this.filterOptions(selectedValues);
                this.form.productThickness = cphd;
                this.form.productWidth = cpkd;
              })
              .catch(error => {
                console.error("Error in requests:", error);
              });
          }
        }
      });
    },
    //合并方法
    // 合并方法
    async cysfun() {
      this.submitForm();
      let code = "";

      // 表单验证
      const valid = await new Promise(resolve => {
        this.$refs["form"].validate(valid => resolve(valid));
      });

      if (!valid) {
        return;
      }

      try {
        let cyspd = '';
        let cysprocess = '';
        let thisGx = '';
        let customObjects = null;
        const phpd = this.dict.type.sys_rz_ph.find(option => option.value === this.form.brand) || { label: 'defaultLH' };
        const pdstr = phpd.label.substring(0, 1);

        const qj = this.form.qualityJudgment;
        console.log("qj = " + qj);

        // 判断 qualityJudgment 类型
        if (qj === '0') {
          cyspd = '1';
          cysprocess = '10';
          thisGx = '已进入成品检验工序';
        } else if (qj === '1') {
          cyspd = '3';
          cysprocess = '8';
          thisGx = '已报废';
        } else if (qj === '4') {
          cyspd = '1';
          cysprocess = '9';
          thisGx = '已进入精轧工序';
        }else if (qj === '5' && pdstr === 'T') {
          cyspd = '1';
          cysprocess = '14';
          thisGx = '已进入精剪边工序';
        } else if (qj === '3') {
          cyspd = '1';
          cysprocess = '12';
          thisGx = '已进入改制工序';

          // 同步等待 listCpfp 返回
          const queryGz = {
            pageNum: 1,
            pageSize: 10,
            workCode: this.form.workorderCode,
            workorderCode: this.form.workorderCode
          };
          const response1 = await listGz(queryGz);
          const cpgz = response1.rows[response1.rows.length - 1];
          if(cpgz){
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
          }else{
            const response = await listSx(queryGz);
            const cpfp = response.rows[0];
            if (cpfp) {
              customObjects = {
                workorderId: cpfp.workorderId,
                workorderCode: cpfp.workorderCode,
                brand: cpfp.brand,
                batchNumber: cpfp.batchNumber,
                weight: cpfp.weightAfterCleaning,
                blankThickness: cpfp.thicknessAfterCleaning,
                blankWidth: cpfp.widthAfterCleaning,
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
              // console.log("cysresponse = ", response);
              // console.log("customObjects = ", customObjects);
            } else {
              this.$modal.msgError("未找到相关改制数据，无法提交！");
              return;
            }
          }
        }

        const scgdcys = {
          workId: this.form.workorderId,
          workStatus: cyspd,
          thisProcess: cysprocess
        };

        const response1 = await updateScgd(scgdcys);

        // 如果需要提交 addGz
        if (qj === '3' && customObjects) {
          const gzResponse = await addGz(customObjects);
          console.log("addGz 返回：", gzResponse);
        }

        this.$modal.msgSuccess(thisGx);
        this.open = false;
        this.getList();
      } catch (error) {
        console.error("处理异常：", error);
        this.$modal.msgError("操作失败，请检查控制台日志！");
      }
    },

    // 报废
    async scrap() {
      this.form.qualityJudgment = '1';
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
          thisProcess: '8'
        };

        const response1 = await updateScgd(scgdcys);  // 使用局部变量 scgdcys
        this.$modal.msgSuccess("已报废");
        this.open = false;
        this.getList();
      } catch (error) {
        console.error("Error adding addCpjy:", error);
      }
    },

    // 成品合格
    async finalSucess() {
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

      // 调用 addCpjy 方法
      try {
        // const response = await addCpjy(customObjects);
        // 确保在调用 updateScgd 时使用正确的 scgdcys 变量
        const scgdcys = {
          workId: this.form.workorderId,
          workStatus : '1',
          thisProcess: '10'
        };

        const response1 = await updateScgd(scgdcys);  // 使用局部变量 scgdcys
        this.$modal.msgSuccess("已进入成品检验工序");
        this.open = false;
        this.getList();
      } catch (error) {
        console.error("Error adding addCpjy:", error);
      }
    },


    // 退货件合格
    async thsucess() {
      this.form.qualityJudgment = '2';
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

      // 调用 addJz 方法
      try {

        // 确保在调用 updateScgd 时使用正确的 scgdcys 变量
        const scgdcys = {
          workId: this.form.workorderId,
          workStatus : '1',
          thisProcess: '9'
        };

        const response1 = await updateScgd(scgdcys);  // 使用局部变量 scgdcys
        // const response = await addJz(customObjects);
        this.$modal.msgSuccess("已进入精轧工序");
        this.open = false;
        this.getList();
      } catch (error) {
        console.error("Error adding addJz:", error);
      }
    },
    /** 查询酸洗列表 */
    getList() {
      this.loading = true;
      listSx(this.queryParams).then(response => {
        this.sxList = response.rows;
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
        picklingId: null,
        picklingCode: null,
        workorderId: null,
        workorderCode: null,
        brand: null,
        batchNumber: null,
        productStatus: null,
        productThickness: null,
        productWidth: null,
        picklingCount: null,
        incomingMaterials: null,
        cleaningEquipment: null,
        weightAfterCleaning: null,
        thicknessAfterCleaning: null,
        widthAfterCleaning: null,
        picklingTime: formattedTime,
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
      this.ids = selection.map(item => item.picklingId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加酸洗";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const picklingId = row.picklingId || this.ids
      getSx(picklingId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改酸洗";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.picklingId != null) {
            updateSx(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSx(this.form).then(response => {
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
      const picklingIds = row.picklingId || this.ids;
      this.$modal.confirm('是否确认删除酸洗编号为"' + picklingIds + '"的数据项？').then(function() {
        return delSx(picklingIds);
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
      this.download('md/sx/export', {
        ...this.queryParams
      }, `sx_${new Date().getTime()}.xlsx`)
    },
    //自动生成编码
    handleAutoGenChange(autoGenFlag){
      if(autoGenFlag){
        genCode('CAS_CODE').then(response =>{
          this.form.picklingCode = response;
        });
      }else{
        this.form.picklingCode = null;
      }
    }
  }
};
</script>
