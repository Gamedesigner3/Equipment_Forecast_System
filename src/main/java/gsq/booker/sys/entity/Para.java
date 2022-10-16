package gsq.booker.sys.entity;

public class Para {

    private Integer Id;

    private Float Idx1;

    public Integer getId() {return Id;}

    public void setId(Integer Id) {this.Id = Id;}

    public Float getIdx1() {return Idx1;}

    public void setIdx1(Float Idx1) {this.Idx1 = Idx1;}

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", Id=").append(Id);
        sb.append(", Idx1=").append(Idx1);
        sb.append("]");
        return sb.toString();
    }
}
