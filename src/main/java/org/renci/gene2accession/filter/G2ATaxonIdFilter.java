package org.renci.gene2accession.filter;

import org.renci.gene2accession.G2AFilter;
import org.renci.gene2accession.model.Record;

public class G2ATaxonIdFilter implements G2AFilter {

    private Integer taxonId;

    public G2ATaxonIdFilter(Integer taxonId) {
        super();
        this.taxonId = taxonId;
    }

    @Override
    public boolean accept(Record record) {
        if (record.getTaxonId().equals(taxonId)) {
            return true;
        }
        return false;
    }

}
