<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.resourceName" placeholder="资源名称" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.resourceType" placeholder="资源类型" clearable class="filter-item" style="width: 150px">
        <el-option v-for="item in resourceTypes" :key="item.value" :label="item.desc" :value="item.obj" />
      </el-select>
      <el-select v-model="listQuery.resourceStatus" placeholder="资源状态" clearable class="filter-item" style="width: 150px">
        <el-option v-for="item in resourceStatuses" :key="item.value" :label="item.desc" :value="item.obj" />
      </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
    </div>

    <div class="filter-container">
      <el-button class="filter-item" type="primary" icon="el-icon-plus" @click="handleCreate">新增</el-button>
      <el-button v-if="listQuery.parentId !== 0" class="filter-item" style="margin-left: 10px;" type="success" icon="el-icon-back" @click="handleParent">返回上级</el-button>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="ID" prop="id" align="center" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="资源名称" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.resourceName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="资源编码" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.resourceCode }}</span>
        </template>
      </el-table-column>
      <el-table-column label="资源路径">
        <template slot-scope="scope">
          <span>{{ scope.row.url }}</span>
        </template>
      </el-table-column>
      <el-table-column label="资源类型" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.resourceType | resourceTypeTagFilter">{{ scope.row.extData.resourceType }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="资源图标" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.resourceIcon }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" width="100">
        <template slot-scope="{row}">
          <el-tag :type="row.resourceStatus === 'AVAILABLE' ? '' : 'danger' ">{{ row.extData.resourceStatus }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.orderNo }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="250" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button size="mini" type="primary" @click="handleUpdate(row)">修改</el-button>
          <el-button size="mini" type="success" style="width: 70px" @click="handleChildren(row)">管理下级</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.current" :limit.sync="listQuery.size" @pagination="getList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="90px" style="width: 400px; margin-left:50px;">
        <el-form-item label="上级资源" prop="parentName">
          <el-input v-model="temp.parentName" disabled />
        </el-form-item>
        <el-form-item label="资源名称" prop="resourceName">
          <el-input v-model="temp.resourceName" />
        </el-form-item>
        <el-form-item label="资源类型" prop="resourceType">
          <el-select v-model="temp.resourceType" placeholder="资源类型">
            <el-option v-for="item in resourceTypes" :key="item.value" :label="item.desc" :value="item.obj" />
          </el-select>
        </el-form-item>
        <el-form-item label="资源编码" prop="resourceCode">
          <el-input v-model="temp.resourceCode" />
        </el-form-item>
        <el-form-item label="资源路径" prop="url">
          <el-input v-model="temp.url" />
        </el-form-item>
        <el-form-item label="排序" prop="orderNo">
          <el-input v-model="temp.orderNo" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="temp.remark" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSaveOrUpdate()">提交</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { toPage, page, detail, saveOrUpdate, del } from '@/api/paddling/admin/resource'
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'PAddlingAdminResource',
  components: { Pagination },
  directives: { waves },
  filters: {
    resourceTypeTagFilter(resourceType) {
      const resourceTypeMap = {
        SHOW_MENU: '',
        HIDE_MENU: 'danger',
        COMPONENT: 'success',
        PATH: 'warning',
        LINK: 'info'
      }
      return resourceTypeMap[resourceType]
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      resourceTypes: [],
      resourceStatuses: [],
      listQuery: {
        current: 1,
        size: 10,
        parentId: 0,
        resourceName: undefined,
        resourceCode: undefined,
        resourceType: undefined,
        resourceStatus: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '修改资源',
        create: '新增资源'
      },
      temp: {
        id: undefined,
        parenId: 0,
        parentName: '顶级资源',
        resourceName: undefined,
        resourceCode: undefined,
        url: undefined,
        resourceIcon: undefined,
        resourceType: 'SHOW_MENU',
        resourceStatus: 'AVAILABLE',
        orderNo: 1,
        remark: undefined
      },
      parentIdStack: [0],
      rules: {
        bookName: [{ required: true, message: '书名为必填项', trigger: 'change' }]
      }
    }
  },
  created() {
    this.initPage()
    this.getList()
  },
  methods: {
    initPage() {
      toPage().then(res => {
        this.resourceTypes = res.data.resourceTypes
        this.resourceStatuses = res.data.resourceStatuses
      })
    },
    getList() {
      this.listLoading = true
      page(this.listQuery).then(res => {
        this.list = res.data.records
        this.total = res.data.total
        this.listLoading = false
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        parentId: this.listQuery.parentId,
        parentName: '顶级资源',
        resourceName: undefined,
        resourceCode: undefined,
        url: undefined,
        resourceIcon: undefined,
        resourceType: 'SHOW_MENU',
        resourceShow: 'AVAILABLE',
        orderNo: 1,
        remark: undefined
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleUpdate(row) {
      detail(row.id).then(res => {
        this.temp = res.data
        this.temp.createdTime = undefined
        this.temp.modifiedTime = undefined
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      })
    },
    handleSaveOrUpdate() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          saveOrUpdate(this.temp).then(() => {
            this.dialogFormVisible = false
            this.getList()
            this.$notify({
              title: '成功',
              message: '保存成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleDelete(row) {
      const id = row.id
      del(id).then(() => {
        this.getList()
        this.$notify({
          title: '成功',
          message: '删除成功',
          type: 'success',
          duration: 2000
        })
      })
    },
    restListQuery() {
      this.listQuery = {
        current: 1,
        size: 10,
        parentId: 0,
        resourceName: undefined,
        resourceCode: undefined,
        resourceType: undefined,
        resourceStatus: undefined
      }
    },
    handleChildren(row) {
      this.restListQuery()
      this.listQuery.parentId = row.id
      this.parentIdStack.push(row.id)
      this.getList()
    },
    handleParent() {
      this.restListQuery()
      this.parentIdStack.pop()
      this.listQuery.parentId = this.parentIdStack[this.parentIdStack.length - 1]
      this.getList()
    }
  }
}
</script>
