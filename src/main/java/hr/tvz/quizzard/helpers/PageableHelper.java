package hr.tvz.quizzard.helpers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableHelper {

    public static Pageable getPageableObject(int pageIndex, int pageSize){
        return PageRequest.of(pageIndex, pageSize);
    }

    public static Pageable getPageableObject(int pageIndex, int pageSize, String sortField, Boolean desc){
        Sort sort = Sort.by(desc ? Sort.Order.desc(sortField) : Sort.Order.asc(sortField));

        return PageRequest.of(pageIndex, pageSize, sort);
    }

    public static Pageable getPageableObject(String sortField, Boolean desc){
        Sort sort = Sort.by(sortField);
        if (desc) { //ako je desc stavljen na true, sortiraj descending, inace ascending
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        return PageRequest.of(0, Integer.MAX_VALUE, sort);
    }

}
