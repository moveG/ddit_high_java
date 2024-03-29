package Study;

public class S0830_20 {
	
	public static void main(String[] args) {
		DataBox dataBox = new DataBox();
		
		ProducerThread pth = new ProducerThread(dataBox);
		ConsumerThread cth = new ConsumerThread(dataBox);
		
		pth.start();
		cth.start();
	}
}

/*
 * 데이터를 공통으로 사용하는 클래스
 */
class DataBox {
	private String data;
	
	//data가 null이 아닐 때 data값을 반환하는 메서드
	public synchronized String getData() {
		if(data == null) {
			try {
				wait();	//data가 null이면 할 일이 없으므로 wait상태로 대기(대기실로 감)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String returnData = data;
		System.out.println("읽어온 데이터 : " + returnData);	//5 11 17
		data = null;
		System.out.println(Thread.currentThread().getName() + "가 notify()메서드 호출");	//6 12 18
		notify();
		
		return returnData;
	}
	
	//data가 null일 경우에만 자료를 세팅하는 메서드
	public synchronized void setData(String data) {
		if(this.data != null) {
			try {
				wait();	//data가 존재하면(null이 아니면) 할 일이 없으므로 wait상태로 대기(대기실로 감)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.data = data;
		System.out.println("세팅한 데이터 : " + this.data);	//2 8 14 20
		System.out.println(Thread.currentThread().getName() + "가 notify()메서드 호출");	//3 9 15
		notify();
	}
}

/*
 * 데이터 세팅만 수행하는 스레드
 */
class ProducerThread extends Thread {
	private DataBox dataBox;
	
	public ProducerThread(DataBox dataBox) {
		super("ProducerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			String data = "Data - " + i;
			System.out.println("dataBox.setData(" + data + ")");	//1 4 10 16
			dataBox.setData(data);
		}
	}
}

/*
 * 데이터를 읽어만 오는 스레드
 */
class ConsumerThread extends Thread {
	private DataBox dataBox;
	
	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			String data = dataBox.getData();
			System.out.println("dataBox.getData() : " + data);	//7 13 19
		}
	}
}
