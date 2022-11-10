package com.itstep.holemole.novaPoshta;

public class Areas {

    public Areas(String Ref, String AreasCenter, String DescriptionRu, String Description) {
        this.Ref = Ref;
        this.AreasCenter = AreasCenter;
        this.DescriptionRu = DescriptionRu;
        this.Description = Description;
    }

    public String Ref;
    public String AreasCenter;
    public String DescriptionRu;
    public String Description;

    @Override
    public String toString() {
        return "Areas{" +
                "Ref='" + Ref + '\'' +
                ", AreasCenter='" + AreasCenter + '\'' +
                ", DescriptionRu='" + DescriptionRu + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
