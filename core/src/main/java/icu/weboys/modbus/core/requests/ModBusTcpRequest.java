package icu.weboys.modbus.core.requests;

import icu.weboys.modbus.core.payloads.ModbusPayLoad;

public class ModBusTcpRequest extends BaseModbusRequest {
    public ModBusTcpRequest(ModbusPayLoad payLoad) {
        super(payLoad);
    }

    public ModBusTcpRequest(Short uid,ModbusPayLoad payLoad) {
        super(uid,payLoad);
    }

}
