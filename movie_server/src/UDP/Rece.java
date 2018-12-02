package UDP;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Rece implements Runnable{
	private DatagramSocket ds;
	public Rece(DatagramSocket ds) {
		this.ds = ds;
	}
	@Override
	public void run() {
		while(true) {
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			try {
				ds.receive(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String text = new String(dp.getData(),0,dp.getLength());
			
			System.out.println(text);
		}
	}

}
