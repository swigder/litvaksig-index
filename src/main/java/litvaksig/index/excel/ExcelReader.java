package litvaksig.index.excel;

import litvaksig.index.excel.annotation.ExcelColumn;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    public static <T> List<T> extractDataFromFile(String filename, int sheetToExtract, int headerRows, Class<T> typeToExtract)
            throws IllegalArgumentException {
        Map<Field, Integer> fields = getFields(typeToExtract);
        List<T> items = new ArrayList<>();

        try (InputStream inp = new FileInputStream(filename)) {
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            HSSFSheet sheet = wb.getSheetAt(sheetToExtract);

            Iterator<Row> rowIterator = sheet.rowIterator();
            for (int i = 0; i < headerRows; i++) { // ignore header rows
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                T newItem = typeToExtract.newInstance();
                Row row = rowIterator.next();
                for (Map.Entry<Field, Integer> entry : fields.entrySet()) {
                    Cell cell = row.getCell(entry.getValue(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    entry.getKey().set(newItem, cell.getStringCellValue());
                }
                items.add(newItem);
            }
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return items;
    }

    private static <T> Map<Field, Integer> getFields(Class<T> typeToExtract) {
        Map<Field, Integer> fields = new HashMap<>();
        for (Field field : typeToExtract.getDeclaredFields()) {
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                int index = field.getAnnotation(ExcelColumn.class).index();
                fields.put(field, index);
            }
        }
        return fields;
    }
}
