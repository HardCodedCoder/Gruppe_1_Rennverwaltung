package at.fhburgenland.org.lecture.interfaces;

public interface TableColumn<T> {
    String getHeader();
    Object getValue(T data);
}
