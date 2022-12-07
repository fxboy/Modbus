package icu.weboys.modbus.core.requests;

import icu.weboys.modbus.core.payloads.ModbusPayLoad;

public interface ModbusRequest{
    ModbusPayLoad getPayLoad();

    int getFlag();

    int setFlag(int i);

    short getPool();

    short getUid();

}
