package cl.kzuniga.evaluacion1.modelo

class ItemMesa( val itemMenu: ItemMenu, val cant: Int) {

    fun calcularSubtotal():Int {
        var precio = itemMenu.precio.toInt()
        return precio * cant
    }
}