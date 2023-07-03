package at.fhburgenland.view.table;

import at.fhburgenland.interfaces.TableColumn;

import java.util.List;

public class TableRenderer<T> {

    public void renderTable(List<T> toRender, TableColumn<T>... columns) {
        int[] columnWidths = calculateColumnWidths(toRender, columns);
        printTableHeader(columnWidths, columns);
        printTableSeparator(columnWidths);
        printTableData(toRender, columnWidths, columns);
        printTableBottomLine(columnWidths);
    }

    private int[] calculateColumnWidths(List<T> toRender, TableColumn<T>[] columns) {
        int[] columnWidths = new int[columns.length];

        for (int i = 0; i < columns.length; i++) {
            int headerWidth = columns[i].getHeader().length();
            columnWidths[i] = Math.max(headerWidth, getColumnMaxWidth(toRender, columns[i]));
        }

        return columnWidths;
    }

    private int getColumnMaxWidth(List<T> toRender, TableColumn<T> column) {
        int maxWidth = column.getHeader().length();

        for (T data : toRender) {
            String cellValue = column.getValue(data).toString();
            maxWidth = Math.max(maxWidth, cellValue.length());
        }

        return maxWidth;
    }

    private void printTableHeader(int[] columnWidths, TableColumn<T>[] columns) {
        StringBuilder topLineBuilder = new StringBuilder("┏");
        StringBuilder headerLineBuilder = new StringBuilder("┃");

        for (int i = 0; i < columns.length; i++) {
            String header = columns[i].getHeader();
            int width = columnWidths[i] + 2;

            topLineBuilder.append("━".repeat(width));
            headerLineBuilder.append(" " + header + " ".repeat(width - header.length() - 1)).append("┃");

            if (i < columns.length - 1) {
                topLineBuilder.append("┳");
            } else {
                topLineBuilder.append("┓");
            }
        }

        System.out.println(topLineBuilder);
        System.out.println(headerLineBuilder);
    }

    private void printTableSeparator(int[] columnWidths) {
        StringBuilder separatorLineBuilder = new StringBuilder("┣");

        for (int width : columnWidths) {
            separatorLineBuilder.append("━".repeat(width + 2)).append("┿");
        }

        separatorLineBuilder.setCharAt(separatorLineBuilder.length() - 1, '┫');
        System.out.println(separatorLineBuilder);
    }

    private void printTableData(List<T> toRender, int[] columnWidths, TableColumn<T>[] columns) {
        for (T data : toRender) {
            StringBuilder rowBuilder = new StringBuilder("┃");

            for (int i = 0; i < columns.length; i++) {
                String cellValue = columns[i].getValue(data).toString();
                int width = columnWidths[i] + 2;
                rowBuilder.append(" ").append(cellValue).append(" ".repeat(width - cellValue.length() - 1)).append("┃");
            }

            System.out.println(rowBuilder);
        }
    }

    private void printTableBottomLine(int[] columnWidths) {
        StringBuilder bottomLineBuilder = new StringBuilder("┗");

        for (int width : columnWidths) {
            bottomLineBuilder.append("━".repeat(width + 2)).append("┷");
        }

        bottomLineBuilder.setCharAt(bottomLineBuilder.length() - 1, '┛');
        System.out.println(bottomLineBuilder);
    }
}

