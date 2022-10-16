package gsq.booker.sys.controller.vo;

import java.io.Serializable;

public class cartVO implements Serializable {

    private Integer cartId;

    private Integer Id;

    private Integer paramCount;

    private Float Idx1;

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Integer getId() {
        return Id;
    }

    public void setParamCount(Integer paramCount) {
        this.paramCount = paramCount;
    }

    public Integer getParamCount() {
        return paramCount;
    }

    public void setIdx1(Float Idx1) {
        this.Idx1 = Idx1;
    }

    public Float getIdx1() {
        return Idx1;
    }
}
