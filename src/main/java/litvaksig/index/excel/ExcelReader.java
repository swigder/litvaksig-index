package litvaksig.index.excel;

import litvaksig.index.excel.annotation.ExcelColumn;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExcelReader {
    public static <T> List<T> extractDataFromFile(String filename, int sheetToExtract, int headerRows, Class<T> typeToExtract)
            throws IllegalArgumentException {
        Map<Field, FieldInfo> fields = getFields(typeToExtract);
        List<T> items = new ArrayList<>();

        try (InputStream inp = new FileInputStream(filename)) {
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            HSSFSheet sheet = wb.getSheetAt(sheetToExtract);

            Iterator<Row> rowIterator = sheet.rowIterator();
            Map<String, Field> fieldsMissingIndex = fields.entrySet().stream()
                    .filter(entry -> entry.getValue().index == -1 && !"".equals(entry.getValue().headerName))
                    .collect(Collectors.toMap(entry -> entry.getValue().headerName, Map.Entry::getKey));
            for (int i = 0; i < headerRows; i++) { // ignore header rows
                Row row = rowIterator.next();
                if (fieldsMissingIndex.isEmpty()) { continue; }
                row.cellIterator().forEachRemaining(cell -> {
                    if (cell.getCellType() != CellType.STRING) { return; }
                    String cellValue = cell.getStringCellValue();
                    if (fieldsMissingIndex.containsKey(cellValue)) {
                        fields.get(fieldsMissingIndex.get(cellValue)).index = cell.getColumnIndex();
                        fieldsMissingIndex.remove(cellValue);
                    }
                });
            }
            while (rowIterator.hasNext()) {
                T newItem = typeToExtract.newInstance();
                Row row = rowIterator.next();
                fields.forEach((field, fieldInfo) -> {
                    Cell cell = row.getCell(fieldInfo.index, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    try {
                        if (fieldInfo.check) {
                            var safeValue = (cell.getCellType() == CellType.NUMERIC) ?
                                    String.valueOf(cell.getNumericCellValue()) :
                                    cell.getStringCellValue();
                            field.set(newItem, safeValue);
                        } else {
                            var rawValue = fieldInfo.method.invoke(cell);
                            var safeValue = fieldInfo.converter == null ? rawValue : fieldInfo.converter.apply(rawValue);
                            field.set(newItem, safeValue);
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        new InvalidExcelCellException(row.getRowNum(), cell.getColumnIndex(), e).printStackTrace();
                    }
                });
                items.add(newItem);
            }
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return items;
    }

    private static class FieldInfo {
        int index;
        Method method;
        Function converter;
        boolean check;
        String headerName;

        FieldInfo(int index, boolean check, Method method, Function converter, String headerName) {
            this.index = index;
            this.check = check;
            this.method = method;
            this.converter = converter;
            this.headerName = headerName;
        }
    }

    private static <T> Map<Field, FieldInfo> getFields(Class<T> typeToExtract) {
        Map<Class, Method> classMethodMap = new HashMap<>();
        try {
            classMethodMap.put(String.class, Cell.class.getMethod("getStringCellValue"));
            classMethodMap.put(Double.class, Cell.class.getMethod("getNumericCellValue"));
            classMethodMap.put(Integer.class, Cell.class.getMethod("getNumericCellValue"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Map<Class, Function> converterMap = new HashMap<>();
        converterMap.put(Integer.class, (Function<Double, Integer>) Double::intValue);

        Map<Field, FieldInfo> fields = new HashMap<>();
        for (Field field : typeToExtract.getDeclaredFields()) {
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                Class<?> type = field.getType();
                fields.put(field, new ExcelReader.FieldInfo(annotation.index(), annotation.checkType(),
                        classMethodMap.get(type), converterMap.get(type), annotation.fromHeader()));
            }
        }
        return fields;
    }
}
