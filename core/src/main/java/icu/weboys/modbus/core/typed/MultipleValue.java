package icu.weboys.modbus.core.typed;

public class MultipleValue {
    private int[] values;

    private int size = 0;

    private int value = 0;

    public MultipleValue(int... bt){
        values = bt;
        this.size = values.length;
    }

    public MultipleValue(Boolean... value){
        this.values = new int[value.length];
        for (int i = 0; i < value.length; i++) {
            this.values[i] = value[i]?1:0;
        }
        ck();
    }

    public MultipleValue(ModbusSwitch... value){
        this.values = new int[value.length];
        for (int i = 0; i < value.length; i++) {
            this.values[i] = value[i] == ModbusSwitch.ON?1:0;
        }
        ck();
    }

    public int value(){
        return this.value;
    }

    public int length(){
        return this.size;
    }

    public int[] values(){
        return this.values;
    }

    private void ck(){
        StringBuffer sb = new StringBuffer();
        for (int i : this.values) {
            sb.append(i);
        }
        this.size = this.values.length;
        this.value = Integer.parseInt(sb.toString(),2);
    }

}
