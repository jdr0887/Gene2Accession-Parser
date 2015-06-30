package org.renci.gene2accession.parser;

import org.renci.gene2accession.model.Record;

public class TaxonIdFilter implements Filter {

    private Integer taxonId;

    public TaxonIdFilter(Integer taxonId) {
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
