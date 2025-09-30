import kotlin.math.abs
 
// Lớp Phân số
class PhanSo(var tu: Int, var mau: Int) {
 
    // Khối init chạy ngay sau khi gọi constructor
    init {
        require(mau != 0) { "Mẫu số không được bằng 0" }
    }
 
    // Hàm in phân số
    fun inPhanSo() {
        println("$tu/$mau")
    }
 
    // Hàm rút gọn
    fun toiGian() {
        val ucln = gcd(abs(tu), abs(mau))
        tu /= ucln
        mau /= ucln
        if (mau < 0) { // chuẩn hóa mẫu số > 0
            tu = -tu
            mau = -mau
        }
    }
 
    // So sánh 2 phân số (-1,0,1)
    fun soSanh(ps: PhanSo): Int {
        val a = tu * ps.mau
        val b = ps.tu * mau
        return when {
            a < b -> -1
            a == b -> 0
            else -> 1
        }
    }
 
    // Cộng 2 phân số
    fun cong(ps: PhanSo): PhanSo {
        val tuMoi = tu * ps.mau + ps.tu * mau
        val mauMoi = mau * ps.mau
        val kq = PhanSo(tuMoi, mauMoi)
        kq.toiGian()
        return kq
    }
 
    // Tìm UCLN
    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }
}
 
// --------- HÀM MAIN ---------
fun main() {
    print("Nhập số lượng phân số: ")
    val n = readln().toInt()
    val arr = Array(n) {
        var tu: Int
        var mau: Int
        // Lặp cho đến khi nhập mẫu ≠ 0
        do {
            print("Nhập tử số: ")
            tu = readln().toInt()
            print("Nhập mẫu số (≠ 0): ")
            mau = readln().toInt()
        } while (mau == 0)
        PhanSo(tu, mau) // gọi constructor, init sẽ kiểm tra
    }
 
    println("\nMảng phân số vừa nhập:")
    arr.forEach { it.inPhanSo() }
 
    println("\nMảng sau khi tối giản:")
    arr.forEach {
        it.toiGian()
        it.inPhanSo()
    }
 
    var tong = PhanSo(0, 1)
    for (ps in arr) {
        tong = tong.cong(ps)
    }
    print("\nTổng các phân số = ")
    tong.inPhanSo()
 
    val maxPS = arr.maxWithOrNull { a, b -> a.soSanh(b) }!!
    print("\nPhân số lớn nhất là: ")
    maxPS.inPhanSo()
 
    println("\nMảng sau khi sắp xếp giảm dần:")
    arr.sortedWith { a, b -> b.soSanh(a) }.forEach { it.inPhanSo() }
}