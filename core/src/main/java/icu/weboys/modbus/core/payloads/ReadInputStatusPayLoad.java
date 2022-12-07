package icu.weboys.modbus.core.payloads;

import icu.weboys.modbus.core.typed.ModbusFCode;

public class ReadInputStatusPayLoad extends BaseModbusPayLoad{
    public ReadInputStatusPayLoad(int address, int amount) {
        super( ModbusFCode.READ_INPUT_STATUS, address, amount);
    }
}
