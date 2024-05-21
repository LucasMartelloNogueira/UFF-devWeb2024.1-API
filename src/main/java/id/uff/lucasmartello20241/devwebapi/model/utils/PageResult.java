package id.uff.lucasmartello20241.devwebapi.model.utils;

import java.util.List;

public record PageResult<T>(
    long totalOfItems,
    int totalOfPages,
    int pageNumber,
    int pageTotal,
    List<T> items
) {
    
}
