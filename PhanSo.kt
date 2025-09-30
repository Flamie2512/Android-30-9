import kotlin.math.abs

// Lop Phan so
class PhanSo(var tu: Int, var mau: Int) {
    init {
        require(mau != 0) { "Mau so khong duoc bang 0" }
    }

    // Ham in phan so
    fun inPhanSo() {
        println("$tu/$mau")
    }

    // Ham rut gon
    fun toiGian() {
        val ucln = gcd(abs(tu), abs(mau))
        tu /= ucln
        mau /= ucln
        if (mau < 0) {
            tu = -tu
            mau = -mau
        }
    }

    // So sanh 2 phan so (-1,0,1)
    fun soSanh(ps: PhanSo): Int {
        val a = tu * ps.mau
        val b = ps.tu * mau
        return when {
            a < b -> -1
            a == b -> 0
            else -> 1
        }
    }

    // Cong 2 phan so
    fun cong(ps: PhanSo): PhanSo {
        val tuMoi = tu * ps.mau + ps.tu * mau
        val mauMoi = mau * ps.mau
        val kq = PhanSo(tuMoi, mauMoi)
        kq.toiGian()
        return kq
    }

    // Tim UCLN
    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }
}

// --------- HAM MAIN ---------
fun main() {
    print("Nhap so luong phan so: ")
    val n = readln().toInt()
    val arr = Array(n) {
        var tu: Int
        var mau: Int
        // Lap cho den khi nhap mau != 0
        do {
            print("Nhap tu so: ")
            tu = readln().toInt()
            print("Nhap mau so (!= 0): ")
            mau = readln().toInt()
        } while (mau == 0)
        PhanSo(tu, mau) // goi constructor, init se kiem tra
    }

    println("\nMang phan so vua nhap:")
    arr.forEach { it.inPhanSo() }

    println("\nMang sau khi toi gian:")
    arr.forEach {
        it.toiGian()
        it.inPhanSo()
    }

    var tong = PhanSo(0, 1)
    for (ps in arr) {
        tong = tong.cong(ps)
    }
    print("\nTong cac phan so = ")
    tong.inPhanSo()

    val maxPS = arr.maxWithOrNull { a, b -> a.soSanh(b) }!!
    print("\nPhan so lon nhat la: ")
    maxPS.inPhanSo()

    println("\nMang sau khi sap xep giam dan:")
    arr.sortedWith { a, b -> b.soSanh(a) }.forEach { it.inPhanSo() }
}
