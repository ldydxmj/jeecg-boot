<template>
  <div>
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
          <a-form-item label="书包颜色" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input v-decorator="[ 'color', validatorRules.color]" placeholder="请输入书包颜色"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="书包大小" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input v-decorator="[ 'size', validatorRules.size]" placeholder="请输入书包大小"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="班级" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input v-decorator="[ 'studentId', validatorRules.studentId]" placeholder="请输入班级"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="是否好看" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input-number v-decorator="[ 'isBeautiful', validatorRules.isBeautiful]" placeholder="请输入是否好看" style="width: 100%"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="使用时长（年）" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input-number v-decorator="[ 'usedTime', validatorRules.usedTime]" placeholder="请输入使用时长（年）" style="width: 100%"/>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </div>
</template>
<script>
  import pick from 'lodash.pick'
  import { getAction } from '@/api/manage'
  import JDate from '@/components/jeecg/JDate'  

  export default {
    name: 'TestOne2oneBookbageForm',
    components: { 
      JDate,
    },
    data () {
      return {
        form: this.$form.createForm(this),
        model: {},
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
        confirmLoading: false,
        validatorRules:{
          createBy:{},
          createTime:{},
          updateBy:{},
          updateTime:{},
          color:{},
          size:{},
          studentId:{},
          isBeautiful:{},
          usedTime:{},
        },
        
      }
    },
    methods:{
      initFormData(url,id){
        this.clearFormData()
        if(!id){
          this.edit({})
        }else{
          getAction(url,{id:id}).then(res=>{
            if(res.success){
              let records = res.result
              if(records && records.length>0){
                this.edit(records[0])
              }
            }
          })
        }
      },
      edit(record){
        this.model = Object.assign({}, record)
        console.log("TestOne2oneBookbageForm-edit",this.model);
        let fieldval = pick(this.model,'createBy','createTime','updateBy','updateTime','color','size','studentId','isBeautiful','usedTime')
        this.$nextTick(() => {
          this.form.setFieldsValue(fieldval)
        })
      },
      getFormData(){
        let formdata_arr = []
        this.form.validateFields((err, values) => {
          if (!err) {
            let formdata = Object.assign(this.model, values)
            let isNullObj = true
            Object.keys(formdata).forEach(key=>{
              if(formdata[key]){
                isNullObj = false
              }
            })
            if(!isNullObj){
              formdata_arr.push(formdata)
            }
          }else{
            this.$emit("validateError","书包表表单校验未通过");
          }
        })
        console.log("书包表表单数据集",formdata_arr);
        return formdata_arr;
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'createBy','createTime','updateBy','updateTime','color','size','studentId','isBeautiful','usedTime'))
      },
      clearFormData(){
        this.form.resetFields()
        this.model={}
      }
    
    }
  }
</script>
