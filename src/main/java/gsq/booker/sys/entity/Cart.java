package gsq.booker.sys.entity;

public class Cart {
    private Integer cartId;

    private Long userId;

    private Integer paramId;

    private Integer paramCount;

    public Integer getCartId() {return cartId;}

    public void setCartId(Integer cartId) {this.cartId = cartId;}

    public Long getUserId() {return userId;}

    public void setUserId(Long userId) {this.userId = userId;}

    public Integer getParamId() {return paramId;}

    public void setParamId(Integer paramId) {this.paramId = paramId;}

    public Integer getParamCount() {return paramCount;}

    public void setParamCount(Integer paramCount) {this.paramCount = paramCount;}

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cartId=").append(cartId);
        sb.append(", userId=").append(userId);
        sb.append(", paramId=").append(paramId);
        sb.append(", paramCount").append(paramCount);
        sb.append("]");
        return sb.toString();
    }
}
