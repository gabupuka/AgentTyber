import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * Kelas ini merupakan kelas utama dari program Agent Tyber.
 * 
 * @author Galuh Buana Putra Kautsar - 1406543580
 * @version 2015.05.24
 *
 */
public class Main
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// Membuat inisialisasi awal untuk laci utama (terbesar)
		Benda root = new Benda("laci1", out);
		Tree<Benda> kumpulanBenda = new Tree<>(root);
		
		// Counter untuk membuat 'key' yang digunakan di TreeMap tetap unik
		int counter = 0;
		
		// Membaca masukkan
		String masukan = null;
		while ((masukan = in.readLine()) != null) {
			StringTokenizer token = new StringTokenizer(masukan);
			String perintah = token.nextToken();
			
			// Menambahkan laci ke dalam laci tertentu
			if (perintah.equals("add")) {
				Benda child = new Benda(token.nextToken(), out);
				Benda parent = new Benda(token.nextToken(), out);
				
				kumpulanBenda.add(child, parent);
			}
			// Memasukkan kunci ke dalam laci tertentu
			else if (perintah.equals("put")) {
				Benda child = new Benda(token.nextToken(),Integer.parseInt(token.nextToken()),
										counter, out);
				Benda parent = new Benda(token.nextToken(), out);
				
				kumpulanBenda.put(child, parent);
				counter++;
			}
			// Menghapus laci atau kunci tertentu
			else if (perintah.equals("throw")) {
				Benda yangDihapus = new Benda(token.nextToken(), out);
				
				kumpulanBenda.hapus(yangDihapus);
				yangDihapus.getOut().flush();
			}
			// Mencetak semua isi dari laci tertentu
			else if (perintah.equals("cetak")) {
				Benda yangDicetak = new Benda(token.nextToken(), out);
				
				kumpulanBenda.cetak(yangDicetak);
			}
			// Mencari lokasi dari kunci tertentu
			else if (perintah.equals("find")) {
				Benda yangDicari = new Benda(token.nextToken(), out);
				
				kumpulanBenda.find(yangDicari);
			}
			// Memindahkan suatu laci ke dalam laci tertentu
			else if (perintah.equals("move")) {
				Benda yangDipindah = new Benda(token.nextToken(), out);
				Benda tujuanPindah = new Benda(token.nextToken(), out);
				
				kumpulanBenda.move(yangDipindah, tujuanPindah);
			}
		}
		
		out.close();
	}
}