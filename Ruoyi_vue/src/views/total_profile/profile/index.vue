<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="83px">
      <el-form-item label="售货机id" prop="vemId">
        <el-input
          v-model="queryParams.vemId"
          placeholder="请输入售货机id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="售出总件数" prop="soldQuantity">
        <el-input
          v-model="queryParams.soldQuantity"
          placeholder="请输入售出总件数"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="剩余总数量" prop="remainingQuantity">
        <el-input
          v-model="queryParams.remainingQuantity"
          placeholder="请输入剩余总数量"
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
          v-hasPermi="['total_profile:profile:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['total_profile:profile:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['total_profile:profile:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['total_profile:profile:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="profileList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="售货机id" align="center" prop="vemId" />
      <el-table-column label="售出总件数" align="center" prop="soldQuantity" />
      <el-table-column label="剩余总数量" align="center" prop="remainingQuantity" />
      <el-table-column label="总利润" align="center" prop="totalProfit" />
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
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['total_profile:profile:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['total_profile:profile:remove']">删除</el-button>
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

    <!-- 添加或修改售货机利润对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="profileRef" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="售货机id" prop="vemId">
          <el-input v-model="form.vemId" placeholder="请输入售货机id" />
        </el-form-item>
        <el-form-item label="售出总件数" prop="soldQuantity">
          <el-input v-model="form.soldQuantity" placeholder="请输入售出总件数" />
        </el-form-item>
        <el-form-item label="剩余总数量" prop="remainingQuantity">
          <el-input v-model="form.remainingQuantity" placeholder="请输入剩余总数量" />
        </el-form-item>
        <el-form-item label="总利润" prop="totalProfit">
          <el-input v-model="form.totalProfit" placeholder="请输入总利润" />
        </el-form-item>
        <el-divider content-position="center">商品信息1信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="Plus" @click="handleAddProductInfo">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="Delete" @click="handleDeleteProductInfo">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="productInfoList" :row-class-name="rowProductInfoIndex" @selection-change="handleProductInfoSelectionChange" ref="productInfo">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="商品名称" prop="productName" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.productName" placeholder="请输入商品名称" />
            </template>
          </el-table-column>
          <el-table-column label="商品数量" prop="productNumber" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.productNumber" placeholder="请输入商品数量" />
            </template>
          </el-table-column>
          <el-table-column label="商品进价" prop="productCost" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.productCost" placeholder="请输入商品进价" />
            </template>
          </el-table-column>
          <el-table-column label="商品售价" prop="productSell" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.productSell" placeholder="请输入商品售价" />
            </template>
          </el-table-column>
          <el-table-column label="商品利润" prop="productProfile" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.productProfile" placeholder="请输入商品利润" />
            </template>
          </el-table-column>
          <el-table-column label="创建时间" prop="createtime" width="240">
            <template #default="scope">
              <el-date-picker clearable
                v-model="scope.row.createtime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择创建时间">
              </el-date-picker>
            </template>
          </el-table-column>
          <el-table-column label="更新时间" prop="updatetime" width="240">
            <template #default="scope">
              <el-date-picker clearable
                v-model="scope.row.updatetime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择更新时间">
              </el-date-picker>
            </template>
          </el-table-column>
        </el-table>
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

<script setup name="Profile">
import { listProfile, getProfile, delProfile, addProfile, updateProfile } from "@/api/total_profile/profile";

const { proxy } = getCurrentInstance();

const profileList = ref([]);
const productInfoList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const checkedProductInfo = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    vemId: null,
    soldQuantity: null,
    remainingQuantity: null,
    totalProfit: null,
    createdTime: null,
    updatedTime: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询售货机利润列表 */
function getList() {
  loading.value = true;
  listProfile(queryParams.value).then(response => {
    profileList.value = response.rows;
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
    vemId: null,
    soldQuantity: null,
    remainingQuantity: null,
    totalProfit: null,
    extendInfo: null,
    createdTime: null,
    updatedTime: null
  };
  productInfoList.value = [];
  proxy.resetForm("profileRef");
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
  ids.value = selection.map(item => item.vemId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加售货机利润";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _vemId = row.vemId || ids.value
  getProfile(_vemId).then(response => {
    form.value = response.data;
    productInfoList.value = response.data.productInfoList;
    open.value = true;
    title.value = "修改售货机利润";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["profileRef"].validate(valid => {
    if (valid) {
      form.value.productInfoList = productInfoList.value;
      if (form.value.vemId != null) {
        updateProfile(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProfile(form.value).then(response => {
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
  const _vemIds = row.vemId || ids.value;
  proxy.$modal.confirm('是否确认删除售货机利润编号为"' + _vemIds + '"的数据项？').then(function() {
    return delProfile(_vemIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 商品信息1序号 */
function rowProductInfoIndex({ row, rowIndex }) {
  row.index = rowIndex + 1;
}

/** 商品信息1添加按钮操作 */
function handleAddProductInfo() {
  let obj = {};
  obj.productName = "";
  obj.productNumber = "";
  obj.productCost = "";
  obj.productSell = "";
  obj.productProfile = "";
  obj.createtime = "";
  obj.updatetime = "";
  productInfoList.value.push(obj);
}

/** 商品信息1删除按钮操作 */
function handleDeleteProductInfo() {
  if (checkedProductInfo.value.length == 0) {
    proxy.$modal.msgError("请先选择要删除的商品信息1数据");
  } else {
    const productInfos = productInfoList.value;
    const checkedProductInfos = checkedProductInfo.value;
    productInfoList.value = productInfos.filter(function(item) {
      return checkedProductInfos.indexOf(item.index) == -1
    });
  }
}

/** 复选框选中数据 */
function handleProductInfoSelectionChange(selection) {
  checkedProductInfo.value = selection.map(item => item.index)
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('total_profile/profile/export', {
    ...queryParams.value
  }, `profile_${new Date().getTime()}.xlsx`)
}

getList();
</script>
