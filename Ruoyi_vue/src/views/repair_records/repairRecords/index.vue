<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="维修记录id" prop="recordId">
        <el-input
          v-model="queryParams.recordId"
          placeholder="请输入维修记录id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="售货机id" prop="vemId">
        <el-input
          v-model="queryParams.vemId"
          placeholder="请输入售货机id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="售货机位置" prop="vemLocation">
        <el-input
          v-model="queryParams.vemLocation"
          placeholder="请输入售货机位置"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="维修者" prop="repairers">
        <el-input
          v-model="queryParams.Repairers"
          placeholder="请输入维修者"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="维修原因" prop="repairIssues">
        <el-input
          v-model="queryParams.repairIssues"
          placeholder="请输入维修原因"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否维修完成" prop="repairCompelete">
        <el-select v-model="queryParams.repairCompelete" placeholder="请选择是否维修完成" clearable>
          <el-option
            v-for="dict in sys_repair_compelete"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createtime">
        <el-date-picker clearable
          v-model="queryParams.createtime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updatetime">
        <el-date-picker clearable
          v-model="queryParams.updatetime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['repair_records:repairRecords:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['repair_records:repairRecords:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['repair_records:repairRecords:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['repair_records:repairRecords:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="repairRecordsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="维修记录id" align="center" prop="recordId" />
      <el-table-column label="售货机id" align="center" prop="vemId" />
      <el-table-column label="售货机位置" align="center" prop="vemLocation" />
      <el-table-column label="维修者" align="center" prop="repairers" />
      <el-table-column label="维修原因" align="center" prop="repairIssues" />
      <el-table-column label="是否维修完成" align="center" prop="repairCompelete">
        <template #default="scope">
          <dict-tag :options="sys_repair_compelete" :value="scope.row.repairCompelete"/>
        </template>
      </el-table-column>
      <el-table-column label="扩展字段" align="center" prop="extendField" />
      <el-table-column label="创建时间" align="center" prop="createtime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updatetime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['repair_records:repairRecords:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['repair_records:repairRecords:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改售货机维修记录对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="repairRecordsRef" :model="form" :rules="rules" label-width="99px">
        <el-form-item label="维修记录id" prop="recordId">
          <el-input v-model="form.recordId" placeholder="请输入维修记录id" />
        </el-form-item>
        <el-form-item label="售货机id" prop="vemId">
          <el-input v-model="form.vemId" placeholder="请输入售货机id" />
        </el-form-item>
        <el-form-item label="售货机位置" prop="vemLocation">
          <el-input v-model="form.vemLocation" placeholder="请输入售货机位置" />
        </el-form-item>
        <el-form-item label="维修者" prop="Repairers">
          <el-input v-model="form.repairers" placeholder="请输入维修者" />
        </el-form-item>
        <el-form-item label="维修原因" prop="repairIssues">
          <el-input v-model="form.repairIssues" placeholder="请输入维修原因" />
        </el-form-item>
        <el-form-item label="是否维修完成" prop="repairCompelete">
          <el-select v-model="form.repairCompelete" placeholder="请选择是否维修完成">
            <el-option
              v-for="dict in sys_repair_compelete"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="RepairRecords">
import { listRepairRecords, getRepairRecords, delRepairRecords, addRepairRecords, updateRepairRecords } from "@/api/repair_records/repairRecords";

const { proxy } = getCurrentInstance();
const { sys_repair_compelete } = proxy.useDict('sys_repair_compelete');

const repairRecordsList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    recordId: null,
    vemId: null,
    vemLocation: null,
    repairers: null,
    repairIssues: null,
    repairCompelete: null,
    extendField: null,
    createtime: null,
    updatetime: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询售货机维修记录列表 */
function getList() {
  loading.value = true;
  listRepairRecords(queryParams.value).then(response => {
    repairRecordsList.value = response.rows;
    total.value = response.total;
    loading.value = false;
    console.log(repairRecordsList.value);
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    recordId: null,
    vemId: null,
    vemLocation: null,
    repairers: null,
    repairIssues: null,
    repairCompelete: null,
    extendField: null,
    createtime: null,
    updatetime: null
  };
  proxy.resetForm("repairRecordsRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.recordId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加售货机维修记录";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _recordId = row.recordId || ids.value
  getRepairRecords(_recordId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改售货机维修记录";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["repairRecordsRef"].validate(valid => {
    if (valid) {
      if (form.value.recordId != null) {
        updateRepairRecords(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addRepairRecords(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _recordIds = row.recordId || ids.value;
  proxy.$modal.confirm('是否确认删除售货机维修记录编号为"' + _recordIds + '"的数据项？').then(function() {
    return delRepairRecords(_recordIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('repair_records/repairRecords/export', {
    ...queryParams.value
  }, `repairRecords_${new Date().getTime()}.xlsx`)
}

getList();
</script>
