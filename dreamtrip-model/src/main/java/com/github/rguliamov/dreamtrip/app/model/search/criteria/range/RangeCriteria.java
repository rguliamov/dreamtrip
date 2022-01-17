package com.github.rguliamov.dreamtrip.app.model.search.criteria.range;

import com.google.common.base.Preconditions;

/**
 * @author Guliamov Rustam
 *
 * Pagination parameter for data retrieval operation
 */
public class RangeCriteria {
    /**
     * Page index(0-base)
     *
     */
    private final int page;

    /**
     * Number of elements per page
     */
    private final int rowCount;

    public RangeCriteria(int page, int rowCount) {
        Preconditions.checkArgument(page >= 0, "Incorrect page index:%s", page);
        Preconditions.checkArgument(rowCount >= 0, "Incorrect row count:%s", rowCount);
        this.page = page;
        this.rowCount = rowCount;
    }

    public int getPage() {
        return page;
    }

    public int getRowCount() {
        return rowCount;
    }
}
