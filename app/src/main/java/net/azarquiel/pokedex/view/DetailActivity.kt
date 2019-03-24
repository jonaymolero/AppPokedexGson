package net.azarquiel.pokedex.view

import android.app.ProgressDialog
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.rowpokemon.view.*
import net.azarquiel.pokedex.R
import net.azarquiel.pokedex.model.Descripcion
import net.azarquiel.pokedex.model.ResultPokemon
import net.azarquiel.pokedex.model.Results
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.textColor
import org.jetbrains.anko.uiThread
import java.net.URL
import java.nio.charset.Charset

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    private lateinit var pokemon:Results
    private lateinit var result:ResultPokemon
    private lateinit var result2:Descripcion
    private lateinit var p:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        pokemon=intent.getSerializableExtra("pokemonpulsado") as Results
        p=indeterminateProgressDialog("This a progress dialog")
        p.show()
        cargarDatos()
    }

    private fun cargarDatos() {
        doAsync {
            val json= URL(pokemon.url.replace("-species","")).readText(Charset.forName("UTF-8"))
            val json2=URL(pokemon.url).readText(Charset.forName("UTF-8"))
            result= Gson().fromJson(json, ResultPokemon::class.java)
            result2=Gson().fromJson(json2,Descripcion::class.java)
            uiThread {
                p.hide()
                pintar()
            }
        }
    }

    private fun pintar() {
        var lp= LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(10,10,10,10)

        for (i in result.abilities){
            var tv=TextView(this)
            tv.setPadding(10,10,10,10)
            tv.layoutParams = lp
            tv.textSize=24F
            tv.text = "- ${i.ability.name}"
            lyHabilidades.addView(tv)
        }
        for(i in result.types){
            var tv=TextView(this)
            tv.setPadding(10,10,10,10)
            tv.layoutParams = lp
            tv.text = i.type.name
            tv.textSize=24F
            var id=resources.getIdentifier(i.type.name,"color",packageName)
            var id1=resources.getColor(id)
            tv.setBackgroundColor(id1)
            tv.textColor=Color.WHITE
            lyTipos.addView(tv)
        }
        tvNumeroDetail.text=result.id.toString()
        tvNombreDetail.text=result.name.toUpperCase()
        Picasso.with(this).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${result.id}.png").into(ivImagenDetail)
        tvpeso.text="${result.weight} kg"
        tvAltura.text="${result.height} dm"
        for(i in result2.flavor_text_entries){
            if(i.language.name.equals("es")){
                tvDescriptionDetail.text=i.flavor_text
                break
            }
        }
    }
}
