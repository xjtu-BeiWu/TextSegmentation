package process;

public class RecordBean {
    public RecordBean(){
    }

    private String facet1;
    private String n1;
    private String facet2;
    private String n2;
    private Double sim;

    public RecordBean(String facet1, String n1, String facet2, String n2, Double sim) {
        super();
        this.facet1 = facet1;
        this.n1 = n1;
        this.facet2 = facet2;
        this.n2 = n2;
        this.sim = sim;
    }

    public String getFacet1() {
        return facet1;
    }

    public void setFacet1(String facet1) {
        this.facet1 = facet1;
    }

    public String getN1() {
        return n1;
    }

    public void setN1(String n1) {
        this.n1 = n1;
    }

    public String getFacet2() {
        return facet2;
    }

    public void setFacet2(String facet2) {
        this.facet2 = facet2;
    }

    public String getN2() {
        return n2;
    }

    public void setN2(String n2) {
        this.n2 = n2;
    }

    public Double getSim() {
        return sim;
    }

    public void setSim(Double sim) {
        this.sim = sim;
    }

    @Override
    public String toString() {
        return facet1+','+n1+','+facet2+','+n2+','+sim;
    }
}

