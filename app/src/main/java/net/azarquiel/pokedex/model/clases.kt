package net.azarquiel.pokedex.model

import java.io.Serializable


data class Result(var results:List<Results>)
data class Results(var name:String,var url:String):Serializable
data class ResultPokemon(var abilities:List<Habilidades>, var height:Int,var id:Int, var name:String,var weight:Int, var types:List<Tipos>)
data class Habilidades(var ability:Habilidad)
data class Habilidad(var name: String)
data class Tipos(var type:Tipo)
data class Tipo(var name:String)
data class Descripcion(var flavor_text_entries:List<Descripciones>)
data class Descripciones(var flavor_text:String, var language:Lengua)
data class  Lengua(var name:String)