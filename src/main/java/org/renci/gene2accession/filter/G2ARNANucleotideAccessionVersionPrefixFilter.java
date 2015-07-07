package org.renci.gene2accession.filter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.renci.gene2accession.G2AFilter;
import org.renci.gene2accession.model.Record;

public class G2ARNANucleotideAccessionVersionPrefixFilter implements G2AFilter {

    private List<String> prefixList;

    public G2ARNANucleotideAccessionVersionPrefixFilter(final List<String> prefixList) {
        super();
        this.prefixList = prefixList;
    }

    @Override
    public boolean accept(Record record) {
        if (StringUtils.isNotEmpty(record.getRNANucleotideAccessionVersion())
                && prefixList.contains(record.getRNANucleotideAccessionVersion().substring(0, 3))) {
            return true;
        }
        return false;
    }

}
