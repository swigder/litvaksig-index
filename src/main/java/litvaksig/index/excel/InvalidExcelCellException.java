package litvaksig.index.excel;

public class InvalidExcelCellException extends RuntimeException {
    public InvalidExcelCellException(int row, int column, Exception cause) {
        super(String.format("Invalid data at row %s, column %s", row, column), cause);
    }
}
