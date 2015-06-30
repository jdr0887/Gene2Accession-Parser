package org.renci.gene2accession.filter;

import java.util.List;

import org.renci.gene2accession.G2AFilter;
import org.renci.gene2accession.model.Record;

public class G2AAndFilter implements G2AFilter {

    private List<G2AFilter> filters;

    public G2AAndFilter(List<G2AFilter> filters) {
        super();
        this.filters = filters;
    }

    @Override
    public boolean accept(Record record) {
        for (G2AFilter f : filters) {
            if (!f.accept(record)) {
                return false;
            }
        }
        return true;
    }

}
