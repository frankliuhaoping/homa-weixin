package cn.cnyirui.framework.extension.excel;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cnyirui.framework.dao.common.CommonDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.framework.model.po.rbac.SysMenu;
import cn.cnyirui.framework.model.vo.JsonResult;
import cn.cnyirui.framework.utils.POIUtils;
import cn.cnyirui.framework.utils.SpringUtils;

/**
 * 导入excel中的数据到实体类
 * 
 * @author pengzhihua
 *
 */
@Service
public class ExcelService {

    @Resource
    private CommonDao commonDao;
    @Resource
    private ConversionService conversionService;

    private BeanWrapper getBeanWrapper(Object object) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(object);
        beanWrapper.setAutoGrowNestedPaths(true);
        beanWrapper.setConversionService(conversionService);
        return beanWrapper;
    }

    /**
     * 根据实体类的注解生成List<ExcelImportColumn>
     * 
     * @param clazz
     * @param annotationClazz
     * @return
     */
    private List<ExcelImportColumn> buildExcelImportColumn(Class<?> clazz) {
        List<ExcelImportColumn> result = new ArrayList<ExcelImportColumn>();
        Field[] fields = FieldUtils.getAllFields(clazz);
        for (Field field : fields) {
            ExcelImportable excelImportable = field.getAnnotation(ExcelImportable.class);
            if (excelImportable != null) {
                ExcelImportColumn excelColumn = new ExcelImportColumn();
                excelColumn.setIndex(excelImportable.index());
                excelColumn.setTitle(excelImportable.title());
                excelColumn.setWidth(excelImportable.width());
                excelColumn.setPropertyName(field.getName());
                excelColumn.setComment(excelImportable.comment());
                excelColumn.setParseMethodName(excelImportable.parseMethodName());
                excelColumn.setIsUnique(excelImportable.isUnique());
                excelColumn.setValidateMethodName(excelImportable.validateMethodName());
                result.add(excelColumn);
            }
        }
        return result;
    }

    /**
     * 执行parseMethod、validateMethod或者formatMethod，先在实体类找，然后在实体类对应的Service中找
     * 
     * @param object
     * @param methodName
     * @param params
     * @return
     */
    private Object invokeMethod(Object object, String methodName, Object... params) {
        if (object != null && StringUtils.isNotBlank(methodName)) {
            Class<?>[] clazzArray = new Class<?>[params.length];
            for (int j = 0; j < params.length; j++) {
                clazzArray[j] = (j == 0 || params[j] == null ? Object.class : params[j].getClass());
            }
            Method method = MethodUtils.getAccessibleMethod(object.getClass(), methodName, clazzArray);
            if (method == null) {
                object = SpringUtils.getServiceBean(object.getClass().getSimpleName());
                if (object != null) {
                    method = MethodUtils.getAccessibleMethod(object.getClass(), methodName, clazzArray);
                }
            }
            if (method != null) {
                try {
                    return method.invoke(object, params);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 检查是否有配置导入注解
     * 
     * @param clazz
     * @return
     */
    public boolean canImportFromExcel(Class<?> clazz) {
        Field[] fields = FieldUtils.getFieldsWithAnnotation(clazz, ExcelImportable.class);
        return ArrayUtils.isNotEmpty(fields);
    }

    /**
     * 生成导入的模板文件
     * 
     * @param clazz
     * @return
     */
    public <T extends BaseEntity> void buildExcelTemplateFile(Class<T> clazz, OutputStream outputStream) {
        List<ExcelImportColumn> excelColumnList = buildExcelImportColumn(clazz);
        if (excelColumnList.isEmpty()) {
            return;
        }
        Workbook workbook = POIUtils.createWorkBook(true, true);
        Sheet sheet = workbook.getSheetAt(0);
        sheet.createFreezePane(0, 1);

        Row row = sheet.createRow(0);
        // 标题行样式
        CellStyle cellStyle = POIUtils.defaultHeaderRowCellStyle(sheet.getWorkbook());
        // 列样式
        CellStyle columnStyle = sheet.getWorkbook().createCellStyle();
        columnStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        for (ExcelImportColumn excelColumn : excelColumnList) {
            Cell cell = row.createCell(excelColumn.getIndex());
            cell.setCellStyle(cellStyle);
            sheet.setColumnWidth(excelColumn.getIndex(), excelColumn.getWidth() * 256);
            sheet.setDefaultColumnStyle(excelColumn.getIndex(), columnStyle);

            cell.setCellValue(excelColumn.getTitle());
            if (StringUtils.isNotBlank(excelColumn.getComment())) {
                cell.setCellComment(POIUtils.createCellComment(sheet, excelColumn.getComment()));
            }
        }
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        POIUtils.closeWorkBook(workbook);
    }

    /**
     * 从excel文件导入
     * 
     * @param excelFile
     * @param clazz
     */
    @Transactional
    public <T extends BaseEntity> JsonResult importFromExcel(File excelFile, Class<T> clazz) {
        List<ExcelImportColumn> excelColumnList = buildExcelImportColumn(clazz);
        if (excelColumnList.isEmpty()) {
            return JsonResult.error("无导入配置，请检查！" + clazz.getSimpleName());
        }
        Workbook wb = POIUtils.createWorkBook(excelFile);
        try {
            // 循环工作表Sheet
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                Sheet sheet = wb.getSheetAt(i);
                if (sheet == null) {
                    continue;
                }
                // 循环行Row
                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (row == null) {
                        continue;
                    }

                    T entity = clazz.newInstance();
                    BeanWrapper beanWrapper = getBeanWrapper(entity);
                    for (ExcelImportColumn excelColumn : excelColumnList) {
                        Object value = POIUtils.getCellValue(row, excelColumn.getIndex());
                        if (StringUtils.isNotBlank(excelColumn.getParseMethodName())) {
                            value = invokeMethod(entity, excelColumn.getParseMethodName(), value,
                                    excelColumn.getPropertyName());
                        }
                        if (excelColumn.getIsUnique() && commonDao.exists(entity.getClass().getSimpleName(),
                                Searchable.newSearchable().addSearchFilter(
                                        excelColumn.getPropertyName(), SearchOperator.eq, value))) {
                            throw new RuntimeException(value + " 数据重复，请检查后再重试！");
                        }
                        if (StringUtils.isNotBlank(excelColumn.getValidateMethodName())) {
                            Object bObject = invokeMethod(entity, excelColumn.getValidateMethodName(), value,
                                    excelColumn.getPropertyName());
                            if (bObject != null && BooleanUtils.isFalse(BooleanUtils.toBoolean(bObject.toString()))) {
                                throw new RuntimeException(value + " 数据未能通过验证，请检查后再重试！");
                            }
                        }
                        beanWrapper.setPropertyValue(excelColumn.getPropertyName(), value);
                    }
                    commonDao.save(entity);
                }
            }
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(e.getMessage());
        } finally {
            POIUtils.closeWorkBook(wb);
        }
    }

    /**
     * 导入excel文件
     * 
     * @param excelFile
     * @param clazz
     * @return
     */
    public <T extends BaseEntity> JsonResult importFromExcel(String excelFile, Class<T> clazz) {
        File file = new File(excelFile);
        return importFromExcel(file, clazz);
    }

    /**
     * 根据实体类的注解生成List<ExcelExportColumn>
     * 
     * @param clazz
     * @param annotationClazz
     * @return
     */
    private List<ExcelExportColumn> buildExcelExportColumn(Class<?> clazz) {
        List<ExcelExportColumn> result = new ArrayList<ExcelExportColumn>();
        Field[] fields = FieldUtils.getAllFields(clazz);
        int j = 0;
        for (Field field : fields) {
            ExcelExportable excelExportable = field.getAnnotation(ExcelExportable.class);
            if (excelExportable != null) {
                ExcelExportColumn excelColumn = new ExcelExportColumn();
                excelColumn.setIndex(excelExportable.index() == -1 ? j : excelExportable.index());
                excelColumn.setFormatMethodName(excelExportable.formatMethodName());

                String[] propertyNames = excelExportable.propertyNames();
                if (ArrayUtils.isEmpty(propertyNames)) {
                    propertyNames = ArrayUtils.add(propertyNames, field.getName());
                }
                excelColumn.setPropertyNames(propertyNames);
                excelColumn.setTitles(excelExportable.titles());

                excelColumn.setWidths(excelExportable.widths());
                result.add(excelColumn);
                j++;
            }

        }
        return result;
    }

    public <T extends BaseEntity> void exportToExcel(List<T> dataList, OutputStream outputStream) {
        if (dataList.isEmpty()) {
            return;
        }
        List<ExcelExportColumn> excelColumnList = buildExcelExportColumn(SysMenu.class);
        if (excelColumnList.isEmpty()) {
            return;
        }

        Workbook workbook = POIUtils.createWorkBook(true, true);
        Sheet sheet = workbook.getSheetAt(0);
        sheet.createFreezePane(0, 1);
        Row row = sheet.createRow(0);
        // 标题行样式
        CellStyle cellStyle = POIUtils.defaultHeaderRowCellStyle(sheet.getWorkbook());
        // 列样式
        CellStyle columnStyle = sheet.getWorkbook().createCellStyle();
        columnStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        for (ExcelExportColumn excelColumn : excelColumnList) {
            for (int i = 0; i < excelColumn.getPropertyNames().length; i++) {
                Cell cell = row.createCell(excelColumn.getIndex() + i);
                cell.setCellStyle(cellStyle);
                sheet.setColumnWidth(excelColumn.getIndex(), excelColumn.getWidths()[i] * 256);
                sheet.setDefaultColumnStyle(excelColumn.getIndex(), columnStyle);

                cell.setCellValue(excelColumn.getTitles()[i]);
            }
        }
        // 数据
        for (int i = 0; i < dataList.size(); i++) {
            BeanWrapper beanWrapper = getBeanWrapper(dataList.get(i));
            row = sheet.createRow(i + 1);
            for (ExcelExportColumn excelColumn : excelColumnList) {
                for (int j = 0; j < excelColumn.getPropertyNames().length; j++) {
                    Object value = beanWrapper.getPropertyValue(excelColumn.getPropertyNames()[j]);
                    if (StringUtils.isNotEmpty(excelColumn.getFormatMethodName())) {
                        value = invokeMethod(dataList.get(i), excelColumn.getFormatMethodName(), value,
                                excelColumn.getPropertyNames()[j]);
                    }
                    Cell cell = row.createCell(excelColumn.getIndex() + j);
                    cell.setCellValue(conversionService.convert(value, String.class));
                }
            }
        }
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        POIUtils.closeWorkBook(workbook);
    }

}
