import java.awt.image.BufferedImage;

public class MnistObject {
    int label;
    BufferedImage temp;
    double euclideanDistance;

    public MnistObject(BufferedImage temp, int label) {
        this.temp = temp;
        this.label = label;
    }

    public MnistObject(BufferedImage temp, int label, double euclideanDistance) {
        this.temp = temp;
        this.label = label;
        this.euclideanDistance = euclideanDistance;
    }
    public MnistObject() {

    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public BufferedImage getTemp() {
        return temp;
    }

    public void setTemp(BufferedImage temp) {
        this.temp = temp;
    }

    public double getEuclideanDistance(){ 
    	return euclideanDistance;
    }

    public void setEuclideanDistance(double euclideanDistance) {
    	this.euclideanDistance = euclideanDistance;
    }
}

