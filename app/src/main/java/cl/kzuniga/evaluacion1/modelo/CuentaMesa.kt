package cl.kzuniga.evaluacion1.modelo

class CuentaMesa() {
    val itemsListaMesa = mutableListOf<ItemMesa>()
    var aceptaPropina = true

    // agrega un item mas, como pastel o cazuela desde ItemMesa
    fun agregarItem( itemMenu: ItemMenu, cant: Int ){
        val agregarItemsMesa = ItemMesa(itemMenu, cant)
        itemsListaMesa.add(agregarItemsMesa)

    }

    // Calcula el monto total de los productos agregados a cuenta recorriendo la lista y sumando
    fun calcularTotalSinPropina():Int {
        var totalMesa = 0
        for (itemLista in itemsListaMesa){
            totalMesa += itemLista.calcularSubtotal()
        }
        return totalMesa
    }

    // Calcula la propina de un 10% del total de la cuenta de la mesa
    fun calcularPropina():Int {
        return ((calcularTotalSinPropina() * 10) / 100)
    }

    // Calcula el total con propina incluida
    fun calcularTotalConPropina():Int{
        return calcularTotalSinPropina() + calcularPropina()
    }
}