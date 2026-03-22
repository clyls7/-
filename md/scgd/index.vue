<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="生产工单编码" label-width="100px" prop="workCode">
        <el-input
          v-model="queryParams.workCode"
          placeholder="请输入生产工单编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生产订单编码" label-width="100px" prop="porderCode">
        <el-input
          v-model="queryParams.porderCode"
          placeholder="请输入生产订单编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交时间" prop="submitTime">
        <el-date-picker clearable
          v-model="queryParams.submitTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择提交时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="工单状态" prop="workStatus">
        <el-select v-model="queryParams.workStatus" placeholder="请选择工单状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_sc_gdzt"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="当前工序" prop="thisProcess" >
        <el-select v-model="queryParams.thisProcess" placeholder="请选择当前工序" clearable>
          <el-option
            v-for="dict in dict.type.sys_cys_gx"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="开工时间" prop="startTime">
        <el-date-picker clearable
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择开工时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="完工时间" prop="endTime">
        <el-date-picker clearable
          v-model="queryParams.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择完工时间">
        </el-date-picker>
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
      <el-form-item label="产品名称" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          placeholder="请输入产品名称"
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
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['md:scgd:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['md:scgd:edit']"
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
          v-hasPermi="['md:scgd:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['md:scgd:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-printer"
          size="mini"
          @click="batchPrint"
        >打印</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-close"
          size="mini"
          @click="endingWork"
        >结束工单</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="scgdList" @selection-change="handleSelectionChange" >
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="生产工单编码" width="150" align="center" prop="workCode" fixed />-->

<!--      <el-table-column label="生产工单编码" width="150" align="center" prop="workCode" >-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="medium"-->
<!--            type="text"-->
<!--            @click="handleView(scope.row)"-->
<!--            v-hasPermi="['md:scgd:query']"-->
<!--          >{{scope.row.workCode}}</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="生产工单编码" width="200" align="center">
        <template slot-scope="scope">
          <div style="display: flex; align-items: center;">
            <span style="flex: 1; user-select: all;">{{ scope.row.workCode }}</span>
            <span> </span>
            <el-button
              size="medium"
              type="text"
              icon="el-icon-document-copy"
              @click="copyWorkCode(scope.row.workCode)"
              v-hasPermi="['md:scgd:query']"
            />
            <el-button
              size="medium"
              type="text"
              icon="el-icon-view"
              @click="handleView(scope.row)"
              v-hasPermi="['md:scgd:query']"
            />
          </div>
        </template>
      </el-table-column>

      <el-table-column label="生产订单编码" width="150" align="center" prop="porderCode" fixed />
      <el-table-column label="提交时间" align="center" prop="submitTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.submitTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工单状态" width="100" align="center" prop="workStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_sc_gdzt" :value="scope.row.workStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="当前工序" width="100" align="center" prop="thisProcess">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_cys_gx" :value="scope.row.thisProcess"/>
        </template>
      </el-table-column>
      <el-table-column label="开工时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="完工时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
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
      <el-table-column label="工单类型" align="center" prop="workOrderType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_cys_gdlx" :value="scope.row.workOrderType"/>
        </template>
      </el-table-column>
      <el-table-column label="需求时间" align="center" prop="demandTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.demandTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="产品编码" width="120" align="center" prop="itemCode" />
      <el-table-column label="产品名称" width="120" align="center" prop="itemName" />
      <el-table-column label="规格型号" align="center" prop="unit" />
      <el-table-column label="客户名称" width="150" align="center" prop="clientName" />
      <el-table-column label="性能要求" width="150" align="center" prop="performanceReq" />
      <el-table-column label="质量要求" width="150" align="center" prop="qualityReq" />
      <el-table-column label="包装要求" width="150" align="center" prop="packageReq" />
      <el-table-column label="备注" width="150" align="center" prop="remark" />
      <el-table-column label="操作" width="120" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['md:scgd:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['md:scgd:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-printer"
            @click="printContent(scope.row)"
          >打印</el-button>
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

    <!-- 添加或修改生产工单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
        <el-form-item label="生产工单编码" prop="workCode">
          <el-input v-model="form.workCode" placeholder="请输入生产工单编码" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="生产订单编码" prop="porderCode">
          <el-input v-model="form.porderCode" placeholder="请输入生产订单编码" />
        </el-form-item>
      </el-col>
        <el-col :span="8">
        <el-form-item label="生产订单名称" prop="porderName">
          <el-input v-model="form.porderName" placeholder="请输入生产订单名称" />
        </el-form-item>
        </el-col>
        <el-col :span="8">
        <el-form-item label="提交时间" prop="submitTime">
          <el-date-picker clearable
            v-model="form.submitTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择提交时间">
          </el-date-picker>
        </el-form-item>
        </el-col>
        <el-col :span="8">
        <el-form-item label="工单状态" prop="workStatus">
          <el-select v-model="form.workStatus" placeholder="请选择工单状态">
            <el-option
              v-for="dict in dict.type.sys_sc_gdzt"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        </el-col>
        <el-col :span="8">
        <el-form-item label="当前工序" prop="thisProcess">
          <el-select v-model="form.thisProcess" placeholder="请选择当前工序" clearable>
            <el-option
              v-for="dict in dict.type.sys_cys_gx"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        </el-col>
        <el-col :span="8">
        <el-form-item label="开工时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择开工时间">
          </el-date-picker>
        </el-form-item>
        </el-col>
        <el-col :span="8">
        <el-form-item label="完工时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择完工时间">
          </el-date-picker>
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
        <el-form-item label="批次号" prop="batchNumber">
          <el-input v-model="form.batchNumber" placeholder="请输入批次号" />
        </el-form-item>
        </el-col>
        <el-col :span="8">
        <el-form-item label="计划订单编号" prop="plannedOrdersCode">
          <el-input v-model="form.plannedOrdersCode" placeholder="请输入计划订单编号" />
        </el-form-item>
        </el-col>
        <el-col :span="8">
        <el-form-item label="工单类型" prop="workOrderType">
          <el-select v-model="form.workOrderType" placeholder="请选择工单类型">
            <el-option
              v-for="dict in dict.type.sys_cys_gdlx"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        </el-col>
        <el-col :span="8">
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
          <el-input v-model="form.itemCode" placeholder="请输入产品编码" />
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
        <el-form-item label="客户id" prop="clientId">
          <el-input v-model="form.clientId" placeholder="请输入客户id" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="客户编码" prop="clientCode">
          <el-input v-model="form.clientCode" placeholder="请输入客户编码" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="客户名称" prop="clientName">
          <el-input v-model="form.clientName" placeholder="请输入客户名称" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="性能要求" prop="performanceReq">
          <el-input v-model="form.performanceReq" type="textarea" placeholder="请输入内容" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="质量要求" prop="qualityReq">
          <el-input v-model="form.qualityReq" type="textarea" placeholder="请输入内容" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="包装要求" prop="packageReq">
          <el-input v-model="form.packageReq" type="textarea" placeholder="请输入内容" />
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
          </el-col>
      </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        &lt;!&ndash; 只保留工单类型 &ndash;&gt;-->
<!--        <el-form-item label="工单类型" prop="workOrderType">-->
<!--          <el-select v-model="form.workOrderType" placeholder="请选择工单类型">-->
<!--            <el-option-->
<!--              v-for="dict in dict.type.sys_cys_gdlx"-->
<!--              :key="dict.value"-->
<!--              :label="dict.label"-->
<!--              :value="dict.value"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->

    <el-dialog :visible.sync="dialogVisible" width="1200px" append-to-body >
<!--      <div class="container" style="position: relative;">-->
      <div ref="printContent" class="container" style="position: relative;">
        <!-- 二维码在左上角 -->
        <div style="position: absolute; top: -40px; left: 10px; z-index: 10; background: #fff; padding: 4px;">
          <img v-if="qrCodeUrl" :src="qrCodeUrl" alt="二维码" style="width: 100px; height: 100px;" />
        </div>
        <div style="position: absolute; top: 0px; left: 1000px; z-index: 10; background: #fff; padding: 4px;">
          <el-button ref="printButton" type="primary" size="medium" @click="handlePrint">打印</el-button>
        </div>
        <h2 class="title">耀泰铜业铜带生产工艺流程卡</h2>

        <!-- 牌号 & 批号 -->
        <el-table :data="[form]" border :show-header="false">
          <el-table-column label="生产工单编码" width="375">
            <template #default="{ row }">
              <span style="font-weight: bold;">{{ formatWorkNumber(row) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="牌号" width="373">
            <template #default="{ row }">
              <span style="font-weight: bold;">{{ formatBrand(row) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="批次号" width="371">
            <template #default="{ row }">
              <span style="font-weight: bold;">{{ formatBatchNumber(row) }}</span>
            </template>
          </el-table-column>
        </el-table>

        <!-- 基本信息表 -->
        <el-table :data="[form]" border>
          <el-table-column prop="specification" label="成品规格">{{form.unit}}</el-table-column>
          <el-table-column prop="performance" label="性能要求">{{form.performanceReq}}</el-table-column>
          <el-table-column prop="quality" label="质量要求">{{form.qualityReq}}</el-table-column>
          <el-table-column prop="package" label="包装要求">{{form.packageReq}}</el-table-column>
          <el-table-column prop="customer" label="客户">{{form.clientName}}</el-table-column>
          <el-table-column prop="deliveryDate" label="交货期"></el-table-column>
        </el-table>

        <!-- 原料配比 -->
        <el-table :data="[formYl]" border>
          <el-table-column prop="angle" label="角料">{{formYl.brassScrap}}</el-table-column>
          <el-table-column prop="copper" label="紫铜">{{formYl.copper}}</el-table-column>
          <el-table-column prop="zinc" label="锌块">{{formYl.zincBlock}}</el-table-column>
          <el-table-column prop="nickel" label="镍白料">{{formYl.whitePlating}}</el-table-column>
          <el-table-column prop="total" label="合计">{{formYl.weight}}</el-table-column>
          <el-table-column prop="castingSpec" label="铸锭规格">{{formYl.ingotSpecifications}}</el-table-column>
          <el-table-column prop="weight" label="重量KG">{{formYl.weight}}</el-table-column>
          <el-table-column prop="operator" label="操作者">{{formYl.userName}}</el-table-column>
          <el-table-column prop="castingDate" label="拉铸日期">{{formYl.castingData}}</el-table-column>
        </el-table>

        <!-- 化学成分 -->
        <el-table class="no-scroll" :data="[formHx]" border :show-header="false">
          <el-table-column label="化学成分" width="127">
            <span style="font-weight: bold;">化学成分</span>
          </el-table-column>
          <el-table-column width="744">
            <div v-html="formatKeyValuePairs"></div>
          </el-table-column>
          <el-table-column label="" width="124">
            <span style="font-weight: bold;">化验员</span>
          </el-table-column>
          <el-table-column label="化验员" width="124">
            <span style="font-weight: bold;">{{ formHx.userName }}</span>
          </el-table-column>
        </el-table>

        <!-- 热轧 -->
        <el-table class="no-scroll" :data="[formRz]" border >
          <el-table-column label="热" width="35">
            <span style="font-weight: bold;" >轧</span>
          </el-table-column>
          <el-table-column prop="heatfLocation" label="加热炉位置">{{formRz.heatfLocation}}</el-table-column>
          <el-table-column prop="heatTime" label="升温时间">{{formRz.heatTime}}</el-table-column>
          <el-table-column prop="rollTime" label="开轧时间">{{formRz.rollTime}}</el-table-column>
          <el-table-column prop="zlpd" label="质量情况">{{ formRz.zlpd }}</el-table-column>
          <el-table-column prop="heatTemperature" label="加热温度℃">{{formRz.heatTemperature}}</el-table-column>
          <el-table-column prop="userName" label="操作者">{{formRz.userName}}</el-table-column>
          <el-table-column prop="installationDate" label="装炉日期">{{formRz.installationDate}}</el-table-column>
        </el-table>

        <!-- 双面铣 -->
        <el-table class="no-scroll" :data="formSmx" border :span-method="spanMethod">
          <el-table-column label="双" width="35">
            <span style="font-weight: bold;" >面</span>
            <span style="font-weight: bold;" >铣</span>
          </el-table-column>
          <el-table-column prop="spBeforeMilling" label="铣前规格mm">
            <template slot-scope="scope">{{ scope.row.spBeforeMilling }}</template>
          </el-table-column>
          <el-table-column prop="spAfterMilling" label="铣后规格mm">
            <template slot-scope="scope">{{ scope.row.spAfterMilling }}</template>
          </el-table-column>
          <el-table-column label="质量情况" prop="qualityJudgment" >
            <template slot-scope="scope">
              {{ getQualityJudgment(scope.row.qualityJudgment) }}
            </template>
          </el-table-column>
          <el-table-column prop="spAfterWeight" label="重量KG">
            <template slot-scope="scope">{{ scope.row.spAfterWeight }}</template>
          </el-table-column>
          <el-table-column prop="userName" label="操作者">
            <template slot-scope="scope">{{ scope.row.userName }}</template>
          </el-table-column>
          <el-table-column prop="millingTime" label="日期">
            <template slot-scope="scope">{{ scope.row.millingTime }}</template>
          </el-table-column>
        </el-table>

        <!-- 开坯 -->
        <el-table class="no-scroll" :data="[formKp]" border >
          <el-table-column label="开" width="35">
            <span style="font-weight: bold;" >坯</span>
          </el-table-column>
          <el-table-column prop="deviceName" label="设备名称">{{formKp.deviceName}}</el-table-column>
          <el-table-column prop="spBeforeMilling" label="轧前规格mm">{{formKp.spBeforeMilling}}</el-table-column>
          <el-table-column prop="spAfterMilling" label="轧后规格mm">{{formKp.spAfterMilling}}</el-table-column>
          <el-table-column prop="zlpd" label="质量情况">{{ formKp.zlpd }}</el-table-column>
          <el-table-column prop="weight" label="重量KG">{{formKp.openAfterWeight}}</el-table-column>
          <el-table-column prop="userName" label="操作者">{{formKp.userName}}</el-table-column>
          <el-table-column prop="rollingTime" label="日期">{{formKp.rollingTime}}</el-table-column>
        </el-table>

        <!-- 精轧 -->
        <el-table class="no-scroll" :data="[formJz]" border >
          <el-table-column label="精" width="35">
            <span style="font-weight: bold;" >轧</span>
          </el-table-column>
          <el-table-column prop="finishrollEquipment" label="设备名称">{{formJz.finishrollEquipment}}</el-table-column>
          <el-table-column prop="speBeforeRoll" label="轧前规格mm">{{formJz.speBeforeRoll}}</el-table-column>
          <el-table-column prop="speAfterRoll" label="轧后规格mm">{{formJz.speAfterRoll}}</el-table-column>
          <el-table-column prop="zlpd" label="质量情况">{{ formJz.zlpd }}</el-table-column>
          <el-table-column prop="weight" label="重量KG">{{formJz.weightAfterRoll}}</el-table-column>
          <el-table-column prop="userName" label="操作者">{{formJz.userName}}</el-table-column>
          <el-table-column prop="finishrTime" label="日期">{{formJz.finishrTime}}</el-table-column>
        </el-table>

        <!-- 粗切边 -->
        <el-table class="no-scroll" :data="[formCqp]" border :span-method="spanMethod">
          <el-table-column label="粗" width="35">
            <span style="font-weight: bold;" >切</span>
            <span style="font-weight: bold;" >边</span>
          </el-table-column>
          <el-table-column prop="" label="切前规格mm">{{formCqp.cutBeforeMilling}}</el-table-column>
          <el-table-column prop="" label="切后规格mm">{{formCqp.cutAfterMilling}}</el-table-column>
          <el-table-column prop="zlpd" label="质量情况">{{ formCqp.zlpd }}</el-table-column>
          <el-table-column prop="weight" label="重量KG">{{formCqp.cutAfterWeight}}</el-table-column>
          <el-table-column prop="userName" label="操作者">{{formCqp.userName}}</el-table-column>
          <el-table-column prop="" label="日期">{{formCqp.cutTime}}</el-table-column>
        </el-table>

      </div>
    </el-dialog>


  </div>
</template>

<script>
import { listScgd, getScgd, delScgd, addScgd, updateScgd } from "@/api/mes/md/scgd";
import WorkorderSelect from "@/components/workorderSelect/single.vue";
import WorkstationSelect from "@/components/workstationSelect/simpletableSingle.vue";
import UserSingleSelect from "@/components/userSelect/single.vue";
import ProtaskSelect from "@/components/TaskSelect/taskSelectSingle.vue";
import ItemSelect from "@/components/itemSelect/single.vue";
import ClientSelect from "@/components/clientSelect/single.vue";
import QRCode from "qrcode";
import {getScdd} from "@/api/mes/md/scdd";
import {listRz} from "@/api/mes/md/rz";
import {listCbfx} from "@/api/mes/md/cbfx";
import {listRezha} from "@/api/mes/md/rezha";
import {listSmx} from "@/api/mes/md/smx";
import {listKp} from "@/api/mes/md/kp";
import {listJz} from "@/api/mes/md/jz";
import {listCqp} from "@/api/mes/md/cqp";

export default {
  name: "Scgd",
  components: {WorkorderSelect,WorkstationSelect,UserSingleSelect,ProtaskSelect, ItemSelect , ClientSelect},
  dicts: ['sys_rz_ph', 'sys_sc_gdzt', 'sys_cys_gx', 'sys_rz_zlpd', 'sys_smx_zlpd','sys_scgd_fpzt','sys_cpjy_zlpd','sys_cys_gdlx'],
  data() {
    return {
      qrCodeUrl: '',
      qrCodeData: "https://www.example.com", // 二维码的内容
      selectedRows: [],  // 用来存储选中的数据
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
      // 生产工单表格数据
      scgdList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //自定义的弹出层控制
      dialogVisible : false,
      //成分分析
      formatKeyValuePairs : null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        workCode: null,   allocationStatus:null,    workOrderType:null,     porderCode: null,        porderName: null,        submitTime: null,        workStatus: null,        thisProcess: null,        startTime: null,        endTime: null,        brand: null,        batchNumber: null,        plannedOrdersCode: null,        demandTime: null,        itemId: null,        itemCode: null,        itemName: null,        unit: null,        clientId: null,        clientCode: null,        clientName: null,        performanceReq: null,        qualityReq: null,        packageReq: null,      },
      // 表单参数
      form: {},
      formYl : {},
      formHx : {},
      formRz : {},
      formSmx: {},
      formKp: {},
      formJz: {},
      formCqp: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  props: {
    dialogVisible: Boolean,
  },
  watch: {
    dialogVisible(val) {
      if (val) {
        this.generateQRCode();
      }
    }
  },
  methods: {
    getBrandLabel(brandValue) {
      const phList = this.dict?.type?.sys_rz_ph || [];
      const match = phList.find(option => option.value === brandValue);
      return match ? match.label : brandValue; // 没匹配到就显示 value 自己
    },
    copyWorkCode(code) {
      const input = document.createElement('input');
      input.value = code;
      document.body.appendChild(input);
      input.select();
      document.execCommand('copy');
      document.body.removeChild(input);
      this.$message.success('工单号已复制');
    },
    async generateQRCode() {
      const workCodeData = JSON.stringify({
        workCode: this.form.workCode // ⬅️ 使用 this.form 里的字段
      });
      try {
        this.qrCodeUrl = await QRCode.toDataURL(workCodeData);
      } catch (err) {
        console.error('二维码生成失败:', err);
      }
    },
    endingWork() {
      if (this.selectedRows.length === 0) {
        this.$message.error("请先勾选要结束的工单!");
        return;
      }
      // 结束工单
      this.selectedRows.forEach(async (row) => {
        row.workStatus = 3;
        // console.log(row);
        this.$modal.confirm("是否确认结束选中的工单？")
          .then(() => {
            // 用户点击“确定”后执行的逻辑
            updateScgd(row).then(response => {
              this.$modal.msgSuccess("选中工单已结束");
            })
              .catch(() => {
                // 用户点击“取消”后执行的逻辑（可选）
                this.$modal.msgWarning("已取消操作");
              });
            this.open = false;
            this.getList();
          });
      });
    },

    // span-method 用于合并单元格
    spanMethod({row, column, rowIndex, columnIndex}) {
      if (columnIndex === 0) {  // 第一列“双面铣”列
        if (rowIndex === 0) {
          return {rowspan: this.formSmx.length, colspan: 1};  // 第一行合并所有行
        } else {
          return {rowspan: 0, colspan: 0};  // 后续行不显示
        }
      }
      return {rowspan: 1, colspan: 1};  // 其他列保持默认
    },
    getQualityJudgment(value) {
      const dict = this.dict.type.sys_smx_zlpd;  // 获取字典数据
      const entry = dict.find(item => item.value === value);  // 根据值查找对应的字典项
      return entry ? entry.label : value;  // 返回对应的标签，如果找不到则返回原值
    },
    formatBrand(row) {
      const brandValue = row.brand;
      const ph = this.dict.type.sys_rz_ph.find(option => option.value === brandValue) || {label: 'defaultLH'};
      return `牌号：${ph.label}`;
    },
    formatBatchNumber(row) {
      return `批次号：` + row.batchNumber;
    },
    formatWorkNumber(row) {
      return `生产工单编码：` + row.workCode;
    },
    // 查询明细按钮操作
    handleView(row) {
      this.reset();
      const workId = row.workId || this.ids;
      const queryRz = {
        pageNum: 1,
        pageSize: 10,
        workorderId: row.workId
      }

      listRz(queryRz).then(response => {
        if (response.rows.length > 0) {
          this.formYl = response.rows[0];
        }else{
          this.formYl = {
            brassScrap: null,
            workorderId: null,
            workorderCode: null,
            brand: null,
            batchNumber: null,
            copper: null,
            zincBlock: null,
            whitePlating: null,
            weight: null,
            ingotSpecifications: null,
            castingData: null,
            userId: null,
            userName: null,
            qualityJudgment: null,
            abnormalCause: null,
          }
        }
      });

      listJz(queryRz).then(response => {
        if (response.rows.length > 0) {
          this.formJz = response.rows[0];
          // console.log("cysformJz = ", this.formJz);
          const zlpd = this.dict.type.sys_cpjy_zlpd.find(option => option.value === this.formJz.qualityJudgment) || {label: 'defaultLH'};
          // console.log("cyszlpd = ", zlpd);
          // console.log("cyszlpd.label = ", zlpd.label);
          this.formJz.zlpd = zlpd.label;
        } else {
          this.formJz = {
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
            finishrTime: null,
            userId: null,
            userName: null,
            qualityJudgment: null,
            abnormalCause: null,
          }
        }
      });

      listCqp(queryRz).then(response => {
        if (response.rows.length > 0) {
          this.formCqp = response.rows[0];
          const zlpd = this.dict.type.sys_rz_zlpd.find(option => option.value === this.formCqp.qualityJudgment) || {label: 'defaultLH'};
          this.formCqp.zlpd = zlpd.label;
        } else {
          this.formCqp = {
            roughcutCode: null,
            workorderId: null,
            workorderCode: null,
            brand: null,
            batchNumber: null,
            deviceName: null,
            cutBeforeMilling: null,
            cutAfterMilling: null,
            weight: null,
            cutTime: null,
            userId: null,
            userName: null,
            qualityJudgment: null,
            abnormalCause: null,
          }
        }
      });

      listKp(queryRz).then(response => {
        if (response.rows.length > 0) {
          this.formKp = response.rows[0];
          const zlpd = this.dict.type.sys_rz_zlpd.find(option => option.value === this.formKp.qualityJudgment) || {label: 'defaultLH'};
          this.formKp.zlpd = zlpd.label;
        } else {
          this.formKp = {
            doublesidedCode: null,
            workorderId: null,
            workorderCode: null,
            brand: null,
            batchNumber: null,
            deviceName: null,
            spBeforeMilling: null,
            spAfterMilling: null,
            weight: null,
            rollingTime: null,
            userId: null,
            userName: null,
            qualityJudgment: null,
            abnormalCause: null,
          }
        }
      });

      listSmx(queryRz).then(response => {
        if (response.rows.length > 0) {
          this.formSmx = response.rows;
        } else {
          this.formSmx = {
            zlpd: null,
            dsmCode: null,
            workorderId: null,
            workorderCode: null,
            brand: null,
            batchNumber: null,
            millingCount: null,
            spBeforeMilling: null,
            spAfterMilling: null,
            weight: null,
            millingTime: null,
            userId: null,
            userName: null,
            qualityJudgment: null,
            abnormalCause: null,
          }
        }
      });

      listRezha(queryRz).then(response => {
        if (response.rows.length > 0) {
          this.formRz = response.rows[0];
          const zlpd = this.dict.type.sys_rz_zlpd.find(option => option.value === this.formRz.qualityJudgment) || {label: 'defaultLH'};
          this.formRz.zlpd = zlpd.label;
        } else {
          this.formRz = {
            zlpd: null,
            batchNumber: null,
            hotrollCode: null,
            workorderId: null,
            workorderCode: null,
            brand: null,
            heatfLocation: null,
            heatTime: null,
            rollTime: null,
            heatTemperature: null,
            installationDate: null,
            userId: null,
            userName: null,
            qualityJudgment: null,
            abnormalCause: null,
          };
        }
        console.log(this.formRz.zlpd);
      });

      const queryCffx = {
        pageNum: 1,
        pageSize: 10,
        workorderId: row.workId
      }

      listRz(queryCffx).then(response => {
        let cysCf = {};
        if (response.rows.length > 0) {
          this.formHx = response.rows[0];
          cysCf = {
            Cu: response.rows[0].cu,
            Fe: response.rows[0].fe,
            Pb: response.rows[0].pb,
            AI: response.rows[0].ai,
            Cd: response.rows[0].cd,
            Sn: response.rows[0].sn
          };
        } else {
          cysCf = {
            Cu: ' ',
            Fe: ' ',
            Pb: ' ',
            AI: ' ',
            Cd: ' ',
            Sn: ' '
          };
        }
        this.formatKeyValuePairs = Object.entries(cysCf)
          .map(([key, value]) => `<strong style="margin-right: 10px;">${key}:</strong><span style="margin-right: 30px;">${value}</span>`)
          .join('   ');
      });


      getScgd(workId).then(response => {
        this.form = response.data;
        this.dialogVisible = true;
        this.optType = "view";
      });
    },
    // 批量打印
    async batchPrint() {
      if (this.selectedRows.length === 0) {
        this.$message.error("请先勾选要打印的记录!");
        return;
      }

      // 为每条勾选的记录单独打印
      this.selectedRows.forEach(async (row) => {
        await this.printContent(row); // 对每个勾选的记录调用 printSingleOrder 打印
      });
    },

    // 打印内容
    printContent: async function (row) {
      const qrCodeData = JSON.stringify({
        workCode: row.workCode,
        porderCode: row.porderCode,
        brand: row.brand,
        batchNumber: row.batchNumber
      });
      console.log("qrCodeData" + qrCodeData)
      // 生成二维码
      const qrCodeUrl = await QRCode.toDataURL(qrCodeData);
      console.log("qrCodeUrl" + qrCodeUrl)
      const workCode = row.workCode || this.ids
      //const annealing_code = row.annealing_code || this.ids
      //  const tableContent = this.$refs.table.$el.querySelector('table').outerHTML;
      //const content = document.getElementById("printArea").innerHTML; // 获取打印区域内容
      const qrCodeImg = new Image();
      qrCodeImg.src = qrCodeUrl;
      qrCodeImg.onload = () => {
        const printWindow = window.open('', '', 'height=600,width=800');
        printWindow.document.write('<html><head><title>打印</title>');
        printWindow.document.write('<style>');
        printWindow.document.write(`
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }
    .container {
      display: flex;
      align-items: flex-start;
      gap: 20px;
    }
    .qrcode {
      flex-shrink: 0;
    }
    .info-table {
      border-collapse: collapse;
      width: auto;
    }
    .info-table th, .info-table td {
      border: 1px solid #000;
      padding: 8px;
      text-align: left;
    }
    .info-table th {
      background-color: #f2f2f2;
      white-space: nowrap;
    }
  `);
        printWindow.document.write('</style></head><body>');

        // 用 Flex 容器包住二维码 + 表格
        printWindow.document.write('<div class="container">');

        // 插入二维码
        printWindow.document.write('<div class="qrcode"><img src="' + qrCodeUrl + '" alt="二维码" style="width: 130px; height: 130px;" /></div>');

        // 插入表格
        printWindow.document.write('<table class="info-table">');
        printWindow.document.write('<tr><th>生产工单编码</th><td>' + row.workCode + '</td></tr>');
        printWindow.document.write('<tr><th>牌号</th><td>' + this.getBrandLabel(row.brand) + '</td></tr>');
        printWindow.document.write('<tr><th>批次号</th><td>' + row.batchNumber + '</td></tr>');
        printWindow.document.write('</table>');

        // 结束容器
        printWindow.document.write('</div>');

        printWindow.document.write('</body></html>');
        printWindow.document.close();
        printWindow.print();
      };
    },
      /** 查询生产工单列表 */
    getList() {
      this.loading = true;
      listScgd(this.queryParams).then(response => {
        this.scgdList = response.rows;
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
        workId: null,
        workCode: null,
        porderCode: null,
        porderName: null,
        submitTime: null,
        workStatus: null,
        thisProcess: null,
        allocationStatus:null,
        startTime: null,
        endTime: null,
        brand: null,
        workOrderType: null,
        batchNumber: null,
        plannedOrdersCode: null,
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
        updateTime: null
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
      this.selectedRows = selection;
      console.log("selectedRows---" + this.selectedRows);
      this.ids = selection.map(item => item.workId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加生产工单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const workId = row.workId || this.ids
      getScgd(workId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改生产工单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(async valid => {
        if (!valid) return;

        try {
          // 查询当前工单信息，根据工单号或工单ID
          const query = {
            workId: this.form.workId,      // 修改时可用
            workCode: this.form.workCode   // 新增或修改都可用
          };

          const resp = await listScgd(query);
          const rows = resp.rows || [];

          // 判断当前工单是否已经选择了工单类型
          // if (rows.length > 0 && rows[0].workOrderType) {
          //   this.$modal.msgWarning("当前工单已选择工单类型，不允许重复选择");
          //   return; // 阻止提交
          // }

          // 新增或修改
          if (this.form.workId != null) {
            updateScgd(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addScgd(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        } catch (err) {
          console.error("listScgd 查询失败", err);
          this.$modal.msgError("校验工单类型失败，请稍后重试");
        }
      });
    }

    ,
    /** 删除按钮操作 */
    handleDelete(row) {
      const workIds = row.workId || this.ids;
      this.$modal.confirm('是否确认删除生产工单编号为"' + workIds + '"的数据项？').then(function () {
        return delScgd(workIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('md/scgd/export', {
        ...this.queryParams
      }, `scgd_${new Date().getTime()}.xlsx`)
    },
    handlePrint() {
      // 安全获取所有可能未定义的变量
      const form = this.form || {};
      const formYl = this.formYl || {};
      const formHx = this.formHx || {};
      const formRz = this.formRz || {};
      const formKp = this.formKp || {};
      const formJz = this.formJz || {};
      const formCqp = this.formCqp || {};

      // 确保 formSmx 和 formCqp 是数组，即使为空或未定义
      const formSmx = Array.isArray(this.formSmx) ? this.formSmx : [];
      // const formCqp = Array.isArray(this.formCqp) ? this.formCqp : [];
      // 创建打印内容模板
      const printContent = `
    <div class="container" style="position: relative; width: 1200px; padding: 20px;">
      ${this.qrCodeUrl ? `
        <div style="position: absolute; top: -40px; left: 10px; z-index: 10; background: #fff; padding: 4px;">
          <img src="${this.qrCodeUrl}" alt="二维码" style="width: 100px; height: 100px;" />
        </div>
      ` : ''}

      <h2 style="text-align: center; margin-bottom: 20px;">耀泰铜业铜带生产工艺流程卡</h2>

      <!-- 工单信息 -->
      <table style="width: 100%; border-collapse: collapse; margin-bottom: 15px;">
        <tr>
          <td style="width: 375px; font-weight: bold; padding: 8px; border: 1px solid #000; text-align: center;">
            ${this.formatWorkNumber(form) || '-'}
          </td>
          <td style="width: 373px; font-weight: bold; padding: 8px; border: 1px solid #000; text-align: center;">
            ${this.formatBrand(form) || '-'}
          </td>
          <td style="width: 371px; font-weight: bold; padding: 8px; border: 1px solid #000; text-align: center;">
            ${this.formatBatchNumber(form) || '-'}
          </td>
        </tr>
      </table>

      <!-- 基本信息 -->
      <table style="width: 100%; border-collapse: collapse; margin-bottom: 15px;">
        <tr>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">成品规格</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">性能要求</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">质量要求</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">包装要求</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">客户</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">交货期</th>
        </tr>
        <tr>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${form.unit || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${form.performanceReq || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${form.qualityReq || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${form.packageReq || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${form.clientName || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${form.deliveryDate || '-'}</td>
        </tr>
      </table>

      <!-- 原料配比 -->
      <table style="width: 100%; border-collapse: collapse; margin-bottom: 15px;">
        <tr>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">角料</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">紫铜</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">锌块</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">镍白料</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">合计</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">铸锭规格</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">重量KG</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">操作者</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">拉铸日期</th>
        </tr>
        <tr>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formYl.brassScrap || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formYl.copper || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formYl.zincBlock || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formYl.whitePlating || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formYl.weight || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formYl.ingotSpecifications || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formYl.weight || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formYl.userName || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formYl.castingData || '-'}</td>
        </tr>
      </table>

      <!-- 化学成分 -->
      <table style="width: 100%; border-collapse: collapse; margin-bottom: 15px;">
        <tr>
          <td style="width: 127px; font-weight: bold; padding: 8px; border: 1px solid #000; text-align: center;">化学成分</td>
          <td style="width: 744px; padding: 8px; border: 1px solid #000; text-align: left;">
            ${this.formatKeyValuePairs || '-'}
          </td>
          <td style="width: 124px; font-weight: bold; padding: 8px; border: 1px solid #000; text-align: center;">化验员</td>
          <td style="width: 124px; padding: 8px; border: 1px solid #000; text-align: center;">
            ${formHx.technicianName || '-'}
          </td>
        </tr>
      </table>

      <!-- 热轧 -->
      <table style="width: 100%; border-collapse: collapse; margin-bottom: 15px;">
        <tr>
          <td rowspan="2" style="width: 35px; font-weight: bold; padding: 8px; border: 1px solid #000; text-align: center; writing-mode: vertical-lr; text-orientation: mixed;">热轧</td>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">加热炉位置</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">升温时间</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">开轧时间</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">质量情况</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">加热温度℃</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">操作者</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">装炉日期</th>
        </tr>
        <tr>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formRz.heatfLocation || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formRz.heatTime || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formRz.rollTime || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formRz.zlpd || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formRz.heatTemperature || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formRz.userName || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formRz.installationDate || '-'}</td>
        </tr>
      </table>

      <!-- 双面铣 -->
      <table style="width: 100%; border-collapse: collapse; margin-bottom: 15px;">
        <tr>
          <td rowspan="${formSmx.length + 1}" style="width: 35px; font-weight: bold; padding: 8px; border: 1px solid #000; text-align: center; writing-mode: vertical-lr; text-orientation: mixed;">双面铣</td>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">铣前规格mm</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">铣后规格mm</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">质量情况</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">重量KG</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">操作者</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">日期</th>
        </tr>
        ${formSmx.length > 0 ? formSmx.map(item => `
          <tr>
            <td style="padding: 8px; border: 1px solid #000; text-align: center;">${item.spBeforeMilling || '-'}</td>
            <td style="padding: 8px; border: 1px solid #000; text-align: center;">${item.spAfterMilling || '-'}</td>
            <td style="padding: 8px; border: 1px solid #000; text-align: center;">${this.getQualityJudgment(item.qualityJudgment) || '-'}</td>
            <td style="padding: 8px; border: 1px solid #000; text-align: center;">${item.spAfterWeight || '-'}</td>
            <td style="padding: 8px; border: 1px solid #000; text-align: center;">${item.userName || '-'}</td>
            <td style="padding: 8px; border: 1px solid #000; text-align: center;">${item.millingTime || '-'}</td>
          </tr>
        `).join('') : `
          <tr>
            <td colspan="6" style="padding: 8px; border: 1px solid #000; text-align: center;">暂无数据</td>
          </tr>
        `}
      </table>

      <!-- 开坯 -->
      <table style="width: 100%; border-collapse: collapse; margin-bottom: 15px;">
        <tr>
          <td rowspan="2" style="width: 35px; font-weight: bold; padding: 8px; border: 1px solid #000; text-align: center; writing-mode: vertical-lr; text-orientation: mixed;">开坯</td>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">设备名称</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">轧前规格mm</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">轧后规格mm</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">质量情况</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">重量KG</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">操作者</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">日期</th>
        </tr>
        <tr>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formKp.deviceName || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formKp.spBeforeMilling || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formKp.spAfterMilling || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formKp.zlpd || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formKp.openAfterWeight || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formKp.userName || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formKp.rollingTime || '-'}</td>
        </tr>
      </table>

      <!-- 精轧 -->
      <table style="width: 100%; border-collapse: collapse; margin-bottom: 15px;">
        <tr>
          <td rowspan="2" style="width: 35px; font-weight: bold; padding: 8px; border: 1px solid #000; text-align: center; writing-mode: vertical-lr; text-orientation: mixed;">精轧</td>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">设备名称</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">轧前规格mm</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">轧后规格mm</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">质量情况</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">重量KG</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">操作者</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">日期</th>
        </tr>
        <tr>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formJz.finishrollEquipment || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formJz.speBeforeRoll || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formJz.speAfterRoll || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formJz.zlpd || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formJz.weightAfterRoll || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formJz.userName || '-'}</td>
          <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formJz.finishrTime || '-'}</td>
        </tr>
      </table>

      <!-- 粗切边 -->
      <table style="width: 100%; border-collapse: collapse;">
        <tr>
          <td rowspan="2" style="width: 35px; font-weight: bold; padding: 8px; border: 1px solid #000; text-align: center; writing-mode: vertical-lr; text-orientation: mixed;">粗切边</td>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">切前规格mm</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">切后规格mm</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">质量情况</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">重量KG</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">操作者</th>
          <th style="padding: 8px; border: 1px solid #000; text-align: center; background: #f5f5f5;">日期</th>
        </tr>
          <tr>
            <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formCqp.cutBeforeMilling || '-'}</td>
            <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formCqp.cutAfterMilling || '-'}</td>
            <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formCqp.zlpd || '-'}</td>
            <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formCqp.cutAfterWeight || '-'}</td>
            <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formCqp.userName || '-'}</td>
            <td style="padding: 8px; border: 1px solid #000; text-align: center;">${formCqp.cutTime || '-'}</td>
          </tr>
<!--          <tr>-->
<!--            <td colspan="6" style="padding: 8px; border: 1px solid #000; text-align: center;">暂无数据</td>-->
<!--          </tr>-->
      </table>
    </div>
  `;

      // 打开打印窗口
      const printWindow = window.open('', '_blank');
      printWindow.document.write(`
    <html>
      <head>
        <title>打印</title>
        <style>
          body { font-family: sans-serif; padding: 20px; }
          table { border-collapse: collapse; width: 100%; }
          th, td { border: 1px solid #ccc; padding: 6px; text-align: center; font-size: 12px; }
          h2 { text-align: center; }
          img { max-width: 100px; }
        </style>
      </head>
      <body>${printContent}</body>
    </html>
  `);

      printWindow.document.close();
      setTimeout(() => {
        printWindow.print();
        printWindow.close();
      }, 500);
    }


  }
};
</script>

<style scoped>
.container {
  padding: 20px;
  background: #fff;
}
.title {
  font-size: 20px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;
}

.no-scroll .el-table__header tr th {
  padding: 0 !important;
}

/* 调整表头第一个单元格样式 */
.no-scroll .el-table__header th:first-child .cell {
  height: 84px; /* 两倍默认行高 */
  display: flex;
  align-items: center;
  justify-content: center;
  border-right: 1px solid #dfe6ec; /* 保留右边框 */
}

/* 隐藏数据行第一列 */
.no-scroll .el-table__body td:first-child {
  display: none; /* 隐藏数据行第一列 */
}

</style>
