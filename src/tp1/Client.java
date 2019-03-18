package tp1;

public class Client {
	private int noClient;
	private String nomClient;
	private String noTel;
	public Client(int noClient, String nomClient, String noTel) {
		super();
		this.noClient = noClient;
		this.nomClient = nomClient;
		this.noTel = noTel;
	}
	public int getNoClient() {
		return noClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public String getNoTel() {
		return noTel;
	}
	@Override
	public String toString() {
		return "Client [noClient=" + noClient + ", nomClient=" + nomClient
				+ ", noTel=" + noTel + "]";
	}
	
}
