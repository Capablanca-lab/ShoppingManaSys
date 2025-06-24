<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="83px">
      <el-form-item label="销售记录ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入销售记录ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="售货机ID" prop="vemId">
        <el-input
          v-model="queryParams.vemId"
          placeholder="请输入售货机ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="售出件数" prop="soldQuantity">
        <el-input
          v-model="queryParams.soldQuantity"
          placeholder="请输入售出件数"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="剩余数量" prop="remainingQuantity">
        <el-input
          v-model="queryParams.remainingQuantity"
          placeholder="请输入剩余数量"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总利润" prop="totalProfit">
        <el-input
          v-model="queryParams.totalProfit"
          placeholder="请输入总利润"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否售罄" prop="isSoldOut">
        <el-select v-model="queryParams.isSoldOut" placeholder="请选择是否售罄" clearable>
          <el-option
            v-for="dict in sell_out"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker clearable
          v-model="queryParams.createdTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updatedTime">
        <el-date-picker clearable
          v-model="queryParams.updatedTime"
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
          v-hasPermi="['vemSys:sales:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['vemSys:sales:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['vemSys:sales:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['vemSys:sales:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="salesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="销售记录ID" align="center" prop="id" />
      <el-table-column label="售货机ID" align="center" prop="vemId" />
      <el-table-column label="商品名称" align="center" prop="productName" />
      <el-table-column label="售出件数" align="center" prop="soldQuantity" />
      <el-table-column label="剩余数量" align="center" prop="remainingQuantity" />
      <el-table-column label="总利润" align="center" prop="totalProfit" />
      <el-table-column label="是否售罄" align="center" prop="isSoldOut">
        <template #default="scope">
          <dict-tag :options="sell_out" :value="scope.row.isSoldOut"/>
        </template>
      </el-table-column>
      <el-table-column label="扩展字段" align="center" prop="extendInfo" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updatedTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['vemSys:sales:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['vemSys:sales:remove']">删除</el-button>
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

    <!-- 添加或修改售货机单商品销售信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="salesRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="售货机ID" prop="vemId">
          <el-input v-model="form.vemId" placeholder="请输入售货机ID" />
        </el-form-item>
        <el-form-item label="商品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="售出件数" prop="soldQuantity">
          <el-input v-model="form.soldQuantity" placeholder="请输入售出件数" />
        </el-form-item>
        <el-form-item label="剩余数量" prop="remainingQuantity">
          <el-input v-model="form.remainingQuantity" placeholder="请输入剩余数量" />
        </el-form-item>
        <el-form-item label="总利润" prop="totalProfit">
          <el-input v-model="form.totalProfit" placeholder="请输入总利润" />
        </el-form-item>
        <el-form-item label="是否售罄" prop="isSoldOut">
          <el-select v-model="form.isSoldOut" placeholder="请选择是否售罄">
            <el-option
              v-for="dict in sell_out"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建时间" prop="createdTime">
          <el-date-picker clearable
            v-model="form.createdTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updatedTime">
          <el-date-picker clearable
            v-model="form.updatedTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择更新时间">
          </el-date-picker>
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

<script setup name="Sales">
import { listSales, getSales, delSales, addSales, updateSales } from "@/api/vemSys/sales";

const { proxy } = getCurrentInstance();
const { sell_out } = proxy.useDict('sell_out');

const salesList = ref([]);
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
    vemId: null,
    productName: null,
    soldQuantity: null,
    remainingQuantity: null,
    totalProfit: null,
    isSoldOut: null,
    extendInfo: null,
    createdTime: null,
    updatedTime: null
  },
  rules: {
    vemId: [
      { required: true, message: "售货机ID不能为空", trigger: "blur" }
    ],
    productName: [
      { required: true, message: "商品名称不能为空", trigger: "blur" }
    ],
    soldQuantity: [
      { required: true, message: "售出件数不能为空", trigger: "blur" }
    ],
    remainingQuantity: [
      { required: true, message: "剩余数量不能为空", trigger: "blur" }
    ],
    totalProfit: [
      { required: true, message: "总利润不能为空", trigger: "blur" }
    ],
    isSoldOut: [
      { required: true, message: "是否售罄不能为空", trigger: "change" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询售货机单商品销售信息列表 */
function getList() {
  loading.value = true;
  listSales(queryParams.value).then(response => {
    salesList.value = response.rows;
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
    vemId: null,
    productId: null,
    productName: null,
    soldQuantity: null,
    remainingQuantity: null,
    totalProfit: null,
    isSoldOut: null,
    extendInfo: null,
    createdTime: null,
    updatedTime: null
  };
  proxy.resetForm("salesRef");
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
  title.value = "添加售货机单商品销售信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getSales(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改售货机单商品销售信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["salesRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateSales(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addSales(form.value).then(response => {
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
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除售货机单商品销售信息编号为"' + _ids + '"的数据项？').then(function() {
    return delSales(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('vemSys/sales/export', {
    ...queryParams.value
  }, `sales_${new Date().getTime()}.xlsx`)
}

getList();
</script>
