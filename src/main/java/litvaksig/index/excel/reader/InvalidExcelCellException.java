package litvaksig.index.excel.reader;

class InvalidExcelCellException extends RuntimeException {

    InvalidExcelCellException(int row, int column, Exception cause) {
        super(String.format("Invalid data at row %s, column %s", row, column), cause);
    }

}
