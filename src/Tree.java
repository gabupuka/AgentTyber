import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Kelas ini merepresentasikan suatu tree.
 * 
 * @author Galuh Buana Putra Kautsar - 1406543580
 * @version 2015.05.24
 *
 */
class Tree<E extends Comparable<E>>
{
	private TreeNode<E> root;
	
	/**
	 * Constructor dari kelas Tree
	 * @param element objek yang dimasukkan ke dalam node teratas di dalam tree
	 */
	public Tree(E element)
	{
		this.root = new TreeNode<>(element);
	}
	
	/**
	 * Driver method untuk menambahkan laci ke dalam laci tertentu
	 * @param child laci yang ingin ditambahkan
	 * @param parent lokasi tujuan tempat ditambahkannya laci
	 */
	public void add(E child, E parent)
	{
		TreeNode<E> childNode = new TreeNode<>(child);
		TreeNode<E> parentNode = this.root.findNode(root, parent);
		
		add(childNode, parentNode);
	}
	
	/**
	 * Method untuk menambahkan atau memindahkan laci ke dalam laci tertentu
	 * @param childNode laci yang ingin ditambahkan atau dipindahkan
	 * @param parentNode lokasi tujuan tempat ditambahkan atau dipindahkannya laci
	 */
	public void add(TreeNode<E> childNode, TreeNode<E> parentNode)
	{
		// Memeriksa apakah lokasi tujuan tempat penambahan atau pemindahannya tidak null
		if (parentNode != null) {
			// Kasus saat di dalam lokasi tujuan sudah berisi kunci
			if (! parentNode.children.isEmpty() &&
				((Benda) parentNode.children.firstEntry().getValue().element).isKunci()) {
				childNode.children = parentNode.children;
				
				parentNode.children = new TreeMap<E, TreeNode<E>>();
				parentNode.children.put(childNode.element, childNode);
				childNode.parent = parentNode;
				
				for (TreeNode<E> anak : childNode.children.values()) {
					anak.parent = childNode;
				}
			}
			// Kasus saat di dalam lokasi tujuan berisi laci atau belum berisi
			else {
				parentNode.children.put(childNode.element, childNode);
				childNode.parent = parentNode;
			}
		}
	}
	
	/**
	 * Method untuk memasukkan kunci ke dalam laci tertentu
	 * @param child kunci yang ingin dimasukkan
	 * @param parent lokasi tujuan tempat dimasukkannya kunci
	 * @throws IOException
	 */
	public void put(E child, E parent) throws IOException
	{
		TreeNode<E> childNode = new TreeNode<>(child);
		TreeNode<E> parentNode = this.root.findNode(root, parent);
		
		// Memeriksa apakah lokasi tujuan tempat pemasukkannya tidak null
		if (parentNode != null) {
			// Kasus saat di dalam lokasi tujuan sudah berisi laci
			if (! parentNode.children.isEmpty() &&
				! ((Benda) parentNode.children.firstEntry().getValue().element).isKunci()) {
				parentNode = this.root.findParentKey(parentNode);
			}
			
			Benda elemenParent = (Benda) parentNode.element;
			Benda elemenChild = (Benda) childNode.element;
			
			parentNode.children.put(child, childNode);
			childNode.parent = parentNode;
			
			elemenChild.getOut().write(elemenChild.getNama() + " masuk di " + elemenParent.getNama() + "\n");
			elemenChild.getOut().flush();
		}
	}
	
	/**
	 * Method untuk menghapus laci atau kunci tertentu
	 * @param yangDihapus laci atau kunci yang ingin dihapus
	 * @throws IOException
	 */
	public void hapus(E yangDihapus) throws IOException
	{
		ArrayList<E> daftarHapus = new ArrayList<>();
		
		root.hapus(root, yangDihapus, daftarHapus);
	}
	
	/**
	 * Method untuk mencetak semua isi dari laci tertentu
	 * @param yangDicetak laci yang ingin dicetak
	 * @throws IOException
	 */
	public void cetak(E yangDicetak) throws IOException
	{
		TreeNode<E> node = root.findNode(root, yangDicetak);
		String hasil = "";
		Benda benda = (Benda) yangDicetak;
		
		root.cetak(node, yangDicetak, hasil);
		benda.getOut().flush();
	}
	
	/**
	 * Method untuk mencari lokasi dari kunci tertentu
	 * @param yangDicari kunci yang ingin dicari
	 * @throws IOException
	 */
	public void find(E yangDicari) throws IOException
	{
		String hasil = "";
		Benda benda = (Benda) yangDicari;
		
		root.tandaiFind(root, yangDicari);
		root.cetakFind(root, yangDicari, hasil);
		benda.getOut().flush();
	}
	
	/**
	 * Method untuk memindahkan suatu laci ke dalam laci tertentu
	 * @param yangDipindah laci yang ingin dipindahkan
	 * @param tujuanPindah lokasi tujuan tempat dipindahkannya laci
	 * @throws IOException
	 */
	public void move(E yangDipindah, E tujuanPindah) throws IOException
	{
		TreeNode<E> nodeYgDipindah = root.findNode(root, yangDipindah);
		TreeNode<E> nodeTujuan = root.findNode(root, tujuanPindah);
		
		hapus(yangDipindah);
		add(nodeYgDipindah, nodeTujuan);
	}
}