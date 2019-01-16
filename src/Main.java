import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SerialPortUtils serialPort = SerialPortUtils.getIsSerialPort();
        serialPort.openSerialPort();

        class StorageData implements Runnable {

            private double humi;
            private double temp;
            private double light;

            public StorageData(String temp, String humi, String light) {
                String[] humis = humi.split(" ");
                int humiCal = Integer.parseInt(humis[1] + humis[0], 16);
                this.humi = humiCal / 100.0;

                String[] temps = temp.split(" ");
                int tempCal = Integer.parseInt(temps[1] + temps[0], 16);
                this.temp = tempCal / 100.0;

                String[] lights = light.split(" ");
                int lightCal = Integer.parseInt(lights[1] + lights[0], 16);
                this.light = lightCal / 100.0;
            }

            @Override
            public void run() {
                System.out.println(serialPort.sensor.get("TEMP"));
                StorageData sd = new StorageData(
                        serialPort.dataAll.get(serialPort.sensor.get("TEMP")),
                        serialPort.dataAll.get(serialPort.sensor.get("HUMI")),
                        serialPort.dataAll.get(serialPort.sensor.get("LIGHT")));
                String tempd = new String(String.valueOf(sd.temp));
                String humid = new String(String.valueOf(sd.humi));
                String lightd = new String(String.valueOf(sd.light));
                Date date = new Date();
                SimpleDateFormat simDate = new SimpleDateFormat(
                        "yy/MM/dd HH:mm:ss");
                String dated = simDate.format(date);
                System.out.println("湿度："+humid+"。温度："+tempd+"。时间："+dated+"。光照度"+lightd);
                Sensor sensor = new Sensor(dated,tempd,humid,lightd);
                //DBUtils.insert(sensor);
            }
        }

        //控制继电器工作
        Thread.sleep(10000);
        String EXECUTEB = serialPort.sensor.get("EXECUTEB").replace(" ","");//去掉空格
        serialPort.sendToPort(EXECUTEB,"00");

        while (true) {
            Thread.sleep(1000);
            String str = serialPort.sensor.get("TEMP"); // debug TODO
            Map tempMap = serialPort.dataAll;
            String test = serialPort.dataAll.get(serialPort.sensor.get("TEMP"));
            new Thread(new StorageData(
                    serialPort.dataAll.get(serialPort.sensor.get("TEMP")),
                    serialPort.dataAll.get(serialPort.sensor.get("HUMI")),
                    serialPort.dataAll.get(serialPort.sensor.get("LIGHT"))
                    )).start();
        }

    }
}
