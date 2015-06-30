package org.renci.gene2accession.parser;

import java.util.List;

import org.renci.gene2accession.model.Record;

public class AndFilter implements Filter {

    private List<Filter> filters;

    public AndFilter(List<Filter> filters) {
        super();
        this.filters = filters;
    }

    @Override
    public boolean accept(Record record) {
        for (Filter f : filters) {
            if (!f.accept(record)) {
                return false;
            }
        }
        return true;
    }

}
