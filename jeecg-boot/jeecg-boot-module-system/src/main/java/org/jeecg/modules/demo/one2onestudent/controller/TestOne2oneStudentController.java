package org.jeecg.modules.demo.one2onestudent.controller;

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
import org.jeecg.modules.demo.one2onestudent.entity.TestOne2oneBookbage;
import org.jeecg.modules.demo.one2onestudent.entity.TestOne2oneStudent;
import org.jeecg.modules.demo.one2onestudent.vo.TestOne2oneStudentPage;
import org.jeecg.modules.demo.one2onestudent.service.ITestOne2oneStudentService;
import org.jeecg.modules.demo.one2onestudent.service.ITestOne2oneBookbageService;
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
 * @Description: 学生表
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Api(tags="学生表")
@RestController
@RequestMapping("/one2onestudent/testOne2oneStudent")
@Slf4j
public class TestOne2oneStudentController {
	@Autowired
	private ITestOne2oneStudentService testOne2oneStudentService;
	@Autowired
	private ITestOne2oneBookbageService testOne2oneBookbageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param testOne2oneStudent
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "学生表-分页列表查询")
	@ApiOperation(value="学生表-分页列表查询", notes="学生表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TestOne2oneStudent testOne2oneStudent,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TestOne2oneStudent> queryWrapper = QueryGenerator.initQueryWrapper(testOne2oneStudent, req.getParameterMap());
		Page<TestOne2oneStudent> page = new Page<TestOne2oneStudent>(pageNo, pageSize);
		IPage<TestOne2oneStudent> pageList = testOne2oneStudentService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param testOne2oneStudentPage
	 * @return
	 */
	@AutoLog(value = "学生表-添加")
	@ApiOperation(value="学生表-添加", notes="学生表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TestOne2oneStudentPage testOne2oneStudentPage) {
		TestOne2oneStudent testOne2oneStudent = new TestOne2oneStudent();
		BeanUtils.copyProperties(testOne2oneStudentPage, testOne2oneStudent);
		testOne2oneStudentService.saveMain(testOne2oneStudent, testOne2oneStudentPage.getTestOne2oneBookbageList());
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param testOne2oneStudentPage
	 * @return
	 */
	@AutoLog(value = "学生表-编辑")
	@ApiOperation(value="学生表-编辑", notes="学生表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TestOne2oneStudentPage testOne2oneStudentPage) {
		TestOne2oneStudent testOne2oneStudent = new TestOne2oneStudent();
		BeanUtils.copyProperties(testOne2oneStudentPage, testOne2oneStudent);
		TestOne2oneStudent testOne2oneStudentEntity = testOne2oneStudentService.getById(testOne2oneStudent.getId());
		if(testOne2oneStudentEntity==null) {
			return Result.error("未找到对应数据");
		}
		testOne2oneStudentService.updateMain(testOne2oneStudent, testOne2oneStudentPage.getTestOne2oneBookbageList());
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "学生表-通过id删除")
	@ApiOperation(value="学生表-通过id删除", notes="学生表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		testOne2oneStudentService.delMain(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "学生表-批量删除")
	@ApiOperation(value="学生表-批量删除", notes="学生表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.testOne2oneStudentService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "学生表-通过id查询")
	@ApiOperation(value="学生表-通过id查询", notes="学生表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TestOne2oneStudent testOne2oneStudent = testOne2oneStudentService.getById(id);
		if(testOne2oneStudent==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(testOne2oneStudent);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "书包表集合-通过id查询")
	@ApiOperation(value="书包表集合-通过id查询", notes="书包表-通过id查询")
	@GetMapping(value = "/queryTestOne2oneBookbageByMainId")
	public Result<?> queryTestOne2oneBookbageListByMainId(@RequestParam(name="id",required=true) String id) {
		List<TestOne2oneBookbage> testOne2oneBookbageList = testOne2oneBookbageService.selectByMainId(id);
		return Result.ok(testOne2oneBookbageList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param testOne2oneStudent
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TestOne2oneStudent testOne2oneStudent) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<TestOne2oneStudent> queryWrapper = QueryGenerator.initQueryWrapper(testOne2oneStudent, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<TestOne2oneStudent> queryList = testOne2oneStudentService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<TestOne2oneStudent> testOne2oneStudentList = new ArrayList<TestOne2oneStudent>();
      if(oConvertUtils.isEmpty(selections)) {
          testOne2oneStudentList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          testOne2oneStudentList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<TestOne2oneStudentPage> pageList = new ArrayList<TestOne2oneStudentPage>();
      for (TestOne2oneStudent main : testOne2oneStudentList) {
          TestOne2oneStudentPage vo = new TestOne2oneStudentPage();
          BeanUtils.copyProperties(main, vo);
          List<TestOne2oneBookbage> testOne2oneBookbageList = testOne2oneBookbageService.selectByMainId(main.getId());
          vo.setTestOne2oneBookbageList(testOne2oneBookbageList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "学生表列表");
      mv.addObject(NormalExcelConstants.CLASS, TestOne2oneStudentPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("学生表数据", "导出人:"+sysUser.getRealname(), "学生表"));
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
              List<TestOne2oneStudentPage> list = ExcelImportUtil.importExcel(file.getInputStream(), TestOne2oneStudentPage.class, params);
              for (TestOne2oneStudentPage page : list) {
                  TestOne2oneStudent po = new TestOne2oneStudent();
                  BeanUtils.copyProperties(page, po);
                  testOne2oneStudentService.saveMain(po, page.getTestOne2oneBookbageList());
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
