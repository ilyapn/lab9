package PIP;

public class Data {
    public double valueX = 0.0;
    public double valueY = 0.0;
    public double valueR = 1.0;
    public String result;

    public Data(double x, double y, double r, String res) {
        this.valueX = x;
        this.valueY = y;
        this.valueR = r;
        this.result = res;
    }

    public double getValueX() {
        return this.valueX;
    }

    public double getValueY() {
        return this.valueY;
    }

    public double getValueR() {
        return this.valueR;
    }

    public String getResult() {
        return this.result;
    }
}