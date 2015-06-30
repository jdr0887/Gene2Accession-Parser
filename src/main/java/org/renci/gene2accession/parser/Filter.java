package org.renci.gene2accession.parser;

import org.renci.gene2accession.model.Record;

public interface Filter {

    public boolean accept(Record record);

}
