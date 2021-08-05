package com.example.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
        Log.w("lifecycle", "CREATE - criando a tela")
    }

    private fun setListeners() {
        height?.doAfterTextChanged { text ->
//            Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show()
        }
        weight?.doOnTextChanged { text, _, _, _ ->
//            titleTxt?.text = text
        }
        calcButton?.setOnClickListener {
            imcCalc(weight.text.toString(), height.text.toString())
        }
    }

    private fun imcCalc(weight: String, height: String) {
        val weight = weight.toFloatOrNull()
        val height = height.toFloatOrNull()
        if (weight != null && height != null) {
            val imc = weight / (height * height)
            titleTxt.text = "Seu IMC é: \n%.2f".format(imc)
        }
    }


    override fun onStart() {
        super.onStart()
        Log.w("lifecycle", "START - tela visível para usuário")
    }

    override fun onResume() {
        super.onResume()
        Log.w("lifecycle", "RESUME - agora você pode interagir com a tela")
    }

    override fun onPause() {
        super.onPause()
        Log.w("lifecycle", "PAUSE - a tela perdeu o foco, não é mais possível interagir")
    }

    override fun onStop() {
        super.onStop()
        Log.w("lifecycle", "STOP - a tela não está mais visível, mas ainda existe")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("lifecycle", "\nDESTROY - tela foi destruída")
    }
}