package Models;

public class Cart {
    public String pid,pname,price,img;
    public Cart(){
    }

    public Cart(String pid, String pname, String price, String img) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.img = img;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
