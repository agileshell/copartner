package com.insoul.ti.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.dao.criteria.ContestEntryCriteria;
import com.insoul.copartner.domain.ContestEntry;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.ContestEntryListRequest;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.ViewRequest;
import com.insoul.ti.shiro.Permission;
import com.insoul.ti.vo.ContestEntryVO;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/contestproject")
@Permission("authc")
public class ContestProjectEntryController extends WebBase {

    private static final String CONTEST_ENTRY_DETAIL = "contestentry_detail";
    private static final String CONTEST_PROJECT_LIST = "contestproject_list";

    @RequestMapping("/download")
    public ModelAndView download(HttpServletResponse response) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件
        HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
        sheet.createFreezePane(1, 2);// 冻结
        // 设置列宽
        sheet.setColumnWidth(0, 1000);
        sheet.setColumnWidth(1, 3500);
        sheet.setColumnWidth(2, 3500);
        sheet.setColumnWidth(3, 6500);
        sheet.setColumnWidth(4, 6500);
        sheet.setColumnWidth(5, 6500);
        sheet.setColumnWidth(6, 6500);
        sheet.setColumnWidth(7, 2500);
        // Sheet样式
//        HSSFCellStyle sheetStyle = workbook.createCellStyle();
        // 背景色的设定
//        sheetStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        // 前景色的设定
//        sheetStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        // 填充模式
//        sheetStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);
        // 设置列的样式
//        for (int i = 0; i <= 14; i++) {
//            sheet.setDefaultColumnStyle((short) i, sheetStyle);
//        }
        // 设置字体
        HSSFFont headfont = workbook.createFont();
        headfont.setFontName("黑体");
        headfont.setFontHeightInPoints((short) 22);// 字体大小
        headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        // 另一个样式
        HSSFCellStyle headstyle = workbook.createCellStyle();
        headstyle.setFont(headfont);
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        headstyle.setLocked(true);
        headstyle.setWrapText(true);// 自动换行
        // 另一个字体样式
        HSSFFont columnHeadFont = workbook.createFont();
        columnHeadFont.setFontName("宋体");
        columnHeadFont.setFontHeightInPoints((short) 10);
        columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 列头的样式
        HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
        columnHeadStyle.setFont(columnHeadFont);
        columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        columnHeadStyle.setLocked(true);
        columnHeadStyle.setWrapText(true);
        columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
        columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
        columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
        columnHeadStyle.setBorderRight((short) 1);// 边框的大小
        columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
        // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
        columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);

        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        // 普通单元格样式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 上下居中
        style.setWrapText(true);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft((short) 1);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBorderRight((short) 1);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
        style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．
        // 另一个样式
        HSSFCellStyle centerstyle = workbook.createCellStyle();
        centerstyle.setFont(font);
        centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        centerstyle.setWrapText(true);
        centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);
        centerstyle.setBorderLeft((short) 1);
        centerstyle.setRightBorderColor(HSSFColor.BLACK.index);
        centerstyle.setBorderRight((short) 1);
        centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
        centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．
        try {
            // 创建第一行
            HSSFRow row0 = sheet.createRow(0);
            // 设置行高
            row0.setHeight((short) 900);
            // 创建第一列
            HSSFCell cell0 = row0.createCell(0);
            cell0.setCellValue(new HSSFRichTextString("河南省大众创业扶持项目汇总表"));
            cell0.setCellStyle(headstyle);
            /**
             * 合并单元格 第一个参数：第一个单元格的行数（从0开始） 第二个参数：第二个单元格的行数（从0开始） 第三个参数：第一个单元格的列数（从0开始） 第四个参数：第二个单元格的列数（从0开始）
             */
            CellRangeAddress range = new CellRangeAddress(0, 0, 0, 14);
            sheet.addMergedRegion(range);
            // 创建第二行
            HSSFRow row1 = sheet.createRow(1);
            row1.setHeight((short) 750);
            HSSFCell cell = row1.createCell(0);
            cell.setCellValue(new HSSFRichTextString("序号"));
            cell.setCellStyle(columnHeadStyle);
            cell = row1.createCell(1);
            cell.setCellValue(new HSSFRichTextString("所在市县或园区"));
            cell.setCellStyle(columnHeadStyle);
            cell = row1.createCell(2);
            cell.setCellValue(new HSSFRichTextString("创业实体名称"));
            cell.setCellStyle(columnHeadStyle);
            cell = row1.createCell(3);
            cell.setCellValue(new HSSFRichTextString("所属行业"));
            cell.setCellStyle(columnHeadStyle);
            cell = row1.createCell(4);
            cell.setCellValue(new HSSFRichTextString("企业法律形态"));
            cell.setCellStyle(columnHeadStyle);
            cell = row1.createCell(5);
            cell.setCellValue(new HSSFRichTextString("注册时间"));
            cell.setCellStyle(columnHeadStyle);
            cell = row1.createCell(6);
            cell.setCellValue(new HSSFRichTextString("吸纳就业人数"));
            cell.setCellStyle(columnHeadStyle);
            cell = row1.createCell(7);
            cell.setCellValue(new HSSFRichTextString("法定代表人"));
            cell.setCellStyle(columnHeadStyle);
            cell = row1.createCell(8);
            cell.setCellValue(new HSSFRichTextString("人员类别"));
            cell.setCellStyle(columnHeadStyle);
            cell = row1.createCell(9);
            cell.setCellValue(new HSSFRichTextString("联系方式"));
            cell.setCellStyle(columnHeadStyle);
            cell = row1.createCell(10);
            cell.setCellValue(new HSSFRichTextString("身份证号"));
            cell.setCellStyle(columnHeadStyle);
            cell = row1.createCell(11);
            cell.setCellValue(new HSSFRichTextString("开户行"));
            cell.setCellStyle(columnHeadStyle);
            cell = row1.createCell(12);
            cell.setCellValue(new HSSFRichTextString("开户名"));
            cell.setCellStyle(columnHeadStyle);
            cell = row1.createCell(13);
            cell.setCellValue(new HSSFRichTextString("账号"));
            cell.setCellStyle(columnHeadStyle);
            cell = row1.createCell(14);
            cell.setCellValue(new HSSFRichTextString("申请扶持金额"));
            cell.setCellStyle(columnHeadStyle);
            List<ContestEntry> projectList = contestEntryDAO.findAll();
            int m = 2;
            int len = projectList.size();
            for (int i = 0; i < len; i++) {
                ContestEntry c = projectList.get(i);
                HSSFRow row = sheet.createRow(m + i);
                cell = row.createCell(0);
                cell.setCellValue(new HSSFRichTextString(c.getId() + ""));
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue(new HSSFRichTextString(c.getLocation()));
                cell.setCellStyle(style);
                cell = row.createCell(2);
                cell.setCellValue(new HSSFRichTextString(c.getInstance()));
                cell.setCellStyle(style);
                cell = row.createCell(3);
                cell.setCellValue(new HSSFRichTextString(c.getIndustry()));
                cell.setCellStyle(style);
                cell = row.createCell(4);
                cell.setCellValue(new HSSFRichTextString(c.getLegalFormation()));
                cell.setCellStyle(style);
                cell = row.createCell(5);
                cell.setCellValue(new HSSFRichTextString(c.getEmployqty() + ""));
                cell.setCellStyle(style);
                cell = row.createCell(6);
                cell.setCellValue(new HSSFRichTextString(c.getRegtime()));
                cell.setCellStyle(style);
                cell = row.createCell(7);
                cell.setCellValue(new HSSFRichTextString(c.getLegalPerson()));
                cell.setCellStyle(style);
                cell = row.createCell(8);
                cell.setCellValue(new HSSFRichTextString(c.getUserCategory()));
                cell.setCellStyle(style);
                cell = row.createCell(9);
                cell.setCellValue(new HSSFRichTextString(c.getContact()));
                cell.setCellStyle(style);
                cell = row.createCell(10);
                cell.setCellValue(new HSSFRichTextString(c.getIdNumber()));
                cell.setCellStyle(style);
                cell = row.createCell(11);
                cell.setCellValue(new HSSFRichTextString(c.getBankName()));
                cell.setCellStyle(style);
                cell = row.createCell(12);
                cell.setCellValue(new HSSFRichTextString(c.getBankUserName()));
                cell.setCellStyle(style);
                cell = row.createCell(13);
                cell.setCellValue(new HSSFRichTextString(c.getBankAccount()));
                cell.setCellStyle(style);
                cell = row.createCell(14);
                cell.setCellValue(new HSSFRichTextString(c.getSupportMoney()));
                cell.setCellStyle(style);
            }
            String filename = System.nanoTime() + ".xls";// 设置下载时客户端Excel的名称
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + filename);
            OutputStream ouputStream = response.getOutputStream();
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
            log.error("download excel Error.", e);
        }
        return null;
    }

    @RequestMapping("/download00")
    public ModelAndView download00(HttpServletResponse response) throws Exception {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            File target = new File("");
            long fileLength = target.length();
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("content-disposition", "attachment;filename=\"" + "" + "\"");
            response.setHeader("content-length", String.valueOf(fileLength));
            in = new BufferedInputStream(new FileInputStream(target));
            out = new BufferedOutputStream(response.getOutputStream());
            StreamUtils.copy(in, out);
        } finally {
            if (in != null) {
                in.close();
                in = null;
            }
            if (out != null) {
                out.close();
                out = null;
            }
        }
        return null;
    }

    @RequestMapping("/list")
    public ModelAndView list(@Valid ContestEntryListRequest request, BindingResult result) {
        ModelAndView mv = createModelView(CONTEST_PROJECT_LIST, request);
        PageQuery query = request.init().getQuery();
        ContestEntryCriteria criteria = new ContestEntryCriteria();
        criteria.setLimit(query.getPage_size());
        criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
        criteria.setContestId(request.getContestId());
        criteria.setUserId(request.getUserId());
        List<ContestEntry> list = contestEntryDAO.queryContestEntry(criteria);
        Long count = contestEntryDAO.countContestEntry(criteria);
        query.setCount((count == null || count <= 0L) ? 0 : count.intValue());
        List<ContestEntryVO> voList = new ArrayList<ContestEntryVO>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ContestEntry contestEntry : list) {
                voList.add(toContestEntryVO(contestEntry));
            }
            mv.addObject("query", query);
            mv.addObject("contestEntryList", voList);
            mv.addObject("success", true);
            mv.addObject("req", request);
            return mv;
        }
        mv.addObject("query", query);
        mv.addObject("contestEntryList", voList);
        mv.addObject("success", false);
        mv.addObject("req", request);
        return mv;
    }

    private ContestEntryVO toContestEntryVO(ContestEntry c) {
        ContestEntryVO vo = new ContestEntryVO();
        vo.setBusinessLicense(c.getBusinessLicense());
        vo.setBusinessLicenseImg(c.getBusinessLicenseImg());
        vo.setContestId(c.getContestId());
        try {
            vo.setContestName(contestDAO.getContestName(c.getContestId()));
        } catch (Exception ingore) {
        }
        vo.setCreated(c.getCreated());
        vo.setHasBusinessRegistered(c.getHasBusinessRegistered());
        vo.setId(c.getId());
        vo.setProjectId(c.getProjectId());
        try {
            vo.setProjectName(projectDAO.getProjectName(c.getProjectId()));
        } catch (Exception ingore) {
        }
        vo.setStatus(c.getStatus());
        vo.setUpdated(c.getUpdated());
        vo.setUserId(c.getUserId());
        try {
            vo.setUserName(userDAO.getUserName(c.getUserId()));
        } catch (Exception ingore) {
        }
        vo.setVotes(c.getVotes());

        vo.setLocation(c.getLocation());
        vo.setInstance(c.getInstance());
        vo.setIndustry(c.getIndustry());
        vo.setLegalFormation(c.getLegalFormation());
        vo.setEmployqty(c.getEmployqty());
        vo.setRegtime(c.getRegtime());
        vo.setLegalPerson(c.getLegalPerson());
        vo.setUserCategory(c.getUserCategory());
        vo.setContact(c.getContact());
        vo.setIdNumber(c.getIdNumber());
        vo.setBankName(c.getBankName());
        vo.setBankUserName(c.getBankUserName());
        vo.setBankAccount(c.getBankAccount());
        vo.setSupportMoney(c.getSupportMoney());

        return vo;
    }

    @RequestMapping("/detail/{contestEntryId}")
    public ModelAndView detail(@PathVariable Long contestEntryId, ViewRequest req) {
        ModelAndView mv = createModelView(CONTEST_ENTRY_DETAIL, req);
        mv.addObject("viewname", CONTEST_PROJECT_LIST);
        try {
            ContestEntry contestEntry = contestEntryDAO.get(contestEntryId);
            mv.addObject("contestEntry", toContestEntryVO(contestEntry));
            mv.addObject("success", contestEntry != null);
        } catch (Exception e) {
            mv.addObject("success", false);
        }
        return mv;
    }
}
