package org.renci.gene2accession.filter;

import org.apache.commons.lang3.StringUtils;
import org.renci.gene2accession.G2AFilter;
import org.renci.gene2accession.model.Record;

public class G2AAssemblyFilter implements G2AFilter {

    private String assembly;

    public G2AAssemblyFilter(String assembly) {
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
