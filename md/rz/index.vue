<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="熔铸单号" prop="castingCode">-->
<!--        <el-input-->
<!--          v-model="queryParams.castingCode"-->
<!--          placeholder="请输入熔铸单号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="生产工单" prop="workorderCode">
        <el-input
          v-model="queryParams.workorderCode"
          placeholder="请输入生产工单单号"
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
      <el-form-item label="拉铸日期" prop="castingData" >
        <el-date-picker clearable
          v-model="queryParams.castingData"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择拉铸日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="炉号" prop="furnaceNo" >
        <el-select v-model="queryParams.furnaceNo" placeholder="请选择牌号" clearable>
          <el-option
            v-for="dict in dict.type.sys_rz_lh"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="炉次" prop="furnaceHeat" >
        <el-input
          v-model="queryParams.furnaceHeat"
          placeholder="请输入炉次"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="员工" prop="userName" >
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入员工名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="品位" prop="grade" >-->
<!--        <el-select v-model="queryParams.grade" placeholder="请选择品位" clearable>-->
<!--          <el-option-->
<!--            v-for="dict in dict.type.sys_rz_pw"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
      <el-form-item label="批次号" prop="batchNumber">
        <el-input
          v-model="queryParams.batchNumber"
          placeholder="请输入批次号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="黄铜角料" prop="brassScrap">
        <el-input
          v-model="queryParams.brassScrap"
          placeholder="请输入黄铜角料"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="紫铜" prop="copper">
        <el-input
          v-model="queryParams.copper"
          placeholder="请输入紫铜"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="锌块" prop="zincBlock">
        <el-input
          v-model="queryParams.zincBlock"
          placeholder="请输入锌块"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="镀白料" prop="whitePlating">
        <el-input
          v-model="queryParams.whitePlating"
          placeholder="请输入镀白料"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投料重量" prop="weight">
        <el-input
          v-model="queryParams.weight"
          placeholder="请输入重量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="铸锭规格" prop="ingotSpecifications">
        <el-input
          v-model="queryParams.ingotSpecifications"
          placeholder="请输入铸锭规格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="来源" prop="sourceRz">
        <el-input
          v-model="queryParams.sourceRz"
          placeholder="请输入异常原因"
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
          v-hasPermi="['md:rz:add']"
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
          v-hasPermi="['md:rz:edit']"
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
          v-hasPermi="['md:rz:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['md:rz:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="modifiedRzList"
      @selection-change="handleSelectionChange"
      auto-resize="true"
    >
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="熔铸工序" align="center" prop="casId" />-->
<!--      <el-table-column label="单号" width="150" align="center" prop="castingCode" />-->
      <el-table-column label="生产工单" width="150" align="center" prop="workorderCode" />
      <el-table-column label="牌号" align="center" prop="brand">
      <template slot-scope="scope">
        <dict-tag :options="dict.type.sys_rz_ph" :value="scope.row.brand"/>
      </template>
      </el-table-column>
      <el-table-column label="拉铸日期" align="center" prop="castingData" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.castingData, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="炉号" align="center" prop="furnaceNo">
      <template slot-scope="scope">
        <dict-tag :options="dict.type.sys_rz_lh" :value="scope.row.furnaceNo"/>
      </template>
      </el-table-column>
      <el-table-column label="炉次" align="center" prop="furnaceHeat"/>
      <el-table-column label="员工" align="center" prop="usercode">
      <template slot-scope="scope">
        <dict-tag :options="dict.type.sys_cys_ygbh" :value="scope.row.usercode"/>
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
      <el-table-column label="黄铜角料" align="center" prop="brassScrap" />
      <el-table-column label="紫铜" align="center" prop="copper" />
      <el-table-column label="锌块" align="center" prop="zincBlock" />
      <el-table-column label="镀白料" align="center" prop="whitePlating" />
      <el-table-column label="电解铜" align="center" prop="electrolyticCopper" />
      <el-table-column label="投料重量" align="center" prop="weight" />
      <el-table-column label="铸锭规格" align="center" prop="ingotSpecifications" />
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
      <el-table-column label="锯切前重量" width="150" align="center" prop="beforeSawing" />
      <el-table-column label="锯切后重量" width="150" align="center" prop="afterSawing" />
      <el-table-column label="来源" width="150" align="center" prop="sourceRz"/>
      <el-table-column label="质量判定" align="center" prop="qualityJudgment" >
      <template slot-scope="scope">
        <dict-tag :options="dict.type.sys_rz_zlpd" :value="scope.row.qualityJudgment"/>
      </template>
      </el-table-column>
      <el-table-column label="异常原因" width="200" align="center" prop="abnormalCause" />

<!--      <el-table-column label="状态" align="center" prop="status">-->
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.sys_rz_type" :value="scope.row.status"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作员" align="center" prop="userName" />
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
            v-hasPermi="['md:rz:edit']"
            v-if = "scope.row.showButton"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['md:rz:remove']"
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

    <!-- 添加或修改熔铸对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1080px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
<!--          <el-col :span="8">-->
<!--            <el-form-item label="工单编号" prop="castingCode">-->
<!--              <el-input v-model="form.castingCode" placeholder="请输入工单编号" />-->
<!--            </el-form-item>-->
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
          <el-input v-model="form.workorderCode" placeholder="请选择生产工单" @change="pdScgd">
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
        <el-form-item label="拉铸日期" prop="castingData" >
          <el-date-picker clearable
            v-model="form.castingData"
            type="date"
            value-format="yyyy-MM-dd"
            @change="autoGenerateCode"
            placeholder="请选择拉铸日期">
          </el-date-picker>
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="炉号" prop="furnaceNo" >
          <el-select v-model="form.furnaceNo" placeholder="请输入炉号" @change="autoGenerateCode">
            <el-option
              v-for="dict in dict.type.sys_rz_lh"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="炉次" prop="furnaceHeat" >
          <el-input v-model="form.furnaceHeat" placeholder="请输入炉次" @change="autoGenerateCode"/>
        </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="员工编号" prop="usercode">
              <el-select v-model="form.usercode" placeholder="请选择员工编号" @change="autoGenerateCode">
                <el-option
                  v-for="dict in dict.type.sys_cys_ygbh"
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
        <el-form-item label="批次号" prop="batchNumber" >
          <el-input v-model="form.batchNumber" placeholder="自动生成" disabled/>
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="黄铜角料" prop="brassScrap">
          <el-input-number :min="0.00" v-model="form.brassScrap" placeholder="请输入黄铜角料" @change="addWeight"/>
          <el-span>&nbsp;Kg</el-span>
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="紫铜" prop="copper">
          <el-input-number :min="0.00" v-model="form.copper" placeholder="请输入紫铜" @change="addWeight"/>
          <el-span>&nbsp;Kg</el-span>
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="锌块" prop="zincBlock">
          <el-input-number :min="0.00" v-model="form.zincBlock" placeholder="请输入锌块" @change="addWeight"/>
          <el-span>&nbsp;Kg</el-span>
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="镀白料" prop="whitePlating">
          <el-input-number :min="0.00" v-model="form.whitePlating" placeholder="请输入镀白料" @change="addWeight"/>
          <el-span>&nbsp;Kg</el-span>
        </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="电解铜" prop="electrolyticCopper">
              <el-input-number :min="0.00" v-model="form.electrolyticCopper" placeholder="请输入电解铜" @change="addWeight"/>
              <el-span>&nbsp;Kg</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="投料重量" prop="weight">
          <el-input-number :min="0.00" v-model="form.weight" placeholder="请输入重量" />
          <el-span>&nbsp;Kg</el-span>
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="铸锭规格" prop="ingotSpecifications">
          <el-input v-model="form.ingotSpecifications" placeholder="请输入铸锭规格" />
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
              <el-span>&nbsp;PPM</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="铁" prop="fe">
              <el-input-number :min="0.00" v-model="form.fe" placeholder="请输入铁" />
              <el-span> PPM</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="铅" prop="pb">
              <el-input-number :min="0.00" v-model="form.pb" placeholder="请输入铅" />
              <el-span>&nbsp;PPM</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="铝" prop="ai">
              <el-input-number :min="0.00" v-model="form.ai" placeholder="请输入铝" />
              <el-span>&nbsp;PPM</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="镉" prop="cd">
              <el-input-number :min="0.00" v-model="form.cd" placeholder="请输入镉" />
              <el-span>&nbsp;PPM</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="锡" prop="sn">
              <el-input-number :min="0.00" v-model="form.sn" placeholder="请输入锡" />
              <el-span>&nbsp;PPM</el-span>
            </el-form-item>
          </el-col>
<!--          <el-col :span="8">-->
<!--            <el-form-item label="化验员" prop="technicianName">-->
<!--              <el-input v-model="form.technicianName" placeholder="请选择员工" >-->
<!--                <el-button slot="append" @click="handleUserSelect" icon="el-icon-search"></el-button>-->
<!--              </el-input>-->
<!--            </el-form-item>-->
<!--            <UserSingleSelect ref="userSelect" @onSelected="onUserSelected"></UserSingleSelect>-->
<!--          </el-col>-->
          <el-col :span="8">
            <el-form-item label="锯切前重量" prop="beforeSawing" label-width="92px">
              <el-input-number :min="0.00" v-model="form.beforeSawing" placeholder="请输入锯切前重量" />
              <el-span>&nbsp;Kg</el-span>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="锯切后重量" prop="afterSawing" label-width="92px">
              <el-input-number :min="0.00" v-model="form.afterSawing" placeholder="请输入锯切后重量" />
              <el-span>&nbsp;Kg</el-span>
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
          <el-col :span="8">
            <el-form-item label="操作员" prop="userName">
              <el-input v-model="form.userName" placeholder="请选择操作员" @change="autoGenerateCode">
                <el-button slot="append" @click="handleUserSelect" icon="el-icon-search"></el-button>
              </el-input>
            </el-form-item>
            <UserSingleSelect ref="userSelect" @onSelected="onUserSelected"></UserSingleSelect>
          </el-col>
          <el-col :span="8">
            <el-form-item label="来源" prop="sourceRz">
              <el-input v-model="form.sourceRz" placeholder="请填写来源" @change="autoGenerateCode"/>
            </el-form-item>
          </el-col>
<!--          <el-col :span="8">-->
<!--            <el-form-item label="状态" prop="stauts">-->
<!--              <el-select v-model="form.status" placeholder="请选择类型" disabled>-->
<!--                <el-option-->
<!--                  v-for="dict in dict.type.sys_rz_type"-->
<!--                  :key="dict.value"-->
<!--                  :label="dict.label"-->
<!--                  :value="dict.value"-->
<!--                ></el-option>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
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
import { listRz, getRz, delRz, addRz, updateRz } from "@/api/mes/md/rz";
import WorkorderSelect from "@/components/scgd/single.vue"
import WorkstationSelect from "@/components/workstationSelect/simpletableSingle.vue";
import UserSingleSelect from "@/components/userSelect/single.vue";
import ProtaskSelect from "@/components/TaskSelect/taskSelectSingle.vue";
import {genCode} from "@/api/system/autocode/rule";
import {addCbfx, listCbfx} from "@/api/mes/md/cbfx";
import {listScgd, updateScgd} from "@/api/mes/md/scgd";

export default {
  name: "Rz",
  components: {WorkorderSelect,WorkstationSelect,UserSingleSelect,ProtaskSelect},
  dicts: ['mes_order_status', 'mes_feedback_type', 'sys_rz_type', 'sys_rz_ph', "sys_rz_lh", "sys_rz_pw", "sys_rz_zlpd", "sys_cys_gx", "sys_cys_ygbh", "sys_cys_ly"],
  data() {
    return {
      // 筛选条件，假设是根据工单状态进行筛选
      filterCondition: {
        gdType: '0',  // 比如筛选状态为 '0-熔铸' 的工单
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
      // 熔铸表格数据
      rzList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        castingCode: null,        workorderId: null,        workorderCode: null,        brand: null,        usercode: null,        castingData: null,        furnaceNo: null,        furnaceHeat: null,        userId: null,        userName: null,        grade: null,        batchNumber: null,        brassScrap: null,        copper: null,        zincBlock: null,        whitePlating: null,        electrolyticCopper: null,        weight: null,        ingotSpecifications: null,        testingTime: null,        cu: null,        fe: null,        pb: null,        ai: null,        cd: null,        sn: null,        technicianId: null,        technicianName: null,        beforeSawing: null,        afterSawing: null,        qualityJudgment: null,    sourceRz:null,    abnormalCause: null,        status: null,      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        batchNumber: [
          { required: true, message: "批次号不能为空", trigger: "blur" }
        ],
        workorderCode: [
          { required: true, message: "生产工单号不能为空", trigger: "blur" }
        ],
        furnaceNo: [
          { required: true, message: "炉号不能为空", trigger: "blur" }
        ],
        furnaceHeat: [
          { required: true, message: "炉次不能为空", trigger: "blur" }
        ],
        usercode: [
          { required: true, message: "员工编号不能为空", trigger: "blur" }
        ],
        grade: [
          { required: true, message: "品位不能为空", trigger: "blur" }
        ],qualityJudgment: [
          { required: true, message: "质量判定不能为空", trigger: "blur" }
        ] }
    };
  },
  created() {
    this.getList();
  },
  computed: {
    modifiedRzList() {
      return this.rzList.map(item => {
        return {
          ...item,
          showButton: item.qualityJudgment == '' || item.qualityJudgment == null  // 根据 qualityJudgment 来设置 showButton 字段
        };
      });
    }
  },
  methods: {
    //值变化监听
    handleInput() {
        this.form.showSave = this.form.qualityJudgment != null
    },
    //值变化监听
    addWeight() {
      //黄铜角料
      const htjl = this.form.brassScrap;
      //紫铜
      const zt = this.form.copper;
      //锌块
      const xk = this.form.zincBlock;
      //镀白料
      const dbl = this.form.whitePlating;
      //电解铜
      const djt = this.form.electrolyticCopper;


      this.form.weight = (Number(htjl) + Number(zt) + Number(xk) + Number(dbl) + Number(djt)).toFixed(2);

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
        console.log(scgd);
        if(response.rows.length === 0){
          this.$modal.msgError("不存在此工单，请重新扫描！");
          this.form.workorderCode = null;
        }else{
          if(scgd.thisProcess !== '0' && scgd.workStatus !== '3'){
            const gxjd = this.dict.type.sys_cys_gx.find(option => option.value === scgd.thisProcess) || { label: 'defaultLH' };
            this.$modal.msgError("该工单当前工序为：" + gxjd.label + " 请重新扫描");
            this.form.workorderCode = null;
          }else if(scgd.thisProcess === '0' && scgd.workStatus !== '3'){
            this.form.workorderId = scgd.workId;
            this.form.workorderCode = scgd.workCode;
            this.form.brand = scgd.brand;
            this.form.batchNumber = scgd.batchNumber;
          }else if(scgd.workStatus === '3'){
            this.$modal.msgError("该工单当前已报废，请重新扫描");
            this.form.workorderCode = null;
          }
        }
      });
    },
    //生成批次号 规则：根据拉铸日期、炉号、炉次、员工编号、品位生成
    autoGenerateCode(value) {
      // 检查 castingData 是否有效
      if (!this.form.castingData || typeof this.form.castingData !== 'string') {
        console.error('Invalid castingData:', this.form.castingData); // 如果需要调试，可以输出错误
        return; // 避免执行后续逻辑
      }

      // 来源
      const ly = this.form.sourceRz;

      // 拉铸日期
      // const lzrq = this.form.castingData.split('-').join('');

      const lzrqStr = this.form.castingData; // "2025-11-25"
      let lzrq = "";

      if (lzrqStr) {
        const [year, month, day] = lzrqStr.split('-');
        lzrq = `${year.slice(-2)}${month}${day}`;
      }

      // 获取炉号，找不到时给默认值
      const lh = this.dict.type.sys_rz_lh.find(option => option.value === this.form.furnaceNo) || { label: '' };

      // 获取品位，找不到时给默认值
      const pw = this.dict.type.sys_rz_pw.find(option => option.value === this.form.grade) || { label: '' };

      // 炉次
      const lc = this.form.furnaceHeat;

      // 员工编号
      const usercode = this.dict.type.sys_cys_ygbh.find(option => option.value === this.form.usercode) || { label: '' };

      // console.log("usercode = " + usercode.label);

      // 检查所有必填项是否有效
      if (lzrq && lh && lc && usercode && pw) {
        // this.form.batchNumber = `${lzrq}${lh.label}${lc}${usercode.label}-${pw.label}`;
        this.form.batchNumber = `${ly}${lzrq}${lh.label}${lc}${usercode.label}`;
        // this.form.batchNumber = `${lzrq}${lh.label}${lc}${usercode.label}`;
        const querypd = {
          pageNum: 1,
          pageSize: 10,
          batchNumber: this.form.batchNumber
        }
        console.log(querypd);
        listRz(querypd).then(response => {
          console.log(response);
          if(response.rows.length > 0){
            this.$modal.msgError("批次号重复，请检查后重试！");
            this.form.batchNumber = "";
          }
        });
      }
    },
    /* 完成本工序，回写状态到生产工单中 */
    async cysfun() {
      this.submitForm();

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
        let cysendTime = '';
        if(this.form.qualityJudgment === '0'){
          cyspd = '1';
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
        }else if(this.form.qualityJudgment === '1'){
          cyspd = '3';
        }
        // 确保在调用 updateScgd 时使用正确的 scgdcys 变量
        const scgdcys = {
          workId : this.form.workorderId,
          workStatus : cyspd,
          thisProcess: '2',
          brand : this.form.brand,
          batchNumber : this.form.batchNumber,
          startTime: cysendTime
        };
        console.log(scgdcys);
        const response1 = await updateScgd(scgdcys);  // 使用局部变量 scgdcys
        console.log("updateScgd response:", response1);

        this.$modal.msgSuccess("已进入热轧工序");
        this.open = false;
        this.getList();
      } catch (error) {
        console.error("Error adding cbfx:", error);
      }
    },
    /** 查询熔铸列表 */
    getList() {
      this.loading = true;
      listRz(this.queryParams).then(response => {
        this.rzList = response.rows;
        this.total = response.total;
        this.loading = false;
        console.log(response.rows);
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
      const formattedDate = `${year}-${month}-${day}`;
      const formattedTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
      this.form = {
        workorderId: null,
        workorderCode: null,
        grade: null,
        castingCode: null,
        userName: this.$store.state.user.name,
        brand: null,
        castingData: formattedDate,
        furnaceNo: null,
        furnaceHeat: null,
        usercode: null,
        userId: this.$store.state.user.userId,
        batchNumber: null,
        brassScrap: null,
        copper: null,
        zincBlock: null,
        whitePlating: null,
        electrolyticCopper: null,
        weight: null,
        ingotSpecifications: null,
        testingTime: formattedTime,
        cu: null,
        fe: null,
        pb: null,
        ai: null,
        cd: null,
        sn: null,
        technicianId: null,
        technicianName: null,
        beforeSawing: null,
        afterSawing: null,
        qualityJudgment: null,
        abnormalCause: null,
        status : '0',
        sourceRz : null,
        buttonstatus: "PREPARE",
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
      this.ids = selection.map(item => item.casId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加熔铸";
      this.status = '0';
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const casId = row.casId || this.ids
      getRz(casId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改熔铸";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.casId != null) {
            updateRz(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRz(this.form).then(response => {
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
      const casIds = row.casId || this.ids;
      this.$modal.confirm('是否确认删除熔铸编号为"' + casIds + '"的数据项？').then(function() {
        return delRz(casIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
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
      }
    },
    //点击人员选择按钮
    handleUserSelect(){
      this.$refs.userSelect.showFlag = true;
    },
    //人员选择返回
    onUserSelected(row){
      this.form.userId = row.userId;
      // this.form.userName = row.userName;
      this.$set(this.form, 'userName', row.userName);
      this.$forceUpdate(); // 强制更新
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('md/rz/export', {
        ...this.queryParams
      }, `rz_${new Date().getTime()}.xlsx`)
    },
    //自动生成编码
    handleAutoGenChange(autoGenFlag){
      if(autoGenFlag){
        genCode('CAS_CODE').then(response =>{
          this.form.castingCode = response;
        });
      }else{
        this.form.castingCode = null;
      }
    }
  }
};
</script>
