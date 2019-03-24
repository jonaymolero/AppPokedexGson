package net.azarquiel.pokedex.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.pokedex.R
import net.azarquiel.pokedex.adapter.CustomAdapter
import net.azarquiel.pokedex.model.Result
import net.azarquiel.pokedex.model.Results
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var result:Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cargarDatos()
    }

    private fun cargarDatos() {
        doAsync {
            val json= URL("https://pokeapi.co/api/v2/pokemon-species/").readText()
            result=Gson().fromJson(json,Result::class.java)
            uiThread {
                pintar()
            }
        }
    }

    private fun pintar() {
        val adapter = CustomAdapter(this,R.layout.rowpokemon,result.results)
        rvPokemon.layoutManager = LinearLayoutManager(this)
        rvPokemon.adapter = adapter
    }

    fun onClickPokemon(v:View){
        var pokemon=v.tag as Results
        var intent=Intent(this,DetailActivity::class.java)
        intent.putExtra("pokemonpulsado",pokemon)
        startActivity(intent)
    }
}
