package PIP;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.imageio.ImageIO;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name="user")
@ApplicationScoped
public class UserBean implements Serializable {
    private double valueX = 0.0;
    private double valueY = 0.0;
    private double valueR = 1.0;
    private final List<Data> data = new ArrayList<Data>();
    private StreamedContent graph;
    private double xgr = 0.0;
    private double ygr = 0.0;
    private String textValueY;
    private String textValueR;
    private boolean incorrect=false;

    public String getTextValueR() {
        return textValueR;
    }

    public void setTextValueR(String textValueR) {
        try{
        valueR = Double.parseDouble(textValueR.replace(",", "."));
        this.textValueR=textValueR;
        
        }
        catch(Exception e){
            incorrect=true;
             this.valueR=0;
        }
        this.setGraph();
    }

    public String getTextValueY() {
        return textValueY;
    }

    public void setTextValueY(String textValueY) {
        try{
        valueY = Double.parseDouble(textValueY.replace(",", "."));
        this.textValueY=textValueY;
        }
        catch(Exception e){
            incorrect=true;
        }
    }

    @PostConstruct
    public void initGraph() {
        this.setGraph();
    }

    public StreamedContent getGraph() {
        return this.graph;
    }

    public void setGraph() {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            BufferedImage img = GraphicImage.genImage(this.data, 400, 400, this.valueR);
            ImageIO.write((RenderedImage)img, "png", os);
            this.graph = new DefaultStreamedContent((InputStream)new ByteArrayInputStream(os.toByteArray()), "image/png");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setXgr(double value) {
        this.xgr = value;
    }

    public double getXgr() {
        return this.xgr;
    }

    public void setYgr(double value) {
        this.ygr = value;
    }

    public double getYgr() {
        return this.ygr;
    }

    public void updateTable() {
        if(this.valueY<-3 || this.valueY>3)
            return;
        if(this.valueR<1 || this.valueR>4)
            return;
        this.data.add(new Data(this.valueX, this.valueY, this.valueR, UserBean.checkResult(this.valueX, this.valueY, this.valueR)));
        this.setGraph();
    }

    public void updateTableGraph() {
        this.data.add(new Data(this.xgr, this.ygr, this.valueR, UserBean.checkResult(this.xgr, this.ygr, this.valueR)));
    }

    public void clearTable() {
        this.data.clear();
        this.setGraph();
    }

    public List<Data> getData() {
        return this.data;
    }

    public void setValueX(double value) {
        this.valueX = value;
    }

    public double getValueX() {
        return this.valueX;
    }

    public void setValueY(double value) {
        this.valueY = value;
    }

    public double getValueY() {
        return this.valueY;
    }

    public void setValueR(double valueR) {
        this.valueR = valueR;
        this.setGraph();
    }

    public double getValueR() {
        return this.valueR;
    }


    public static String checkResult(double x, double y, double r) {
        if (-r<=x && x<=0 && -r<=y && y<=0 && x*x+y*y<=r*r ||
             y<=0 && x>=0 && y>=-r && x<=r  ||
             0>=x && y>=0 && y<=x/2+r/2) {
            return "IN";
        }
        return "OUT";
    }
}