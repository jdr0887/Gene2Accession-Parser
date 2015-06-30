package org.renci.gene2accession;

import org.renci.gene2accession.model.Record;

public interface G2AFilter {

    public boolean accept(Record record);

}
