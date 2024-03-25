package top.wayne06.generalbackend.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * SQL utils
 *
 * @author wayne06
 */
public class SqlUtils {

    /**
     * check if sort field is legal (prevent SQL injection)
     *
     * @param sortField
     * @return
     */
    public static boolean validSortField(String sortField) {
        if (StringUtils.isBlank(sortField)) {
            return false;
        }
        return !StringUtils.containsAny(sortField, "=", "(", ")", " ");
    }
}
