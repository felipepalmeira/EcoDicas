package com.gs.ecodicas


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var tipsAdapter: TipsAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        val btnTeam: Button = findViewById(R.id.btnTeam)

        val tipsList = listOf(
            Tip("Use lâmpadas LED", "Lâmpadas LED são mais eficientes e duram mais."),
            Tip("Desligue aparelhos que não estão em uso", "Aparelhos eletrônicos consomem energia mesmo em modo de espera. Desconecte quando não for usar."),
            Tip("Reduza o uso do carro", "Caminhe, use bicicletas ou transporte público sempre que possível."),
            Tip("Recicle", "Reciclar ajuda a reduzir a quantidade de lixo e a conservar recursos."),
            Tip("Use sacolas reutilizáveis", "Reduza o uso de sacolas plásticas ao usar sacolas reutilizáveis.")
        )

        tipsAdapter = TipsAdapter(tipsList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = tipsAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = tipsList.filter {
                    it.title.contains(newText ?: "", ignoreCase = true) ||
                            it.description.contains(newText ?: "", ignoreCase = true)
                }
                tipsAdapter = TipsAdapter(filteredList)
                recyclerView.adapter = tipsAdapter
                return true
            }
        })

        btnTeam.setOnClickListener {
            val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
        }
    }
}
