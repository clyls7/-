<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="生产订单编码" label-width="100px" prop="porderCode">
        <el-input
          v-model="queryParams.porderCode"
          placeholder="请输入生产订单编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生产订单名称" label-width="100px" prop="porderName">
        <el-input
          v-model="queryParams.porderName"
          placeholder="请输入生产订单名称"
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
      <el-form-item label="订单来源" prop="orderSource">
        <el-select v-model="queryParams.orderSource" placeholder="请选择订单来源" clearable>
          <el-option
            v-for="dict in dict.type.mes_workorder_sourcetype"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="计划订单编号" label-width="100px" prop="plannedOrdersCode">
        <el-input
          v-model="queryParams.plannedOrdersCode"
          placeholder="请输入计划订单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工单数量" prop="workCount">
        <el-input
          v-model="queryParams.workCount"
          placeholder="请输入工单数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="需求时间" prop="demandTime">
        <el-date-picker clearable
          v-model="queryParams.demandTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择需求时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="产品编码" prop="itemCode">
        <el-input
          v-model="queryParams.itemCode"
          placeholder="请输入产品编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品名称" label-width="100px" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          placeholder="请输入产品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户编码" prop="clientCode">
        <el-input
          v-model="queryParams.clientCode"
          placeholder="请输入客户编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户名称" prop="clientName">
        <el-input
          v-model="queryParams.clientName"
          placeholder="请输入客户名称"
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
          v-hasPermi="['md:scdd:add']"
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
          v-hasPermi="['md:scdd:edit']"
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
          v-hasPermi="['md:scdd:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['md:scdd:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="modifiedRzList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="操作" width="150" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-circle-check"
            @click="cysXd(scope.row)"
            v-hasPermi="['md:scdd:update']"
            v-if = "scope.row.showButton"
          >下达</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['md:scdd:edit']"
            v-if = "scope.row.showButton"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['md:scdd:remove']"
            v-if = "scope.row.showButton"
          >删除</el-button>
        </template>
      </el-table-column>
      <el-table-column label="生产订单编码" width="150" align="center" prop="porderCode" >
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="text"
          @click="handleView(scope.row)"
          v-hasPermi="['md:scdd:query']"
        >{{scope.row.porderCode}}</el-button>
      </template>
      </el-table-column>
      <el-table-column label="生产订单名称" width="150" align="center" prop="porderName" />
      <el-table-column label="牌号" align="center" prop="brand">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_rz_ph" :value="scope.row.brand"/>
        </template>
      </el-table-column>
      <el-table-column label="订单来源" align="center" prop="orderSource">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mes_workorder_sourcetype" :value="scope.row.orderSource"/>
        </template>
      </el-table-column>
      <el-table-column label="计划订单编号" width="150" align="center" prop="plannedOrdersCode" />
      <el-table-column label="工单数量" align="center" prop="workCount" />
      <el-table-column label="需求时间" align="center" prop="demandTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.demandTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="产品编码" width="150" align="center" prop="itemCode" />
      <el-table-column label="产品名称" width="150" align="center" prop="itemName" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="客户编码" width="120" align="center" prop="clientCode" />
      <el-table-column label="客户名称" width="150" align="center" prop="clientName" />
      <el-table-column label="性能要求" width="250" align="center" prop="performanceReq" />
      <el-table-column label="质量要求" width="250" align="center" prop="qualityReq" />
      <el-table-column label="包装要求"width="250" align="center" prop="packageReq" />
      <el-table-column label="备注" width="250" align="center" prop="remark" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改生产订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
        <el-form-item label="生产订单编码" label-width="100px" prop="porderCode">
          <el-input v-model="form.porderCode" placeholder="请输入生产订单编码" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item  label-width="80">
              <el-switch v-model="autoGenFlag"
                         active-color="#13ce66"
                         active-text="自动生成"
                         @change="handleAutoGenChange(autoGenFlag)" v-if="optType != 'view' && form.buttonstatus =='PREPARE'">
              </el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="生产订单名称" label-width="100px" prop="porderName">
          <el-input v-model="form.porderName" placeholder="请输入生产订单名称" />
        </el-form-item>
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
        <el-form-item label="订单来源" prop="orderSource">
          <el-select v-model="form.orderSource" placeholder="请选择订单来源">
            <el-option
              v-for="dict in dict.type.mes_workorder_sourcetype"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="计划订单编号" label-width="100px" prop="plannedOrdersCode">
          <el-input v-model="form.plannedOrdersCode" placeholder="请输入计划订单编号" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="工单数量" prop="workCount">
          <el-input-number :min="1" v-model="form.workCount" placeholder="请输入工单数量" />
        </el-form-item>
          </el-col>
          <el-col :span="12">
        <el-form-item label="需求时间" prop="demandTime">
          <el-date-picker clearable
            v-model="form.demandTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择需求时间">
          </el-date-picker>
        </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="产品编码" prop="itemCode">
              <el-input v-model="form.itemCode" placeholder="请选择产品" >
                <el-button slot="append" @click="handleSelectProduct" icon="el-icon-search"></el-button>
              </el-input>
              <ItemSelect ref="itemSelect" @onSelected="onItemSelected" > </ItemSelect>
            </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="产品名称" prop="itemName">
          <el-input v-model="form.itemName" placeholder="请输入产品名称" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入单位" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
          <el-form-item label="客户编码" prop="clientCode">
            <el-input v-model="form.clientCode" placeholder="请选择客户" >
              <el-button slot="append" @click="handleSelectClient" icon="el-icon-search"></el-button>
            </el-input>
            <ClientSelect ref="clientSelect" @onSelected="onClientSelected" > </ClientSelect>
          </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="客户名称" prop="clientName">
          <el-input v-model="form.clientName" placeholder="请输入客户名称" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
          <el-form-item label="状态" prop="status">
            <el-select v-model="form.status" placeholder="请选择状态" >
              <el-option
                v-for="dict in dict.type.mes_order_status"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          </el-col>
          <el-col :span="24">
        <el-form-item label="性能要求" prop="performanceReq">
          <el-input v-model="form.performanceReq" type="textarea" placeholder="请输入内容" />
        </el-form-item>
          </el-col>
          <el-col :span="24">
        <el-form-item label="质量要求" prop="qualityReq">
          <el-input v-model="form.qualityReq" type="textarea" placeholder="请输入内容" />
        </el-form-item>
          </el-col>
          <el-col :span="24">
        <el-form-item label="包装要求" prop="packageReq">
          <el-input v-model="form.packageReq" type="textarea" placeholder="请输入内容" />
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
        <el-button type="primary" @click="submitForm" v-if = "form.showSave">保 存</el-button>
        <el-button @click="cancel" v-if = "form.showSave">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listScdd, getScdd, delScdd, addScdd, updateScdd } from "@/api/mes/md/scdd";
import WorkorderSelect from "@/components/workorderSelect/single.vue";
import WorkstationSelect from "@/components/workstationSelect/simpletableSingle.vue";
import UserSingleSelect from "@/components/userSelect/single.vue";
import ProtaskSelect from "@/components/TaskSelect/taskSelectSingle.vue";
import {genCode} from "@/api/system/autocode/rule";
import ItemSelect from "@/components/itemSelect/single.vue";
import ClientSelect from "@/components/clientSelect/single.vue";
import {dofinish} from "@/api/mes/md/scdd";
import {addScgd} from "@/api/mes/md/scgd";
import {getWorkorder} from "@/api/mes/pro/workorder";
import {addRz} from "@/api/mes/md/rz";


export default {
  name: "Scdd",
  components: {WorkorderSelect,WorkstationSelect,UserSingleSelect,ProtaskSelect, ItemSelect , ClientSelect},
  dicts: ['sys_rz_ph', 'mes_workorder_sourcetype', 'mes_order_status'],
  data() {
    return {
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
      // 生产订单表格数据
      scddList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        porderCode: null,   status: null,     porderName: null,        brand: null,        orderSource: null,        plannedOrdersCode: null,        workCount: null,        demandTime: null,     itemId: null  , itemCode: null,        itemName: null,        unit: null,        clientId: null,        clientCode: null,        clientName: null,        performanceReq: null,        qualityReq: null,        packageReq: null,      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  computed: {
    modifiedRzList() {
      return this.scddList.map(item => {
        return {
          ...item,
          showButton: item.status != 'FINISHED'  // 根据 status 来设置 showButton 字段
        };
      });
    }
  },
  methods: {
    // 查询明细按钮操作
    handleView(row){
      this.reset();
      const porderId = row.porderId || this.ids;
      getScdd(porderId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "查看订单信息";
        this.optType = "view";
        if(row.status == 'FINISHED'){
          this.form.showSave = false;
        }else{
          this.form.showSave = true;
        }
      });
    },
    cysXd(row){
      const porderIds = row.porderId || this.ids;
      const workCount = row.workCount;
      this.$modal.confirm('确认下达订单？一旦下达，此订单将无法修改').then(function() {
        return dofinish(porderIds) //完成订单
      }).then(() => {
        // 根据 workCount 值执行循环逻辑
        for (let i = 0; i < workCount; i++) {
          // 在这里调用向另外一张表插入数据的逻辑
          this.cysfun(row)
        }
        this.getList();
        this.$modal.msgSuccess("下达成功");
      }).catch(() => {});
    },
    /* 向生产工单中插入一条数据 */
    async cysfun(row) {
      let code = "";
      let code1 = "";

      // 禁用提交按钮，防止重复提交
      this.isSubmitting = true;

      // 获取 COSTA_CODE 和 CAS_CODE
      try {
        code = await genCode('COSTA_CODE');
        code1 = await genCode('CAS_CODE');
        console.log("Generated codes: ", code, code1);
      } catch (error) {
        console.error("Failed to generate code:", error);
        this.isSubmitting = false;  // 请求失败时恢复按钮
        return;  // 如果获取 code 失败，退出
      }

      // 转换为本地时间字符串（自动处理时区）
      const now = new Date();
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, '0'); // 补零
      const day = String(now.getDate()).padStart(2, '0'); // 补零
      const hours = String(now.getHours()).padStart(2, '0'); // 补零
      const minutes = String(now.getMinutes()).padStart(2, '0'); // 补零
      const seconds = String(now.getSeconds()).padStart(2, '0'); // 补零

      // 格式化为 YYYY-MM-DD HH:MM:SS
      const formattedTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;

      // 创建 cys 对象并合并
      this.cys = {
        // submitTime: new Date().toISOString(),
        submitTime: formattedTime,
        workStatus: '0',
        thisProcess: '0',
        startTime: '',
        qualityJudgment: '',
        workCode: code,
        workorderCode: code,
        castingCode: code1
      };

      // 合并 form 对象和 cys 对象
      const customObjects = {
        ...row,  // 使用已有的 row 对象（表单数据）
        ...this.cys    // 合并 cys 对象，覆盖同名字段
      };

      try {
        // 调用 addScgd 方法，插入生产工单并获取返回的 workId 和编码
        const response = await addScgd(customObjects);

        // 成功插入后关闭表单并刷新列表
        this.open = false;
        this.getList();

      } catch (error) {
        console.error("Error during data submission:", error);
      } finally {
        // 恢复提交按钮状态
        this.isSubmitting = false;
      }
    },
    /** 查询生产订单列表 */
    getList() {
      this.loading = true;
      listScdd(this.queryParams).then(response => {
        this.scddList = response.rows;
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
        porderId: null,
        porderCode: null,
        porderName: null,
        brand: null,
        orderSource: null,
        plannedOrdersCode: null,
        workCount: null,
        demandTime: null,
        itemId: null,
        itemCode: null,
        itemName: null,
        unit: null,
        clientId: null,
        clientCode: null,
        clientName: null,
        performanceReq: null,
        qualityReq: null,
        packageReq: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        buttonstatus: "PREPARE",
        status : null
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
      this.ids = selection.map(item => item.porderId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加生产订单";
      this.form.showSave = true;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const porderId = row.porderId || this.ids
      getScdd(porderId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改生产订单";
        this.form.showSave = true;
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.porderId != null) {
            updateScdd(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addScdd(this.form).then(response => {
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
      const porderIds = row.porderId || this.ids;
      this.$modal.confirm('是否确认删除生产订单编号为"' + porderIds + '"的数据项？').then(function() {
        return delScdd(porderIds);
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
    /** 导出按钮操作 */
    handleExport() {
      this.download('md/scdd/export', {
        ...this.queryParams
      }, `scdd_${new Date().getTime()}.xlsx`)
    },
    //自动生成编码
    handleAutoGenChange(autoGenFlag){
      if(autoGenFlag){
        genCode('CAS_CODE').then(response =>{
          this.form.porderCode = response;
        });
      }else{
        this.form.porderCode = null;
      }
    },
    handleSelectProduct(){
      this.$refs.itemSelect.showFlag = true;
    },
    handleSelectClient(){
      this.$refs.clientSelect.showFlag = true;
    },
    //物料选择弹出框
    onItemSelected(obj){
      if(obj != undefined && obj != null){
        this.form.itemId = obj.itemId;
        this.form.itemCode = obj.itemCode;
        this.form.itemName = obj.itemName;
        this.form.productSpc = obj.specification;
        this.form.unit = obj.unitOfMeasure;
      }
    },
    //客户选择弹出框
    onClientSelected(obj){
      if(obj != undefined && obj != null){
        this.form.clientId = obj.clientId;
        this.form.clientCode = obj.clientCode;
        this.form.clientName = obj.clientName;
      }
    },
  }
};
</script>
