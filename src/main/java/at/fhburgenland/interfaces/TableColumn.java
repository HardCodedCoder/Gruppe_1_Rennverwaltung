package at.fhburgenland.interfaces;

public interface TableColumn<T> {
    String getHeader();
    Object getValue(T data);
}
