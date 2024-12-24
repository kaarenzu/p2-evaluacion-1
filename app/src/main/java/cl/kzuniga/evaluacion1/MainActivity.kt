package cl.kzuniga.evaluacion1

import android.os.Bundle
import android.text.Editable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cl.kzuniga.evaluacion1.modelo.ItemMenu
import cl.kzuniga.evaluacion1.modelo.ItemMesa
import cl.kzuniga.evaluacion1.modelo.CuentaMesa

class MainActivity : AppCompatActivity() {
    // Instancio los objetos de disponibles en el Menú
    val pastelChoclo = ItemMenu(nombre = "Pastel de choclo", precio = "12000")
    val cazuela = ItemMenu(nombre = "Cazuela", precio = "10000")

    // Instancio los objetos disponible
    private var switchCalcularPropina: Switch? = null
    private var tvPropina : TextView?=null
    private var calcularPropina = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvPropina = findViewById<TextView>(R.id.tvPropina)
        switchCalcularPropina = findViewById<Switch>(R.id.switchPropina)
        val idCantPastelChoclo = findViewById<EditText>(R.id.etCantidadPastelDeChoclo)
        val idCantCazuela = findViewById<EditText>(R.id.etCantidadCazuela)


        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged( s: CharSequence?, start: Int, count: Int,after: Int){}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                calcularTotalPastelChoclo()
                calcularTotalCazuela()
                calcularPropina()
                calcularTotalCuentaSP()
                calcularTotalConPropina()
            }
        }
        idCantPastelChoclo.addTextChangedListener(textWatcher)
        idCantCazuela.addTextChangedListener(textWatcher)


        switchCalcularPropina?.setOnCheckedChangeListener{ _, isChecked ->
            calcularPropina = isChecked
        }
    }

    //Calcular total cazuela
    fun calcularTotalCazuela() {
        //capturo el id
        val idCantCazuela = findViewById<EditText>(R.id.etCantidadCazuela)
        val cantCazuela = idCantCazuela.text.toString().toIntOrNull() ?: 0
        val totalCazuela = ItemMesa(cazuela,cantCazuela)



        //textview Cazuela
        findViewById<TextView>(R.id.tvPrecioCazuela).text = totalCazuela.calcularSubtotal().toString()
    }

    //Calcular total Pastel de choclo
    fun calcularTotalPastelChoclo() {
        //capturo el id
        val idCantPastelChoclo = findViewById<EditText>(R.id.etCantidadPastelDeChoclo)
        val cantPastel = idCantPastelChoclo.text.toString().toIntOrNull() ?: 0
        val totalPastelChoclo = ItemMesa(pastelChoclo,cantPastel )
        // se actualiza el total del pastel de choclo
        findViewById<TextView>(R.id.tvPrecioPastel).text = totalPastelChoclo.calcularSubtotal().toString()
    }

    //Calcular total de la cuenta sin propina
    fun calcularTotalCuentaSP() {
        var totalCuenta = CuentaMesa()
        //capturo el id
        val idCantPastelChoclo = findViewById<EditText>(R.id.etCantidadPastelDeChoclo)
        val idCantCazuela = findViewById<EditText>(R.id.etCantidadCazuela)
        //lo paso a int o 0
        val cantPastelChoclo = idCantPastelChoclo.text.toString().toIntOrNull() ?: 0
        val cantCazuela = idCantCazuela.text.toString().toIntOrNull() ?: 0

        totalCuenta.agregarItem(pastelChoclo, cantPastelChoclo)
        totalCuenta.agregarItem(cazuela, cantCazuela)
        // Creo una variable para mostrar el total de la cuenta sin propina
        val totalCuentaSP = totalCuenta.calcularTotalSinPropina().toString()
        findViewById<TextView>(R.id.tvTotalComida).text = totalCuentaSP
    }

    fun calcularPropina(){
        var totalCuenta = CuentaMesa()
        totalCuenta.calcularPropina()

        findViewById<TextView>(R.id.tvTotalPropina).text =
            totalCuenta.calcularPropina().toString()

    }

    fun calcularTotalConPropina(){
        var totalCuenta = CuentaMesa()
        totalCuenta.calcularTotalConPropina()

        findViewById<TextView>(R.id.tvTotalCuenta).text =
            totalCuenta.calcularTotalConPropina().toString()
    }
}