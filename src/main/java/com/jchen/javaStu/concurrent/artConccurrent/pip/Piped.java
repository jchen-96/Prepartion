package com.jchen.javaStu.concurrent.artConccurrent.pip;

import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * Created by jchen on 17-7-28.
 */
public class Piped {
    public static void main(String[] args) throws Exception {
        PipedReader in = new PipedReader();
        PipedWriter out = new PipedWriter();
//    需要将输出流和输入流进行连接，否则使用的时候会抛出异常
        out.connect(in);

        Thread printThread = new Thread(new Print(in), "printThread");
        printThread.start();
        int receive=0;

        try {
            while ((receive=System.in.read())!=-1){
                out.write(receive);
            }
        }catch (Exception e){

        }finally {
            out.close();
        }



    }

    static class Print implements Runnable {
        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        public void run() {
            int receive = 0;
            try {
                while ((receive=in.read())!=1){
                    System.out.print((char)receive);
                }
            } catch (Exception e) {

            }
        }


    }

}
