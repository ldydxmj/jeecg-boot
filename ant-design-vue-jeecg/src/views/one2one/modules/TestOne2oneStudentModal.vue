<template>
  <a-modal
    :title="title"
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>

          <a-col :span="12">
            <a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="[ 'createBy', validatorRules.createBy]" placeholder="请输入创建人"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="创建日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择创建日期" v-decorator="[ 'createTime', validatorRules.createTime]" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="更新人" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="[ 'updateBy', validatorRules.updateBy]" placeholder="请输入更新人"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="更新日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择更新日期" v-decorator="[ 'updateTime', validatorRules.updateTime]" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="性别" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="[ 'sex', validatorRules.sex]" placeholder="请输入性别"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="用户名" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="[ 'name', validatorRules.name]" placeholder="请输入用户名"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="班级" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="[ 'mclass', validatorRules.mclass]" placeholder="请输入班级"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="年级" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="[ 'gruade', validatorRules.gruade]" placeholder="请输入年级"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="班级中的学号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="[ 'studentNo', validatorRules.studentNo]" placeholder="请输入班级中的学号" style="width: 100%"/>
            </a-form-item>
          </a-col>

        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="书包表" :key="refKeys[0]" :forceRender="true">
          <test-one2one-bookbage-form ref="testOne2oneBookbageForm" @validateError="validateError"></test-one2one-bookbage-form>
        </a-tab-pane>
      
      </a-tabs>

    </a-spin>
  </a-modal>
</template>

<script>

  import pick from 'lodash.pick'
  import { FormTypes,getRefPromise } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'
  import { validateDuplicateValue } from '@/utils/util'
  import TestOne2oneBookbageForm from './TestOne2oneBookbageForm.vue'
  import JDate from '@/components/jeecg/JDate'  

  export default {
    name: 'TestOne2oneStudentModal',
    mixins: [JEditableTableMixin],
    components: {
    TestOne2oneBookbageForm,
      JDate,
    },
    data() {
      return {
        labelCol: {
          span: 6
        },
        wrapperCol: {
          span: 16
        },
        labelCol2: {
          span: 3
        },
        wrapperCol2: {
          span: 20
        },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
          createBy: {rules: [
          ]},
          createTime: {rules: [
          ]},
          updateBy: {rules: [
          ]},
          updateTime: {rules: [
          ]},
          sex: {rules: [
          ]},
          name: {rules: [
          ]},
          mclass: {rules: [
          ]},
          gruade: {rules: [
          ]},
          studentNo: {rules: [
          ]},
        },
        refKeys: ['testOne2oneBookbage', ],
        tableKeys:[],
        activeKey: 'testOne2oneBookbage',
        // 书包表
        testOne2oneBookbageTable: {
          loading: false,
          dataSource: [],
          columns: [
          ]
        },
        url: {
          add: "/one2onestudent/testOne2oneStudent/add",
          edit: "/one2onestudent/testOne2oneStudent/edit",
          testOne2oneBookbage: {
            list: '/one2onestudent/testOne2oneStudent/queryTestOne2oneBookbageByMainId'
          },
        }
      }
    },
    methods: {
      getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        let fieldval = pick(this.model,'createBy','createTime','updateBy','updateTime','sex','name','mclass','gruade','studentNo')
        this.$nextTick(() => {
          this.form.setFieldsValue(fieldval)
          this.$refs.testOne2oneBookbageForm.initFormData(this.url.testOne2oneBookbage.list,this.model.id)
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
        }
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)

        return {
          ...main, // 展开
          testOne2oneBookbageList: this.$refs.testOne2oneBookbageForm.getFormData(),
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },
     popupCallback(row){
       this.form.setFieldsValue(pick(row,'createBy','createTime','updateBy','updateTime','sex','name','mclass','gruade','studentNo'))
     },

    }
  }
</script>

<style scoped>
</style>