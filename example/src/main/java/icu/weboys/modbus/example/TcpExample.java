package icu.weboys.modbus.example;

import icu.weboys.modbus.core.analyse.ByteAnalyse;
import icu.weboys.modbus.core.payloads.ReadCoilStatusPayLoad;
import icu.weboys.modbus.core.payloads.ReadHoldingRegisterPayLoad;
import icu.weboys.modbus.core.payloads.ReadInputRegisterPayLoad;
import icu.weboys.modbus.core.payloads.ReadInputStatusPayLoad;
import icu.weboys.modbus.core.requests.ModBusTcpRequest;
import icu.weboys.modbus.poll.tcp.ModBusTcpPoll;
import icu.weboys.modbus.poll.tcp.ModbusTcpConfig;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class TcpExample {
    public static void main(String[] args) {
        run();
    }

    public static void run(){
        ModbusTcpConfig config = new ModbusTcpConfig.Builder("127.0.0.1",505).build();
        ModBusTcpPoll poll = new ModBusTcpPoll(config,(resp)->{
            if(resp.code() == 1 || resp.code() == 2){
                int[] result = ByteAnalyse.coil_analyse((byte[]) resp.data());
                System.out.println(Arrays.toString(result));
            }else{
                System.out.println(resp);
            }
        }, (ch)->{
            System.out.println("已断开连接");
        });

        poll.connect();

        new Thread(()->{
            while (true){
                try {
                  //  poll.send(new ModBusTcpRequest((short) 1,new ReadCoilStatusPayLoad(0,16)));
                    poll.send(new ModBusTcpRequest((short) 1,new ReadInputStatusPayLoad(0,10)));
//                    poll.send(new ModBusTcpRequest((short) 1,new ReadHoldingRegisterPayLoad(0,10)));
//                    poll.send(new ModBusTcpRequest((short) 1,new ReadInputRegisterPayLoad(0,10)));
                    Thread.sleep(100);

                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
}
