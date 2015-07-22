package org.renci.gene2accession.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.renci.gene2accession.G2AFilter;
import org.renci.gene2accession.model.Record;

public class G2AAssemblyFilter implements G2AFilter {

    private final Pattern p;

    public G2AAssemblyFilter(String assembly) {
        super();
        p = Pattern.compile(assembly);
    }

    @Override
    public boolean accept(Record record) {
        Matcher m = p.matcher(record.getAssembly());
        if (StringUtils.isNotEmpty(record.getAssembly()) && m.matches()) {
            return true;
        }
        return false;
    }

}
