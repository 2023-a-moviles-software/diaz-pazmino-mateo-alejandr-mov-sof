package DAO

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.Writer

object JSON {
    var objectMapper: ObjectMapper = getDefaultObjectMapper()
    private fun getDefaultObjectMapper(): ObjectMapper {
        var defaultObjectMapper = ObjectMapper()
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
        defaultObjectMapper.registerModule(JavaTimeModule())
        return defaultObjectMapper
    }

    private fun parse(src:String): JsonNode {
        return objectMapper.readTree(src)
    }

    fun <A>  fromJson(node: JsonNode, clazz:Class<A>):ArrayList<A> {
        var objects:ArrayList<A> = ArrayList()
        node.forEach { objects.add(objectMapper.treeToValue(it,clazz)) }
        return objects
    }

    fun toJson(a: Any): JsonNode {
        return objectMapper.valueToTree(a)
    }

    fun saveJson(node: JsonNode){
        val output: Writer
        val file =  File("src/main/resources/Generos_Peliculas.json")
        output = BufferedWriter(FileWriter(file))
        output.write(jsonToString(node))
        output.close()
    }
    fun loadJson():JsonNode{
        var jsonReaded = File("src/main/resources/Generos_Peliculas.json").readText()
        return parse(jsonReaded)
    }

    fun jsonToString(node: JsonNode):String{
        var objectWriter = objectMapper.writer()
        objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT)
        return objectWriter.writeValueAsString(node)
    }
}