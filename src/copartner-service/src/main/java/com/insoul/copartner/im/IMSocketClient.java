package com.insoul.copartner.im;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.io.Charsets;

import com.insoul.copartner.constant.IMVersion;
import com.insoul.copartner.util.ByteUtil;

public class IMSocketClient {

    public static String sendMsg(String msg) {
        // 定义头信息的字节数
        int HEAD_BYTE_LENGTH = 20;

        // 在头信息中, 前4位表示该报文的长度, 剩余的16位头信息备用
        int PACKET_BYTE_LENGTH = 4;

        // 版本号所在的头位置
        int VERSION_POSITION = 5;

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        try {
            socket = new Socket("120.24.228.100", 9099);
            socket.setKeepAlive(false);
            socket.setSoTimeout(30000);
            in = socket.getInputStream();
            out = socket.getOutputStream();

            byte[] packetBytes = msg.getBytes(Charsets.UTF_8);
            int packetLength = packetBytes.length;
            byte[] packetLengthBytes = ByteUtil.parseBytes(packetLength, PACKET_BYTE_LENGTH);
            int parseLen = ByteUtil.toInt(packetLengthBytes);
            if (parseLen != packetLength) {
                return null;
            }
            byte[] headBytes = new byte[HEAD_BYTE_LENGTH];
            System.arraycopy(packetLengthBytes, 0, headBytes, 0, packetLengthBytes.length);
            headBytes[VERSION_POSITION - 1] = IMVersion.VERSION_1_0_0.getId();

            out.write(headBytes);
            out.write(packetBytes);
            out.flush();

            byte[] headBytes1 = new byte[HEAD_BYTE_LENGTH];
            in.mark(HEAD_BYTE_LENGTH);
            if (in.read(headBytes1) != HEAD_BYTE_LENGTH) {
                in.reset();
                return null;
            }
            int length = ByteUtil.toInt(ByteUtil.getBytes(headBytes1, 0, PACKET_BYTE_LENGTH));
            byte[] packetBytes1 = new byte[length];
            in.read(packetBytes1);

            return new String(packetBytes1, Charsets.UTF_8);
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
