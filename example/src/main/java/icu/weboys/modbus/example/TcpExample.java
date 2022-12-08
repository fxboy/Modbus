package icu.weboys.modbus.example;

import icu.weboys.modbus.core.analyse.ByteAnalyse;
import icu.weboys.modbus.core.payloads.*;
import icu.weboys.modbus.core.requests.ModBusTcpRequest;
import icu.weboys.modbus.core.requests.ModbusRequest;
import icu.weboys.modbus.core.value.BinaryValue;
import icu.weboys.modbus.core.value.MultipleValue;
import icu.weboys.modbus.poll.tcp.ModBusTcpPoll;
import icu.weboys.modbus.poll.tcp.ModbusTcpConfig;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class TcpExample {
    static ModBusTcpPoll poll;
    public static void main(String[] args) {
        run();
    }

    static void send(ModbusRequest request) throws ExecutionException, InterruptedException {
        poll.send(request);
    }
    public static void run(){
        ModbusTcpConfig config = new ModbusTcpConfig.Builder("127.0.0.1",505).build();
         poll = new ModBusTcpPoll(config,(resp)->{
            byte[] bts = (byte[]) resp.data();
            if(resp.code() == 1 ){
                System.out.println("1:" + Arrays.toString(ByteAnalyse.coil_analyse(bts)));
            }
            else if(resp.code() == 2){
                System.out.println("2:" +Arrays.toString(ByteAnalyse.input_analyse(bts)));
            }
            else if(resp.code() == 3){
                System.out.println("3:" +Arrays.toString(ByteAnalyse.holding_analyse(bts)));
            }
            else if( resp.code() == 4){
                System.out.println("4:" +Arrays.toString(ByteAnalyse.input_register_analyse(bts)));
            }
            else{
                System.out.println(resp);
            }
        }, (ch)->{
            System.out.println("已断开连接");
        });

        poll.connect();


//       try{
//           // 写入单个线圈 三种方式
//           //send(new ModBusTcpRequest(new WriteSingleCoilPayLoad(0,true)));
//           //send(new ModBusTcpRequest(new WriteSingleCoilPayLoad(0,1)));
//           //send(new ModBusTcpRequest(new WriteSingleCoilPayLoad(0,(short) 1)));
//           //=============================================================================
//           // 写入多个线圈
//           //send(new ModBusTcpRequest(new WriteMultipleCoilPayLoad(0,new BinaryValue(1,1,1,1,0,0,1,1,1,1,1))));
//           //send(new ModBusTcpRequest(new WriteMultipleCoilPayLoad(0,new BinaryValue((short) 1,(short)1,(short)1,(short)1,(short)0,(short)0,(short)0,(short)1))));
//           //=============================================================================
//           // 写入单个寄存器
//           //send(new ModBusTcpRequest(new WriteSingleRegisterPayLoad(1,(short) 199)));
//           //=============================================================================
//           // 写入多个寄存器
//           //send(new ModBusTcpRequest(new WriteMultipleRegisterPayLoad(0,new MultipleValue((short) 199, (short) 299, (short) 399, (short) 499, (short) 599, (short) 699, (short) 799, (short) 899))));
//
//       }catch (InterruptedException | ExecutionException exception){
//           exception.printStackTrace();
//       }


        new Thread(()->{
            while (true){
                try {
                   poll.send(new ModBusTcpRequest((short) 1,new ReadCoilStatusPayLoad(0,10)));
                   poll.send(new ModBusTcpRequest((short) 1,new ReadInputStatusPayLoad(0,10)));
                   poll.send(new ModBusTcpRequest((short) 1,new ReadHoldingRegisterPayLoad(0,10)));
                   poll.send(new ModBusTcpRequest((short) 1,new ReadInputRegisterPayLoad(0,10)));
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
