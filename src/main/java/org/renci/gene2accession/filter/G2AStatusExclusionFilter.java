package org.renci.gene2accession.filter;

import java.util.List;

import org.renci.gene2accession.G2AFilter;
import org.renci.gene2accession.model.Record;
import org.renci.gene2accession.model.StatusType;

public class G2AStatusExclusionFilter implements G2AFilter {

    private List<StatusType> statusList;

    public G2AStatusExclusionFilter(final List<StatusType> statusList) {
        super();
        this.statusList = statusList;
    }

    @Override
    public boolean accept(Record record) {
        if (record.getStatus() != null && !statusList.contains(record.getStatus())) {
            return true;
        }
        return false;
    }

}
