package org.jeecg.modules.demo.one2manystudent.controller;

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
import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyBooks;
import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyMathScore;
import org.jeecg.modules.demo.one2manystudent.entity.TestOne2manyMain;
import org.jeecg.modules.demo.one2manystudent.vo.TestOne2manyMainPage;
import org.jeecg.modules.demo.one2manystudent.service.ITestOne2manyMainService;
import org.jeecg.modules.demo.one2manystudent.service.ITestOne2manyBooksService;
import org.jeecg.modules.demo.one2manystudent.service.ITestOne2manyMathScoreService;
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
 * @Description: 测试一对多学生
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Api(tags="测试一对多学生")
@RestController
@RequestMapping("/one2manystudent/testOne2manyMain")
@Slf4j
public class TestOne2manyMainController {
	@Autowired
	private ITestOne2manyMainService testOne2manyMainService;
	@Autowired
	private ITestOne2manyBooksService testOne2manyBooksService;
	@Autowired
	private ITestOne2manyMathScoreService testOne2manyMathScoreService;
	
	/**
	 * 分页列表查询
	 *
	 * @param testOne2manyMain
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "测试一对多学生-分页列表查询")
	@ApiOperation(value="测试一对多学生-分页列表查询", notes="测试一对多学生-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TestOne2manyMain testOne2manyMain,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TestOne2manyMain> queryWrapper = QueryGenerator.initQueryWrapper(testOne2manyMain, req.getParameterMap());
		Page<TestOne2manyMain> page = new Page<TestOne2manyMain>(pageNo, pageSize);
		IPage<TestOne2manyMain> pageList = testOne2manyMainService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param testOne2manyMainPage
	 * @return
	 */
	@AutoLog(value = "测试一对多学生-添加")
	@ApiOperation(value="测试一对多学生-添加", notes="测试一对多学生-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TestOne2manyMainPage testOne2manyMainPage) {
		TestOne2manyMain testOne2manyMain = new TestOne2manyMain();
		BeanUtils.copyProperties(testOne2manyMainPage, testOne2manyMain);
		testOne2manyMainService.saveMain(testOne2manyMain, testOne2manyMainPage.getTestOne2manyBooksList(),testOne2manyMainPage.getTestOne2manyMathScoreList());
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param testOne2manyMainPage
	 * @return
	 */
	@AutoLog(value = "测试一对多学生-编辑")
	@ApiOperation(value="测试一对多学生-编辑", notes="测试一对多学生-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TestOne2manyMainPage testOne2manyMainPage) {
		TestOne2manyMain testOne2manyMain = new TestOne2manyMain();
		BeanUtils.copyProperties(testOne2manyMainPage, testOne2manyMain);
		TestOne2manyMain testOne2manyMainEntity = testOne2manyMainService.getById(testOne2manyMain.getId());
		if(testOne2manyMainEntity==null) {
			return Result.error("未找到对应数据");
		}
		testOne2manyMainService.updateMain(testOne2manyMain, testOne2manyMainPage.getTestOne2manyBooksList(),testOne2manyMainPage.getTestOne2manyMathScoreList());
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试一对多学生-通过id删除")
	@ApiOperation(value="测试一对多学生-通过id删除", notes="测试一对多学生-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		testOne2manyMainService.delMain(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "测试一对多学生-批量删除")
	@ApiOperation(value="测试一对多学生-批量删除", notes="测试一对多学生-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.testOne2manyMainService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试一对多学生-通过id查询")
	@ApiOperation(value="测试一对多学生-通过id查询", notes="测试一对多学生-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TestOne2manyMain testOne2manyMain = testOne2manyMainService.getById(id);
		if(testOne2manyMain==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(testOne2manyMain);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "学生的书本集合-通过id查询")
	@ApiOperation(value="学生的书本集合-通过id查询", notes="学生的书本-通过id查询")
	@GetMapping(value = "/queryTestOne2manyBooksByMainId")
	public Result<?> queryTestOne2manyBooksListByMainId(@RequestParam(name="id",required=true) String id) {
		List<TestOne2manyBooks> testOne2manyBooksList = testOne2manyBooksService.selectByMainId(id);
		return Result.ok(testOne2manyBooksList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "学生的数学成绩集合-通过id查询")
	@ApiOperation(value="学生的数学成绩集合-通过id查询", notes="学生的数学成绩-通过id查询")
	@GetMapping(value = "/queryTestOne2manyMathScoreByMainId")
	public Result<?> queryTestOne2manyMathScoreListByMainId(@RequestParam(name="id",required=true) String id) {
		List<TestOne2manyMathScore> testOne2manyMathScoreList = testOne2manyMathScoreService.selectByMainId(id);
		return Result.ok(testOne2manyMathScoreList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param testOne2manyMain
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TestOne2manyMain testOne2manyMain) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<TestOne2manyMain> queryWrapper = QueryGenerator.initQueryWrapper(testOne2manyMain, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<TestOne2manyMain> queryList = testOne2manyMainService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<TestOne2manyMain> testOne2manyMainList = new ArrayList<TestOne2manyMain>();
      if(oConvertUtils.isEmpty(selections)) {
          testOne2manyMainList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          testOne2manyMainList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<TestOne2manyMainPage> pageList = new ArrayList<TestOne2manyMainPage>();
      for (TestOne2manyMain main : testOne2manyMainList) {
          TestOne2manyMainPage vo = new TestOne2manyMainPage();
          BeanUtils.copyProperties(main, vo);
          List<TestOne2manyBooks> testOne2manyBooksList = testOne2manyBooksService.selectByMainId(main.getId());
          vo.setTestOne2manyBooksList(testOne2manyBooksList);
          List<TestOne2manyMathScore> testOne2manyMathScoreList = testOne2manyMathScoreService.selectByMainId(main.getId());
          vo.setTestOne2manyMathScoreList(testOne2manyMathScoreList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "测试一对多学生列表");
      mv.addObject(NormalExcelConstants.CLASS, TestOne2manyMainPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("测试一对多学生数据", "导出人:"+sysUser.getRealname(), "测试一对多学生"));
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
              List<TestOne2manyMainPage> list = ExcelImportUtil.importExcel(file.getInputStream(), TestOne2manyMainPage.class, params);
              for (TestOne2manyMainPage page : list) {
                  TestOne2manyMain po = new TestOne2manyMain();
                  BeanUtils.copyProperties(page, po);
                  testOne2manyMainService.saveMain(po, page.getTestOne2manyBooksList(),page.getTestOne2manyMathScoreList());
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
