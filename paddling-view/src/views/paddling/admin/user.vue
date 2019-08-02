<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.username" placeholder="用户名" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-input v-model="listQuery.nickName" placeholder="昵称" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-input v-model="listQuery.email" placeholder="邮箱" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-input v-model="listQuery.mobile" placeholder="手机号" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.userStatus" placeholder="用户状态" clearable class="filter-item" style="width: 150px">
        <el-option v-for="item in userStatuses" :key="item.value" :label="item.desc" :value="item.obj" />
      </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查询</el-button>
    </div>

    <div class="filter-container">
      <el-button class="filter-item" type="primary" icon="el-icon-plus" @click="handleCreate">新增</el-button>
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
      <el-table-column label="用户名" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column label="昵称" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.nickName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="邮箱" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机号" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.mobile }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.extData.userStatus }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createdTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button size="mini" type="primary" @click="handleUpdate(row)">修改</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(row)">删除</el-button>
          <p />
          <el-button size="mini" type="primary" style="width: 70px" @click="handleRoleBind(row)">分配角色</el-button>
          <el-button size="mini" type="success" style="width: 70px" @click="handleResetPwd(row)">重置密码</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.current" :limit.sync="listQuery.size" @pagination="getList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="90px" style="width: 400px; margin-left:50px;">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="temp.username" />
        </el-form-item>
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="temp.nickName" />
        </el-form-item>
        <el-form-item label="电子邮箱" prop="email">
          <el-input v-model="temp.email" />
        </el-form-item>
        <el-form-item label="手机号" prop="mobile">
          <el-input v-model="temp.mobile" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSaveOrUpdate()">提交</el-button>
      </div>
    </el-dialog>

    <el-dialog title="分配角色" :visible.sync="dialogRoleVisible">
      <el-form ref="dataForm" :rules="roleRules" :model="roleTemp" label-position="left" label-width="90px" style="width: 400px; margin-left:50px;">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="roleTemp.username" disabled />
        </el-form-item>
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="temp.nickName" />
        </el-form-item>
        <el-form-item label="角色" prop="roleIds">
          <el-select v-model="temp.roleIds" multiple filterable placeholder="请选择角色">
            <el-option v-for="item in roleList" :key="item.id" :label="item.roleName" :value="item.id" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogRoleVisible = false">取消</el-button>
        <el-button type="primary" @click="handleRoleBindData()">提交</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { toPage, page, detail, saveOrUpdate, resetPwd, del } from '@/api/paddling/admin/user'
import { listAll as roleListAll } from '@/api/paddling/admin/role'
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'PaddlingAdminUser',
  components: { Pagination },
  directives: { waves },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      userStatuses: [],
      listQuery: {
        current: 1,
        size: 10,
        username: undefined,
        nickName: undefined,
        email: undefined,
        mobile: undefined,
        userStatus: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '修改资源',
        create: '新增资源'
      },
      temp: {
        id: undefined,
        username: undefined,
        email: undefined,
        mobile: undefined
      },
      rules: {
        username: [{ required: true, message: '用户名为必填项', trigger: 'change' }]
      },
      dialogRoleVisible: false,
      roleTemp: {
        username: undefined,
        roleIds: []
      },
      roleList: [],
      roleRules: {}
    }
  },
  created() {
    this.initPage()
    this.getList()
  },
  methods: {
    initPage() {
      toPage().then(res => {
        this.userStatuses = res.data.userStatuses
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
        username: undefined,
        nickName: undefined,
        email: undefined,
        mobile: undefined
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
        this.temp.lockedTime = undefined
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
    handleResetPwd(row) {
      resetPwd(row.id).then(() => {
        this.$notify({
          title: '成功',
          message: '重置密码成功',
          type: 'success',
          duration: 2000
        })
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
    handleRoleBind(row) {
      this.roleTemp.username = row.username
      roleListAll(null).then(res => {
        this.roleList = res.data
      })
      this.dialogRoleVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleRoleBindData() {}
  }
}
</script>
