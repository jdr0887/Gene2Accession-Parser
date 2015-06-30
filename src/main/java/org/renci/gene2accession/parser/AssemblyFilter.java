package org.renci.gene2accession.parser;

import org.apache.commons.lang3.StringUtils;
import org.renci.gene2accession.model.Record;

public class AssemblyFilter implements Filter {

    private String assembly;

    public AssemblyFilter(String assembly) {
        super();
        this.assembly = assembly;
    }

    @Override
    public boolean accept(Record record) {
        if (StringUtils.isNotEmpty(record.getAssembly()) && record.getAssembly().equals(assembly)) {
            return true;
        }
        return false;
    }

}
