package litvaksig.index.excel;

class InvalidExcelCellException extends RuntimeException {

    InvalidExcelCellException(int row, int column, Exception cause) {
        super(String.format("Invalid records at row %s, column %s", row, column), cause);
    }

}
