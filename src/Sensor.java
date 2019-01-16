public class Sensor {
    private int id;
    private String date;
    private String temp;
    private String humi;
    private String light;

    public Sensor(String date, String temp, String humi, String light) {
        this.date = date;
        this.temp = temp;
        this.humi = humi;
        this.light = light;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumi() {
        return humi;
    }

    public void setHumi(String humi) {
        this.humi = humi;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    @Override
    public String toString() {
        return "Sensor [id=" + id + ", temp=" + temp + ", humi=" + humi + "light" + light
                + "]";
    }
}
