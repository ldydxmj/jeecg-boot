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
            <a-form-item label="名字" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="[ 'name', validatorRules.name]" placeholder="请输入名字"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="学号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="[ 'xueNo', validatorRules.xueNo]" placeholder="请输入学号"></a-input>
            </a-form-item>
          </a-col>

        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="学生的书本" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="testOne2manyBooksTable.loading"
            :columns="testOne2manyBooksTable.columns"
            :dataSource="testOne2manyBooksTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
        
        <a-tab-pane tab="学生的数学成绩" :key="refKeys[1]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[1]"
            :loading="testOne2manyMathScoreTable.loading"
            :columns="testOne2manyMathScoreTable.columns"
            :dataSource="testOne2manyMathScoreTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
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

  export default {
    name: 'TestOne2manyMainModal',
    mixins: [JEditableTableMixin],
    components: {
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
          name: {rules: [
          ]},
          xueNo: {rules: [
          ]},
        },
        refKeys: ['testOne2manyBooks', 'testOne2manyMathScore', ],
        tableKeys:['testOne2manyBooks', 'testOne2manyMathScore', ],
        activeKey: 'testOne2manyBooks',
        // 学生的书本
        testOne2manyBooksTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '名称',
              key: 'bookName',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '书本主人id',
              key: 'bookMasterId',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
          ]
        },
        // 学生的数学成绩
        testOne2manyMathScoreTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '主人的id',
              key: 'masterId',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '是否优等生',
              key: 'isGood',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '是否语文成绩好',
              key: 'chineseIsGood',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '成绩',
              key: 'score',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
          ]
        },
        url: {
          add: "/one2manystudent/testOne2manyMain/add",
          edit: "/one2manystudent/testOne2manyMain/edit",
          testOne2manyBooks: {
            list: '/one2manystudent/testOne2manyMain/queryTestOne2manyBooksByMainId'
          },
          testOne2manyMathScore: {
            list: '/one2manystudent/testOne2manyMain/queryTestOne2manyMathScoreByMainId'
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
        let fieldval = pick(this.model,'name','xueNo')
        this.$nextTick(() => {
          this.form.setFieldsValue(fieldval)
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.testOne2manyBooks.list, params, this.testOne2manyBooksTable)
          this.requestSubTableData(this.url.testOne2manyMathScore.list, params, this.testOne2manyMathScoreTable)
        }
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)

        return {
          ...main, // 展开
          testOne2manyBooksList: allValues.tablesValue[0].values,
          testOne2manyMathScoreList: allValues.tablesValue[1].values,
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },
     popupCallback(row){
       this.form.setFieldsValue(pick(row,'name','xueNo'))
     },

    }
  }
</script>

<style scoped>
</style>