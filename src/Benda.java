import java.io.BufferedWriter;

/**
 * Kelas ini merepresentasikan objek dari laci atau kunci.
 * 
 * @author Galuh Buana Putra Kautsar - 1406543580
 * @version 2015.05.24
 *
 */
class Benda implements Comparable<Benda>
{
	private String nama;
	private int berat;
	private int counter;
	private boolean isKunci;
	private boolean ketemu;
	private boolean sudahDicetak;
	private boolean terhapus;
	private BufferedWriter out;

	/**
	 * Constructor dari kelas Benda saat membuat objek laci
	 * @param nama nama laci
	 * @param out buffered writer untuk mencetak string jika dibutuhkan
	 */
	public Benda(String nama, BufferedWriter out)
	{
		this.nama = nama;
		this.berat = 1;
		this.isKunci = false;
		this.ketemu = false;
		this.sudahDicetak = false;
		this.terhapus = false;
		this.out = out;
	}
	
	/**
	 * Constructor dari kelas Benda saat membuat objek kunci
	 * @param nama nama kunci
	 * @param berat berat kunci
	 * @param counter counter untuk membuat setiap kunci tetap unik
	 * @param out buffered writer untuk mencetak string jika dibutuhkan
	 */
	public Benda(String nama, int berat, int counter, BufferedWriter out)
	{
		this.nama = nama;
		this.berat = berat;
		this.counter = counter;
		this.isKunci = true;
		this.ketemu = false;
		this.sudahDicetak = false;
		this.terhapus = false;
		this.out = out;
	}	

	/**
	 * Method untuk mendapatkan nama dari objek laci atau kunci
	 * @return nama dari objek laci atau kunci
	 */
	public String getNama()
	{
		return nama;
	}
	
	/**
	 * Method untuk mendapatkan berat dari objek laci atau kunci
	 * @return berat dari objek laci atau kunci
	 */
	public int getBerat()
	{
		return berat;
	}
	
	/**
	 * Method untuk mendapatkan bilangan counter tertentu dari objek kunci
	 * @return bilangan counter tertentu dari objek kunci
	 */
	public int getCounter()
	{
		return counter;
	}

	/**
	 * Method untuk mendapatkan informasi apakah suatu benda merupakan laci atau kunci
	 * @return informasi apakah suatu benda merupakan laci atau kunci
	 */
	public boolean isKunci()
	{
		return isKunci;
	}
	
	/**
	 * Method untuk mengeset apakah suatu benda sudah ditemukan atau belum
	 * @param isKetemu informasi apakah suatu benda sudah ditemukan atau belum
	 */
	public void setKetemu(boolean isKetemu)
	{
		this.ketemu = isKetemu;
	}
	
	/**
	 * Method untuk mendapatkan informasi apakah suatu benda sudah ditemukan atau belum
	 * @return informasi apakah suatu benda sudah ditemukan atau belum
	 */
	public boolean isKetemu()
	{
		return ketemu;
	}
	
	/**
	 * Method untuk mengeset apakah suatu benda sudah dicetak atau belum
	 * @param isSudahDicetak informasi apakah suatu benda sudah dicetak atau belum
	 */
	public void setSudahDicetak(boolean isSudahDicetak)
	{
		this.sudahDicetak = isSudahDicetak;
	}
	
	/**
	 * Method untuk mendapatkan informasi apakah suatu benda sudah dicetak atau belum
	 * @return informasi apakah suatu benda sudah dicetak atau belum
	 */
	public boolean isSudahDicetak()
	{
		return sudahDicetak;
	}

	/**
	 * Method untuk mengeset apakah suatu benda sudah dihapus atau belum
	 * @param isTerhapus informasi apakah suatu benda sudah dihapus atau belum
	 */
	public void setTerhapus(boolean isTerhapus)
	{
		this.terhapus = isTerhapus;
	}
	
	/**
	 * Method untuk mendapatkan informasi apakah suatu benda sudah dihapus atau belum
	 * @return informasi apakah suatu benda sudah dihapus atau belum
	 */
	public boolean isTerhapus()
	{
		return terhapus;
	}
	
	/**
	 * Method untuk mendapatkan objek buffered writer dari suatu laci atau kunci
	 * @return
	 */
	public BufferedWriter getOut()
	{
		return out;
	}

	/**
	 * Method untuk menentukan indikator dari dua buah benda dapat dikatakan sama
	 */
	public boolean equals(Object o)
	{
		return this.nama.equals(((Benda) o).getNama());
	}
	
	/**
	 * Method untuk mengurutkan kumpulan laci dan kunci berdasarkan
	 * nama (untuk laci) dan nama, berat, atau counter (untuk kunci)
	 */
	public int compareTo(Benda benda)
	{
		if (this.nama.equals(benda.getNama())) {
			if (this.berat == benda.getBerat()) {
				return this.counter - benda.getCounter();
			}
			return this.berat - benda.getBerat();
		}
		return this.nama.compareTo(benda.getNama());
	}
}