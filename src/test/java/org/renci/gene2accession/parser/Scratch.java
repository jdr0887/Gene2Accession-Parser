package org.renci.gene2accession.parser;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.renci.gene2accession.model.Record;

public class Scratch {

    @Test
    public void testFilterAssembly() {
        Gene2AccessionParser parser = Gene2AccessionParser.getInstance();

        AndFilter andFilter = new AndFilter(Arrays.asList(new Filter[] { new TaxonIdFilter(9606),
                new AssemblyFilter("Reference GRCh38.p2 Primary Assembly") }));
        List<Record> recordList = parser.parse(andFilter, new File("/tmp", "gene2refseq.filtered.gz"));
        assertTrue(recordList != null && !recordList.isEmpty());
        for (Record record : recordList) {
            assertTrue(record.getTaxonId().equals(9606));
            assertTrue(record.getAssembly().contains("GRCh38.p2"));
        }

    }
}
