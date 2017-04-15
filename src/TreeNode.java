import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Kelas ini merepresentasikan setiap node dari suatu tree.
 * 
 * @author Galuh Buana Putra Kautsar - 1406543580
 * @version 2015.05.24
 *
 */
class TreeNode<E extends Comparable<E>> implements Comparable<TreeNode<E>>
{
	E element;
	TreeMap<E, TreeNode<E>> children;
	TreeNode<E> parent;
	
	/**
	 * Constructor dari kelas TreeNode
	 * @param element objek yang dimasukkan ke dalam node
	 */
	public TreeNode(E element)
	{
		this.element = element;
		this.children = new TreeMap<>();
		this.parent = null;
	}
	
	/**
	 * Method untuk mencari node tertentu secara rekursif
	 * @param t node saat ini (diawali dari node paling atas yaitu root)
	 * @param x elemen dari node yang dimaksud
	 * @return node yang dicari
	 */
	public TreeNode<E> findNode(TreeNode<E> t, E x)
	{	
		// Memeriksa apakah node saat ini null atau merupakan node yang dicari
		if (t == null || ((Benda) t.element).equals(x)) {
			return t;
		}
		
		TreeNode<E> nodeSkrg = null;
		
		// Melanjutkan rekursif ke anak-anak dari node saat ini jika punya anak
		if (! t.children.isEmpty()) {
			for (TreeNode<E> node : t.children.values()) {
				nodeSkrg = findNode(node, x);
				
				if (nodeSkrg != null) {
					break;
				}
			}
		}
		
		return nodeSkrg;
	}
	
	/**
	 * Method untuk mencari node yang tepat untuk memasukkan kunci secara rekursif
	 * @param t node saat ini (diawali dari node yang diminta di masukkan)
	 * @return node yang sesuai
	 */
	public TreeNode<E> findParentKey(TreeNode<E> t)
	{
		// Memeriksa apakah node saat ini tidak punya anak atau mempunyai anak berupa kunci
		if (t.children.isEmpty() ||
			((Benda) t.children.firstEntry().getValue().element).isKunci()) {
			return t;
		}
		
		TreeNode<E> nodeSkrg = null;
		
		// Melanjutkan rekursif ke anak-anak dari node saat ini jika punya anak
		for (TreeNode<E> node : t.children.values()) {
			nodeSkrg = findParentKey(node);
			
			if (nodeSkrg != null) {
				break;
			}
		}
		
		return nodeSkrg;
	}
	
	/**
	 * Method untuk menghitung berat dari laci secara rekursif
	 * @return berat dari laci
	 */
	public int berat()
	{	
		// Memeriksa apakah node saat ini tidak punya anak
		if (this.children.isEmpty()) {
			return ((Benda) this.element).getBerat();
		}
		
		int berat = ((Benda) this.element).getBerat();
		
		// Melanjutkan rekursif ke anak-anak dari node saat ini jika punya anak
		for (TreeNode<E> node : this.children.values()) {
			berat += node.berat();
		}
		
		return berat;
	}
	
	/**
	 * Method untuk mencetak isi dari suatu laci
	 * @param t node saat ini (diawali dari node laci yang ingin dicetak)
	 * @param yangDicetak elemen dari node yang ingin dicetak
	 * @param hasil string dari nama dan berat setiap laci atau kunci yang dicetak
	 * @throws IOException
	 */
	public void cetak(TreeNode<E> t, E yangDicetak, String hasil) throws IOException
	{	
		int berat = 0;
		Benda bendaTmp = ((Benda) t.element);
		Benda benda = (Benda) yangDicetak;
		
		// Memeriksa apakah benda saat ini merupakan kunci atau laci
		if (bendaTmp.isKunci()) {
			berat = bendaTmp.getBerat();
		}
		else {
			berat = t.berat();
		}
		
		benda.getOut().write(hasil + "> " + bendaTmp.getNama() + " " + berat + "\n");
		
		// Melanjutkan rekursif ke anak-anak dari node saat ini jika punya anak
		if (! t.children.isEmpty()) {
			for (TreeNode<E> tmp : t.children.values()) {
				cetak(tmp, yangDicetak, hasil + "  ");
			}
		}
	}
	
	/**
	 * Method untuk menghapus suatu laci atau kunci
	 * @param t node saat ini (diawali dari node paling atas yaitu root)
	 * @param yangDihapus elemen dari node yang ingin dihapus
	 * @param daftarHapus daftar laci atau kunci yang ingin dihapus
	 * @return informasi apakah suatu laci atau kunci berhasil dihapus
	 * @throws IOException
	 */
	public boolean hapus(TreeNode<E> t, E yangDihapus, ArrayList<E> daftarHapus) throws IOException
	{
		// Memeriksa apakah node saat ini null
		if (t == null) {
			return false;
		}
		
		// Memeriksa apakah node saat ini merupakan node yang ingin dihapus
		if (t.element.equals(yangDihapus)) {
			daftarHapus.add(t.element);
			return true;
		}
		
		// Melanjutkan rekursif ke anak-anak dari node saat ini jika punya anak
		if (! t.children.isEmpty()) {
			for (TreeNode<E> node : t.children.values()) {
				boolean terhapus = hapus(node, yangDihapus, daftarHapus);
				
				if (! ((Benda) node.element).isKunci() && terhapus) {
					break;
				}
			}
			
			// Mulai menghapus node dengan acuan dari daftar yang ingin hapus
			for (int i = 0; i < daftarHapus.size(); i++) {
				E bendaArr = daftarHapus.get(i);
				Benda benda = (Benda) yangDihapus;
				Benda bendaAsli = (Benda) daftarHapus.get(i);
				
				t.children.remove(bendaArr);
				
				// Memeriksa apakah benda yang dihapus merupakan kunci atau laci
				if (bendaAsli.isKunci()) {
					if (! benda.isTerhapus()) {
						benda.getOut().write("kunci " + benda.getNama() + " dihapus" + "\n");
						benda.setTerhapus(true);
					}
				}
				else {
					benda.getOut().write("laci " + benda.getNama() + " dihapus" + "\n");
					benda.setTerhapus(true);
				}
				
				daftarHapus.remove(i);
				i--;
			}
		}
		
		return false;
	}
	
	/**
	 * Method untuk menandai jalur atau direktori menuju tempat suatu kunci yang dicari
	 * @param t node saat ini (diawali dari node paling atas yaitu root)
	 * @param yangDicari elemen dari node yang ingin dicari
	 */
	public void tandaiFind(TreeNode<E> t, E yangDicari)
	{
		// Memeriksa apakah node saat ini null
		if (t == null) {
			return;
		}
		
		Benda elemenSkrg = (Benda) t.element;
		
		// Memeriksa apakah node saat ini merupakan node yang dicari
		if (elemenSkrg.equals(yangDicari)) {
			elemenSkrg.setKetemu(true);
			((Benda) yangDicari).setKetemu(true);
			return;
		}
		
		// Melanjutkan rekursif ke anak-anak dari node saat ini jika punya anak
		if (! t.children.isEmpty()) {
			boolean kunciSudahKetemu = false;
			
			for (TreeNode<E> node : t.children.values()) {
				Benda nodeSkrg = (Benda) node.element;
				
				if (! ((Benda) yangDicari).isKetemu()) {
					tandaiFind(node, yangDicari);
					
					if (nodeSkrg.isKetemu() && nodeSkrg.isKunci()) {
						if (! kunciSudahKetemu) {
							TreeNode<E> tmp = node.parent;
							
							/* Menandai orang tua dari node saat ini
							sampai root untuk mengetahui jalur menuju node saat ini */
							while (tmp != null) {
								((Benda) tmp.element).setKetemu(true);
								tmp = tmp.parent;
							}
							
							kunciSudahKetemu = true;
						}
					}
				}				
			}
			
			((Benda) yangDicari).setKetemu(false);
		}
	}
	
	/**
	 * Method untuk mencetak jalur atau direktori menuju tempat suatu kunci yang sudah ditandai
	 * @param t node saat ini (diawali dari node paling atas yaitu root)
	 * @param yangDicari elemen dari node yang ingin dicari
	 * @param hasil string dari nama setiap laci atau kunci yang dicetak
	 * @throws IOException
	 */
	public void cetakFind(TreeNode<E> t, E yangDicari, String hasil) throws IOException
	{
		// Memeriksa apakah node saat ini null
		if (t == null) {
			return;
		}
		
		Benda elemenSkrg = (Benda) t.element;
		Benda benda = (Benda) yangDicari;
		
		// Memeriksa apakah node saat ini sudah ditandai
		if (elemenSkrg.isKetemu()) {
			benda.getOut().write(hasil + "> " + elemenSkrg.getNama() + "\n");
			elemenSkrg.setKetemu(false);
			
			if (elemenSkrg.isKunci()) {
				benda.setSudahDicetak(true);
			}
			
			// Melanjutkan rekursif ke anak-anak dari node saat ini jika punya anak
			if (! t.children.isEmpty()) {
				for (TreeNode<E> node : t.children.values()) {
					if (! ((Benda) node.element).isKunci()) {
						benda.setSudahDicetak(false);
					}
					
					if (! benda.isSudahDicetak()) {
						cetakFind(node, yangDicari, hasil + "  ");
					}
				}
			}
		}
	}

	/**
	 * Method untuk mengurutkan node berdasarkan hasil urutan dari elemen node
	 */
	public int compareTo(TreeNode<E> child)
	{
		return this.element.compareTo(child.element);
	}
}