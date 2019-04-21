package com.mall.bit.cqt.mall.entity;

public class Orderdetail {

	private Integer dId;

    private String oId;
    
    private String sName;

	private Long sId;

    private Integer oNum;

    private Double oMoney;
    
    private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item ) {
		this.item = item;
	}

	public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

    public Integer getoNum() {
        return oNum;
    }

    public void setoNum(Integer oNum) {
        this.oNum = oNum;
    }

    public Double getoMoney() {
        return oMoney;
    }

    public void setoMoney(Double oMoney) {
        this.oMoney = oMoney;
    }

    @Override
   	public String toString() {
   		return "Orderdetail [dId=" + dId + ", oId=" + oId + ", sName=" + sName
   				+ ", sId=" + sId + ", oNum=" + oNum + ", oMoney=" + oMoney
   				+ ", snackinfo=" + item + "]";
   	}

}