package org.renci.gene2accession.model;

/**
 * 
 * 
 * @author jdr0887
 */
public class Record {

    private Integer taxonId;

    private Long geneId;

    private StatusType status;

    private String RNANucleotideAccessionVersion;

    private Long RNANucleotideGenInfoId;

    private String proteinAccessionVersion;

    private Long proteinGenInfoId;

    private String genomicNucleotideAccessionVersion;

    private Long genomicNucleotideGenInfoId;

    private Integer genomicStartPosition;

    private Integer genomicEndPosition;

    private OrientationType orientation;

    private String assembly;

    private String maturePeptideAccessionVersion;

    private Long maturePeptideGenInfoId;

    private String symbol;

    public Record() {
        super();
    }

    public Integer getTaxonId() {
        return taxonId;
    }

    public void setTaxonId(Integer taxonId) {
        this.taxonId = taxonId;
    }

    public Long getGeneId() {
        return geneId;
    }

    public void setGeneId(Long geneId) {
        this.geneId = geneId;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public String getRNANucleotideAccessionVersion() {
        return RNANucleotideAccessionVersion;
    }

    public void setRNANucleotideAccessionVersion(String rNANucleotideAccessionVersion) {
        RNANucleotideAccessionVersion = rNANucleotideAccessionVersion;
    }

    public Long getRNANucleotideGenInfoId() {
        return RNANucleotideGenInfoId;
    }

    public void setRNANucleotideGenInfoId(Long rNANucleotideGenInfoId) {
        RNANucleotideGenInfoId = rNANucleotideGenInfoId;
    }

    public String getProteinAccessionVersion() {
        return proteinAccessionVersion;
    }

    public void setProteinAccessionVersion(String proteinAccessionVersion) {
        this.proteinAccessionVersion = proteinAccessionVersion;
    }

    public Long getProteinGenInfoId() {
        return proteinGenInfoId;
    }

    public void setProteinGenInfoId(Long proteinGenInfoId) {
        this.proteinGenInfoId = proteinGenInfoId;
    }

    public String getGenomicNucleotideAccessionVersion() {
        return genomicNucleotideAccessionVersion;
    }

    public void setGenomicNucleotideAccessionVersion(String genomicNucleotideAccessionVersion) {
        this.genomicNucleotideAccessionVersion = genomicNucleotideAccessionVersion;
    }

    public Long getGenomicNucleotideGenInfoId() {
        return genomicNucleotideGenInfoId;
    }

    public void setGenomicNucleotideGenInfoId(Long genomicNucleotideGenInfoId) {
        this.genomicNucleotideGenInfoId = genomicNucleotideGenInfoId;
    }

    public Integer getGenomicStartPosition() {
        return genomicStartPosition;
    }

    public void setGenomicStartPosition(Integer genomicStartPosition) {
        this.genomicStartPosition = genomicStartPosition;
    }

    public Integer getGenomicEndPosition() {
        return genomicEndPosition;
    }

    public void setGenomicEndPosition(Integer genomicEndPosition) {
        this.genomicEndPosition = genomicEndPosition;
    }

    public OrientationType getOrientation() {
        return orientation;
    }

    public void setOrientation(OrientationType orientation) {
        this.orientation = orientation;
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly;
    }

    public String getMaturePeptideAccessionVersion() {
        return maturePeptideAccessionVersion;
    }

    public void setMaturePeptideAccessionVersion(String maturePeptideAccessionVersion) {
        this.maturePeptideAccessionVersion = maturePeptideAccessionVersion;
    }

    public Long getMaturePeptideGenInfoId() {
        return maturePeptideGenInfoId;
    }

    public void setMaturePeptideGenInfoId(Long maturePeptideGenInfoId) {
        this.maturePeptideGenInfoId = maturePeptideGenInfoId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return String
                .format("Record [taxonId=%s, geneId=%s, status=%s, RNANucleotideAccessionVersion=%s, RNANucleotideGenInfoId=%s, proteinAccessionVersion=%s, proteinGenInfoId=%s, genomicNucleotideAccessionVersion=%s, genomicNucleotideGenInfoId=%s, genomicStartPosition=%s, genomicEndPosition=%s, orientation=%s, assembly=%s, maturePeptideAccessionVersion=%s, maturePeptideGenInfoId=%s, symbol=%s]",
                        taxonId, geneId, status, RNANucleotideAccessionVersion, RNANucleotideGenInfoId,
                        proteinAccessionVersion, proteinGenInfoId, genomicNucleotideAccessionVersion,
                        genomicNucleotideGenInfoId, genomicStartPosition, genomicEndPosition, orientation, assembly,
                        maturePeptideAccessionVersion, maturePeptideGenInfoId, symbol);
    }

}
