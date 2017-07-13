package com.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelUtil {
	/**
	 * 二位码按批次号导出
	 * @param batchNos
	 * @return
	 */
	public InputStream exportExcelByBatchNos(String batchNos) {
		InputStream excelFile  = null;
		HSSFWorkbook wb = new HSSFWorkbook();
		// 在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("二维码");
		// 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		// 生成一个字体
		HSSFFont font = wb.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);


		// 写列名
		HSSFCell cell;
		short cellCount ;
		cell = row.createCell(0);
		cell.setCellValue("批次号");
		cell.setCellStyle(style);
		cellCount = row.getFirstCellNum();


		cell = row.createCell(1);
		cell.setCellValue("生成箱数");
		cell.setCellStyle(style);
		cellCount = row.getLastCellNum();


		cell = row.createCell(cellCount);
		cell.setCellValue("每箱数量");
		cell.setCellStyle(style);
		cellCount = row.getLastCellNum();

		cell = row.createCell(cellCount);
		cell.setCellValue("商品ID");
		cell.setCellStyle(style);
		cellCount = row.getLastCellNum();

		cell = row.createCell(cellCount);
		cell.setCellValue("skuCode");
		cell.setCellStyle(style);
		cellCount = row.getLastCellNum();

		cell = row.createCell(cellCount);
		cell.setCellValue("有效日期");
		cell.setCellStyle(style);
		cellCount = row.getLastCellNum();

		cell = row.createCell(cellCount);
		cell.setCellValue("生成日期");
		cell.setCellStyle(style);
		cellCount = row.getLastCellNum();

		cell = row.createCell(cellCount);
		cell.setCellValue("操作人");
		cell.setCellStyle(style);
		cellCount = row.getLastCellNum();

		cell = row.createCell(cellCount);
		cell.setCellValue("备注");
		cell.setCellStyle(style);
		cellCount = row.getLastCellNum();

		cell = row.createCell(cellCount);
		cell.setCellValue("二维码连接地址");
		cell.setCellStyle(style);
		cellCount = row.getLastCellNum();

		// 取数据
		List exOrderInfo = new ArrayList();//一般是从数据库中查询出来的 ,注意顺序
		if (exOrderInfo != null) {
			for (int i = 0; i < exOrderInfo.size(); i++) {
				row = sheet.createRow(i + 1);
				row.createCell(0).setCellValue(i + 1);
				Object[] objExOrderInfo = (Object[]) exOrderInfo.get(i);

				int objExOrderInfoLength=objExOrderInfo.length;
				for (int k = 0; k < objExOrderInfoLength; k++) {
					String s = String.valueOf(objExOrderInfo[k]);
					s = (s == "null") ? "" : s;
					if(k==objExOrderInfoLength-1){
						row.createCell(k).setCellValue("?code=");
					}else{
						row.createCell(k).setCellValue(s);
					}					
				}
			}
			// 自动设置EXCEL的列宽，视自己的需求而定
			sheet.autoSizeColumn((short) 0);
			sheet.autoSizeColumn((short) 1);
			sheet.autoSizeColumn((short) 2);
			sheet.autoSizeColumn((short) 3);
			sheet.autoSizeColumn((short) 4);
			sheet.autoSizeColumn((short) 5);
			sheet.autoSizeColumn((short) 6);
			sheet.autoSizeColumn((short) 7);
			sheet.autoSizeColumn((short) 8);
			sheet.autoSizeColumn((short) 9);
			sheet.autoSizeColumn((short) 10);
			sheet.autoSizeColumn((short) 11);
			sheet.autoSizeColumn((short) 12);
			sheet.autoSizeColumn((short) 13);

			// 设置文件名，用格式化日期来生成一个ID
			String filePath = "";
			Date dt = new Date();
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String date = df.format(dt).toString();
			filePath = "qrBatch_" + date + ".xls";
			try {
				// OutputStream out=new FileOutputStream(file);
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				wb.write(output);
				byte[] ba = output.toByteArray();
				excelFile = new ByteArrayInputStream(ba);
				output.flush();
				output.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return excelFile;

		}
		return excelFile;
	}

	public static void importExcel() throws BiffException, IOException{
		File file = new File("");
		Workbook rwb=Workbook.getWorkbook(file);//file是excel文件路径
		Sheet rs = rwb.getSheet(0);
		int col = rs.getColumns();//得到所有的列
		int row = rs.getRows();//得到所有的行
		System.out.println("col"+col+" rows:"+row);
		//创建新增时 的二维码 创建时间,查询次数,创建人, 箱数 =1     每箱数量(无法设置)
		Map<String,Long> perQuantityMap = new HashMap<String,Long>(); //String 代表batchNo, Long代表每箱数量
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				//getCell(x,y) 代表 第y行第x列

			}

		}//for j end
	}
}
