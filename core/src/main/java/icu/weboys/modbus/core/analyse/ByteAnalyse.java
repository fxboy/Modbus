package icu.weboys.modbus.core.analyse;

// 解析传过来的byte
public class ByteAnalyse {
     static int[] toBinaryReverse(int val) {
         // 固定长度为8位
        int len = 8;
        int[] buf = new int[len];
        int i = 0;
        do {
            buf[i++] = val & (1 << 1 - 1);
            val >>>= 1;
        } while (val != 0 && i< len);
        return buf;
    }
    public static int[] input_analyse(byte[] bytes){
        return coil_analyse(bytes);
    }
    public static int[] coil_analyse(byte[] bytes){
        int[] temp = new int[0];
        int len;
        for (byte bt : bytes) {
            int[] bi = toBinaryReverse(Byte.toUnsignedInt(bt));
            len = temp.length;
            int[] temp1 = new int[len + bi.length];
            System.arraycopy(temp,0,temp1,0,len);
            System.arraycopy(bi,0,temp1,len,bi.length);
            temp = temp1;
        }
        return temp;
    }

    public static int[] holding_analyse(byte[] bytes){
        int[] temp = new int[bytes.length/2];
        for (int i = 0; i < bytes.length; i+=2) {
            temp[i/2] =(bytes[i]<<8 & 0xFF00) | (bytes[i+1] & 0xFF);
        }
        return temp;
    }

    public static int[] input_register_analyse(byte[] bytes){
         return holding_analyse(bytes);
    }


}
