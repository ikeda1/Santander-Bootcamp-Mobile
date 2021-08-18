package com.example.listacontatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class contact_detail : AppCompatActivity() {
    private var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
        initToolbar()
        getExtras()
        bindViews()
    }

    private fun initToolbar() {
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Habilita o botao de voltar na toolbar, mas é preciso criar um outro método que realize a ação de click
        }

    // Possibilita a operação de click no botão de voltar da toolbar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    // Obtém os dados que foram mandados da tela que abriu a contact_detail activity
    private fun getExtras() {
        contact = intent.getParcelableExtra(EXTRA_CONTACT)
    }

    // Coloca os dados recebidos na tela contact_detail
    private fun bindViews() {
        findViewById<TextView>(R.id.tv_name).text = contact?.name
        findViewById<TextView>(R.id.tv_phone).text = contact?.phone
    }

    companion object {
        const val EXTRA_CONTACT: String = "EXTRA CONTACT"
    }
}