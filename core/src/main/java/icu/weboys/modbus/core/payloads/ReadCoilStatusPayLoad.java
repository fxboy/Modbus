package icu.weboys.modbus.core.payloads;

import icu.weboys.modbus.core.typed.ModbusFCode;

public class ReadCoilStatusPayLoad extends BaseModbusPayLoad{
    public ReadCoilStatusPayLoad( int address, int amount) {
        super(ModbusFCode.READ_COIL_STATUS, address, amount);
    }


}
