package top.wayne06.generalbackend.common;

import top.wayne06.generalbackend.constant.CommonConstant;
import lombok.Data;

/**
 * Paging request
 *
 * @author wayne06
 */
@Data
public class PageRequest {

    /**
     * current page number
     */
    private int current = 1;

    /**
     * record size in 1 page
     */
    private int pageSize = 10;

    /**
     * sort field
     */
    private String sortField;

    /**
     * sort order (default asc)
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}
