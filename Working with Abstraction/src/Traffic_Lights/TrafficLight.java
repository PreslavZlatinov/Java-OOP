package Traffic_Lights;

public class TrafficLight {
    private Lights lightColor;

    public TrafficLight(Lights lightColor) {
        this.lightColor = lightColor;
    }

    public Lights getLightColor() {
        return lightColor;
    }

    public void changeColor(){
        switch (lightColor){
            case RED:
                this.lightColor = Lights.GREEN;
                break;
            case GREEN:
                this.lightColor = Lights.YELLOW;
                break;
            case YELLOW:
                this.lightColor = Lights.RED;
                break;
        }
    }
}
