package icu.weboys.modbus.poll.tcp.codec;

import icu.weboys.modbus.core.payloads.ModbusPayLoad;
import icu.weboys.modbus.core.requests.ModbusRequest;
import icu.weboys.modbus.core.responses.ReadCoilStatusResponse;
import icu.weboys.modbus.core.responses.ReadHoldingRegisterResponse;
import icu.weboys.modbus.core.responses.ReadInputRegisterResponse;
import icu.weboys.modbus.core.responses.ReadInputStatusResponse;
import icu.weboys.modbus.core.typed.ModbusFCode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import java.util.List;

public class MessageCodec extends ByteToMessageCodec<ModbusRequest> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ModbusRequest request, ByteBuf buf) throws Exception {
        int sIndex = buf.writerIndex();
        buf.writeZero(7);
        int rIndex = buf.writerIndex();
        payload(request,buf);
        header(sIndex,rIndex, request.getFlag(),request.getPool(),request.getUid(),buf);
    }




    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int bytesLen = byteBuf.readableBytes();
        if (bytesLen < 11){
            byteBuf.skipBytes(bytesLen);
            return;
        }
        byteBuf.markReaderIndex();
        int flag    = byteBuf.readUnsignedShort();
        int pool    = byteBuf.readUnsignedShort();
        int dtLen   = byteBuf.readUnsignedShort();
        int uid     = byteBuf.readUnsignedByte();
        if(pool != 0){
            byteBuf.skipBytes(bytesLen);
            return;
        }
        int code    = byteBuf.readByte();
        int btLen   = byteBuf.readUnsignedByte();
        byte[] data = new byte[btLen];
        int index = byteBuf.readerIndex();
        byteBuf.getBytes(index,data,0,btLen);
        if(code == ModbusFCode.READ_COIL_STATUS){
            list.add(new ReadCoilStatusResponse(flag,uid,code,btLen,data));
        }
        if(code == ModbusFCode.READ_INPUT_STATUS){
            list.add(new ReadInputStatusResponse(flag,uid,code,btLen,data));
        }
        if(code == ModbusFCode.READ_HOLDING_REGISTER){
            list.add(new ReadHoldingRegisterResponse(flag,uid,code,btLen,data));
        }
        if(code == ModbusFCode.READ_INPUT_REGISTER){
            list.add(new ReadInputRegisterResponse(flag,uid,code,btLen,data));
        }
        // 无写入返回

        byteBuf.resetReaderIndex();
        byteBuf.readBytes(bytesLen);
    }

    private void header(int sIndex ,int rIndex,int flag,short pool, short uid,ByteBuf buf){
        int layLen = buf.writerIndex() - rIndex;
        int nIndex = buf.writerIndex();
        buf.writerIndex(sIndex);
        buf.writeShort(flag);
        buf.writeShort(pool);
        buf.writeShort(layLen + 1);
        buf.writeByte(uid);
        buf.writerIndex(nIndex);
    }

    private void payload(ModbusRequest request,ByteBuf buf){
        int code = request.getPayLoad().getCode();
        buf.writeByte(code);
        switch (code){
            case ModbusFCode.READ_COIL_STATUS:
            case ModbusFCode.READ_INPUT_STATUS:
            case ModbusFCode.READ_HOLDING_REGISTER:
            case ModbusFCode.READ_INPUT_REGISTER:
                currency(request.getPayLoad(),buf);
                break;
            case ModbusFCode.WRITE_SINGLE_COIL:
                writeSingleCoil(request.getPayLoad(),buf);
                break;
            case ModbusFCode.WRITE_SINGLE_REGISTER:
                writeSingleRegister(request.getPayLoad(),buf);
                break;
            case ModbusFCode.WRITE_MULTIPLE_COIL:
                writeMultipleCoil(request.getPayLoad(),buf);
                break;
            case ModbusFCode.WRITE_MULTIPLE_REGISTER:
                writeMultipleRegister(request.getPayLoad(),buf);
                break;
            default:
                throw new RuntimeException(String.format("%s is an unsupported method",code));

        }
    }

    private void currency(ModbusPayLoad payload, ByteBuf buf){
        buf.writeShort(payload.getAddress());
        buf.writeShort(payload.getAmount());
    }

    private void writeSingleCoil(ModbusPayLoad<Integer> payload,ByteBuf buf){
        buf.writeShort(payload.getAddress());
        buf.writeShortLE(payload.val());
      //  buf.writeByte(payload.value());
    }
    private void writeSingleRegister(ModbusPayLoad<Short> payload,ByteBuf buf){
        buf.writeShort(payload.getAddress());
        buf.writeShort(payload.val());
    }

    private void writeMultipleCoil(ModbusPayLoad<Integer> payload,ByteBuf buf){
        currency(payload,buf);
        int count = (payload.getAmount() + 7) / 8;
        buf.writeByte(count);
        ByteBuf temp = Unpooled.buffer();
        temp.writeShortLE(payload.val());
        buf.writeBytes(temp,count);
    }

    private void writeMultipleRegister(ModbusPayLoad<short[]> payload,ByteBuf buf){
        currency(payload,buf);
        int count = payload.getAmount() * 2;
        buf.writeByte(count);
        ByteBuf temp = Unpooled.buffer();
        for (int i = 0; i < payload.val().length; i++) {
            temp.writeShort(payload.val()[i]);
        }
        buf.writeBytes(temp,count);
    }

}
