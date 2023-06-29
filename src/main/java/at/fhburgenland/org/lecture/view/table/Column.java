package at.fhburgenland.org.lecture.view.table;

import at.fhburgenland.org.lecture.interfaces.TableColumn;

import java.util.function.Function;

public class Column<T, R> implements TableColumn<T> {

    private final String header;
    private final Function<T, R> valueExtractor;

    public Column(String header, Function<T, R> valueExtractor) {
        this.header = header;
        this.valueExtractor = valueExtractor;
    }

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public Object getValue(T data) {
        return valueExtractor.apply(data);
    }
}

