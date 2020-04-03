package org.jeecg.modules.demo.one2one.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.demo.one2one.model.TestOne2onestudentFullModel;
import org.jeecg.modules.system.model.SysLoginModel;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.one2one.entity.TestOne2oneHeadthy;
import org.jeecg.modules.demo.one2one.entity.TestOne2onestudent;
import org.jeecg.modules.demo.one2one.vo.TestOne2onestudentPage;
import org.jeecg.modules.demo.one2one.service.ITestOne2onestudentService;
import org.jeecg.modules.demo.one2one.service.ITestOne2oneHeadthyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 一对一学生表
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
@Api(tags="一对一学生表")
@RestController
@RequestMapping("/one2one/testOne2onestudent")
@Slf4j
public class TestOne2onestudentController {
	@Autowired
	private ITestOne2onestudentService testOne2onestudentService;
	@Autowired
	private ITestOne2oneHeadthyService testOne2oneHeadthyService;
	
	/**
	 * 分页列表查询
	 *
	 * @param testOne2onestudent
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "一对一学生表-分页列表查询")
	@ApiOperation(value="一对一学生表-分页列表查询", notes="一对一学生表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TestOne2onestudent testOne2onestudent,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TestOne2onestudent> queryWrapper = QueryGenerator.initQueryWrapper(testOne2onestudent, req.getParameterMap());
		Page<TestOne2onestudent> page = new Page<TestOne2onestudent>(pageNo, pageSize);
		IPage<TestOne2onestudent> pageList = testOne2onestudentService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	 /**
	  * 返回list-all
	  * @param testOne2onestudent
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @ApiOperation("返回json")
	 @RequestMapping(value = "/listAll", method = RequestMethod.GET)
	 public Result<JSONObject> backjson(TestOne2onestudent testOne2onestudent,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 Result<JSONObject> result = new Result<JSONObject>();
		 Page<TestOne2onestudent> page = new Page<TestOne2onestudent>(pageNo, pageSize);
		 QueryWrapper<TestOne2onestudent> queryWrapper = QueryGenerator.initQueryWrapper(testOne2onestudent, req.getParameterMap());
		 List<TestOne2onestudentFullModel> pageList = testOne2onestudentService.page2();
		 JSONObject obj = new JSONObject();
		 obj.put("list", pageList);
		 result.setResult(obj);
		 result.success("成功!");
		 return result;
	 }


	 /**
	 *   添加
	 *
	 * @param testOne2onestudentPage
	 * @return
	 */
	@AutoLog(value = "一对一学生表-添加")
	@ApiOperation(value="一对一学生表-添加", notes="一对一学生表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TestOne2onestudentPage testOne2onestudentPage) {
		TestOne2onestudent testOne2onestudent = new TestOne2onestudent();
		BeanUtils.copyProperties(testOne2onestudentPage, testOne2onestudent);
		testOne2onestudentService.saveMain(testOne2onestudent, testOne2onestudentPage.getTestOne2oneHeadthyList());
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param testOne2onestudentPage
	 * @return
	 */
	@AutoLog(value = "一对一学生表-编辑")
	@ApiOperation(value="一对一学生表-编辑", notes="一对一学生表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TestOne2onestudentPage testOne2onestudentPage) {
		TestOne2onestudent testOne2onestudent = new TestOne2onestudent();
		BeanUtils.copyProperties(testOne2onestudentPage, testOne2onestudent);
		TestOne2onestudent testOne2onestudentEntity = testOne2onestudentService.getById(testOne2onestudent.getId());
		if(testOne2onestudentEntity==null) {
			return Result.error("未找到对应数据");
		}
		testOne2onestudentService.updateMain(testOne2onestudent, testOne2onestudentPage.getTestOne2oneHeadthyList());
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "一对一学生表-通过id删除")
	@ApiOperation(value="一对一学生表-通过id删除", notes="一对一学生表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		testOne2onestudentService.delMain(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "一对一学生表-批量删除")
	@ApiOperation(value="一对一学生表-批量删除", notes="一对一学生表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.testOne2onestudentService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "一对一学生表-通过id查询")
	@ApiOperation(value="一对一学生表-通过id查询", notes="一对一学生表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TestOne2onestudent testOne2onestudent = testOne2onestudentService.getById(id);
		if(testOne2onestudent==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(testOne2onestudent);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "学生健康表集合-通过id查询")
	@ApiOperation(value="学生健康表集合-通过id查询", notes="学生健康表-通过id查询")
	@GetMapping(value = "/queryTestOne2oneHeadthyByMainId")
	public Result<?> queryTestOne2oneHeadthyListByMainId(@RequestParam(name="id",required=true) String id) {
		List<TestOne2oneHeadthy> testOne2oneHeadthyList = testOne2oneHeadthyService.selectByMainId(id);
		return Result.ok(testOne2oneHeadthyList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param testOne2onestudent
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TestOne2onestudent testOne2onestudent) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<TestOne2onestudent> queryWrapper = QueryGenerator.initQueryWrapper(testOne2onestudent, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<TestOne2onestudent> queryList = testOne2onestudentService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<TestOne2onestudent> testOne2onestudentList = new ArrayList<TestOne2onestudent>();
      if(oConvertUtils.isEmpty(selections)) {
          testOne2onestudentList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          testOne2onestudentList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<TestOne2onestudentPage> pageList = new ArrayList<TestOne2onestudentPage>();
      for (TestOne2onestudent main : testOne2onestudentList) {
          TestOne2onestudentPage vo = new TestOne2onestudentPage();
          BeanUtils.copyProperties(main, vo);
          List<TestOne2oneHeadthy> testOne2oneHeadthyList = testOne2oneHeadthyService.selectByMainId(main.getId());
          vo.setTestOne2oneHeadthyList(testOne2oneHeadthyList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "一对一学生表列表");
      mv.addObject(NormalExcelConstants.CLASS, TestOne2onestudentPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("一对一学生表数据", "导出人:"+sysUser.getRealname(), "一对一学生表"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<TestOne2onestudentPage> list = ExcelImportUtil.importExcel(file.getInputStream(), TestOne2onestudentPage.class, params);
              for (TestOne2onestudentPage page : list) {
                  TestOne2onestudent po = new TestOne2onestudent();
                  BeanUtils.copyProperties(page, po);
                  testOne2onestudentService.saveMain(po, page.getTestOne2oneHeadthyList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
    }

}
