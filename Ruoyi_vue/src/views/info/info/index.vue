<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="110px">
      <el-form-item label="售货机id" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入售货机id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="售货机位置" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入售货机位置"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="售货机状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择售货机状态" clearable>
          <el-option
            v-for="dict in vem_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="售货机公司品牌" prop="version">
        <el-input
          v-model="queryParams.version"
          placeholder="请输入售货机公司品牌"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上架货物供应商" prop="suppliers">
        <el-select v-model="queryParams.suppliers" placeholder="请选择上架货物供应商" clearable>
          <el-option
            v-for="dict in supplier"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="建立时间" prop="createdTime">
        <el-date-picker clearable
          v-model="queryParams.createdTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择建立时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="最后维护时间" prop="lastMaintenanceDate">
        <el-date-picker clearable
          v-model="queryParams.lastMaintenanceDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择最后维护时间">
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
          v-hasPermi="['info:info:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['info:info:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['info:info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['info:info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="售货机id" align="center" prop="id" />
      <el-table-column label="售货机位置" align="center" prop="location" />
      <el-table-column label="售货机状态" align="center" prop="status">
        <template #default="scope">
          <dict-tag :options="vem_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="售货机公司品牌" align="center" prop="version" />
      <el-table-column label="上架货物供应商" align="center" prop="suppliers">
        <template #default="scope">
          <dict-tag :options="supplier" :value="scope.row.suppliers"/>
        </template>
      </el-table-column>
      <el-table-column label="建立时间" align="center" prop="createdTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后维护时间" align="center" prop="lastMaintenanceDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.lastMaintenanceDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['info:info:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['info:info:remove']">删除</el-button>
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

    <!-- 添加或修改售货机管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="infoRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="售货机位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入售货机位置" />
        </el-form-item>
        <el-form-item label="售货机状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择售货机状态">
            <el-option
              v-for="dict in vem_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="售货机公司品牌" prop="version">
          <el-input v-model="form.version" placeholder="请输入售货机公司品牌" />
        </el-form-item>
        <el-form-item label="上架货物供应商" prop="suppliers">
          <el-select v-model="form.suppliers" placeholder="请选择上架货物供应商">
            <el-option
              v-for="dict in supplier"
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

<script setup name="Info">
import { listInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/info/info";

const { proxy } = getCurrentInstance();
const { supplier, vem_status } = proxy.useDict('supplier', 'vem_status');

const infoList = ref([]);
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
    id: null,
    location: null,
    status: null,
    version: null,
    suppliers: null,
    createdTime: null,
    lastMaintenanceDate: null,
  },
  rules: {
    id: [
      { required: true, message: "售货机id不能为空", trigger: "blur" }
    ],
    location: [
      { required: true, message: "售货机位置不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "售货机状态不能为空", trigger: "change" }
    ],
    version: [
      { required: true, message: "售货机公司品牌不能为空", trigger: "blur" }
    ],
    suppliers: [
      { required: true, message: "供货商不能为空", trigger: "change" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询售货机管理列表 */
function getList() {
  loading.value = true;
  listInfo(queryParams.value).then(response => {
    infoList.value = response.rows;
    total.value = response.total;
    loading.value = false;
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
    id: null,
    location: null,
    status: null,
    version: null,
    suppliers: null,
    createdTime: null,
    lastMaintenanceDate: null,
    extendfield: null,
    comment: null
  };
  proxy.resetForm("infoRef");
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
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加售货机管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getInfo(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改售货机管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["infoRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateInfo(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        form.value.createdTime   = getNowFormatDate();
        addInfo(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

//获取当前日期函数
function getNowFormatDate() {

  let date = new Date(),
    obj = {
      year: date.getFullYear(), //获取完整的年份(4位)
      month: date.getMonth() + 1, //获取当前月份(0-11,0代表1月)
      strDate: date.getDate(), // 获取当前日(1-31)
      // week: '星期' + '日一二三四五六'.charAt(date.getDay()), //获取当前星期几(0 ~ 6,0代表星期天)
      hour: date.getHours(), //获取当前小时(0 ~ 23)
      minute: date.getMinutes(), //获取当前分钟(0 ~ 59)
      second: date.getSeconds() //获取当前秒数(0 ~ 59)
    }
  
  Object.keys(obj).forEach(key => {
    if (obj[key] < 10) obj[key] = `0${obj[key]}`
    // console.log(obj[key])
  })

  return `${obj.year}-${obj.month}-${obj.strDate} ${obj.hour}:${obj.minute}:${obj.second}`
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除售货机管理编号为"' + _ids + '"的数据项？').then(function() {
    return delInfo(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('info/info/export', {
    ...queryParams.value
  }, `info_${new Date().getTime()}.xlsx`)
}

getList();
</script>
